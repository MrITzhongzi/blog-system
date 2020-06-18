package com.lhw.blog.controller;

import com.lhw.blog.config.CommonParam;
import com.lhw.blog.domain.LhwArticles;
import com.lhw.blog.domain.LhwUser;
import com.lhw.blog.domain.LhwUserFirends;
import com.lhw.blog.service.UserService;
import com.lhw.blog.tool.IpUtil;
import com.lhw.blog.tool.JWTUtils;
import com.lhw.blog.tool.JsonBuilder;
import com.lhw.blog.tool.SecretUtils;
import org.springframework.web.bind.annotation.*;
import sun.misc.Request;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 用户模块
 * @author: linger
 * @time: 2020/4/8 1:42 下午
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 注册逻辑 map 中有 username password phone nickname
     *
     * @param mapParams
     * @param request
     * @return
     */
    @RequestMapping(path = "register", method = RequestMethod.POST)
    public JsonBuilder userRegister(@RequestBody Map<String, String> mapParams,
                                    HttpServletRequest request) {

        String username = mapParams.get("username");
        String password = mapParams.get("password");
        String phone = mapParams.get("phone");
        String nickname = mapParams.get("nickname");
        // 从数据库查询有没有此用户，有的话返回错误，没有继续

        LhwUser tempUser = userService.queryUserByPhone(phone);
        if (tempUser != null) {
            return JsonBuilder.buildError("该手机号已经注册");
        }
        // 校验 各个字段是否符合要求
        if (username.trim().isEmpty()
                || password.trim().isEmpty()
                || phone.trim().isEmpty()
                || nickname.trim().isEmpty()) {
            return JsonBuilder.buildError("输入的信息有误");
        } else if (username.contains(CommonParam.NULLSTRING)
                || password.contains(CommonParam.NULLSTRING)
                || phone.contains(CommonParam.NULLSTRING)
                || nickname.contains(CommonParam.NULLSTRING)) {
            return JsonBuilder.buildError("输入的信息不能包含空格");
        }

        // 密码加密，存入数据库
        String newPwd = SecretUtils.generatePwd(password);
        if (newPwd == null) {
            return JsonBuilder.buildError("密码加密失败，请稍后再试。");
        }

        //获取 ip
        String requestIp = IpUtil.getIpAddr(request);

        LhwUser user = new LhwUser();
        user.setUserPassword(newPwd);
        user.setUserTelephoneNumber(phone);
        user.setUserNickname(nickname);
        user.setUserName(username);
        user.setUserIp(requestIp);
        // 返回主键id  0 为异常
        Integer row = userService.insert(user);
        if (row != 0) {
            Map<String, String> map = new HashMap<>();
            map.put("nickname", nickname);
            map.put("phone", phone);
            return JsonBuilder.buildSuccess("成功", map);
        }

        return JsonBuilder.buildError("失败，请稍后重试");
    }


    @RequestMapping(path = "login", method = RequestMethod.POST)
    public JsonBuilder userRegister(@RequestBody Map<String, String> mapParams) {

        String phone = mapParams.get("phone");
        String password = mapParams.get("password");

        LhwUser user = userService.queryUserByPhone(phone);
        if (user == null) {
            return JsonBuilder.buildError("用户不存在。");
        }

        String generatePwd = SecretUtils.generatePwd(password);
        if (phone.equals(user.getUserTelephoneNumber()) && generatePwd.equals(user.getUserPassword())) {
            String token = JWTUtils.generateToken(user);
            Map<String, String> map = new HashMap<>();
            map.put("username", user.getUserName());
            map.put("nickname", user.getUserNickname());
            map.put("phone", user.getUserTelephoneNumber());
            map.put("token", token);
            return JsonBuilder.buildSuccess(map);
        }


        return JsonBuilder.buildError("用户名或密码不正确");
    }

    @RequestMapping(path = "get_user_info", method = RequestMethod.GET)
    public JsonBuilder getUserInfo(@RequestParam(value = "user_id", required = false) String user_id,
                                   HttpServletRequest request) {
        int userId;
        if (user_id != null) {
            userId = Integer.valueOf(user_id);
        } else {
            userId = (int) request.getAttribute("user_id");
        }
        LhwUser lhwUser = userService.queryUserById(userId);
        if (lhwUser != null) {
            return JsonBuilder.buildSuccess(lhwUser);
        }

        return JsonBuilder.buildError("查询用户失败，请稍后再试。");
    }

    /**
     * 需要参数 userEmail 邮箱 userProfilePhoto 头像
     * userRegistrationTime 注册时间  userBirthday 生日
     * userAge 年龄
     *
     * @param map
     * @param request
     * @return
     */
    @RequestMapping(path = "update_user_info", method = RequestMethod.POST)
    public JsonBuilder updateUserInfo(@RequestBody Map<String, String> map,
                                      HttpServletRequest request) {
        int userId = (int) request.getAttribute("user_id");
        if (!map.containsKey("userEmail")
                || !map.containsKey("userProfilePhoto")
                || !map.containsKey("userRegistrationTime")
                || !map.containsKey("userBirthday")
                || !map.containsKey("userAge")) {
            return JsonBuilder.buildError("填写的信息有误，请稍后重试。");
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            simpleDateFormat.parse(map.get("userRegistrationTime"));
            Date userBirthday = simpleDateFormat.parse(map.get("userBirthday"));
            java.sql.Date date = new java.sql.Date(userBirthday.getTime());

            LhwUser lhwUser = new LhwUser();
            lhwUser.setUserId(userId);
            lhwUser.setUserEmail(map.get("userEmail"));
            lhwUser.setUserProfilePhoto(map.get("userProfilePhoto"));
            lhwUser.setUserRegistrationTime(simpleDateFormat.parse(map.get("userRegistrationTime")));
            lhwUser.setUserBirthday(date);
            lhwUser.setUserAge(Integer.valueOf(map.get("userAge")));

            int i = userService.updateUserInfo(lhwUser);
            if (i == 1) {
                return JsonBuilder.buildSuccess("更新成功。");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return JsonBuilder.buildError("更新失败。");
    }

    /**
     * oldPwd 原密码 newPwd 新密码
     *
     * @param map
     * @param request
     * @return
     */
    @RequestMapping(path = "update_pwd", method = RequestMethod.POST)
    public JsonBuilder updatePassword(@RequestBody Map<String, String> map,
                                      HttpServletRequest request) {
        int userId = (int) request.getAttribute("user_id");
        if (!map.containsKey("oldPwd") || !map.containsKey("newPwd")) {
            return JsonBuilder.buildError("输入的密码有误，请重新输入。");
        }

        String oldPwd = map.get("oldPwd"),
                newPwd = map.get("newPwd");
        LhwUser lhwUser = userService.queryUserById(userId);
        String generateOldPwd = SecretUtils.generatePwd(oldPwd);
        if (!lhwUser.getUserPassword().equals(generateOldPwd)) {
            return JsonBuilder.buildError("输入的愿密码错误。");
        }

        String genrateNewPwd = SecretUtils.generatePwd(newPwd);
        if (generateOldPwd == null) {
            return JsonBuilder.buildError("生成新密码失败，请稍后重试。");
        }

        int i = userService.updatePassword(genrateNewPwd, userId);
        if (i == 1) {
            return JsonBuilder.buildSuccess("密码修改成功。");
        }

        return JsonBuilder.buildError("密码修改失败。");
    }


    /**
     * 更改头像 headImg 头像地址
     *
     * @param map
     * @return
     */
    @RequestMapping(path = "update_head_img", method = RequestMethod.POST)
    public JsonBuilder updateUserHeaderPhoto(@RequestBody Map<String, String> map,
                                             HttpServletRequest request) {
        System.out.println(map);
        String headImg = map.get("headImg");
        int userId = (int) request.getAttribute("user_id");
        if (headImg.isEmpty()) {
            return JsonBuilder.buildError("头像地址不能为空。");
        }
        int i = userService.updateHeadImg(userId, headImg);
        if (i == 1) {
            return JsonBuilder.buildSuccess("更新成功");
        }
        return JsonBuilder.buildError("更新失败");
    }


    /**
     * 查询用户文章
     *
     * @param request
     * @return
     */
    @RequestMapping(path = "user_article_list", method = RequestMethod.GET)
    public JsonBuilder checkUserArticleList(HttpServletRequest request) {
        int userId = (int) request.getAttribute("user_id");
        List<LhwArticles> lhwArticles = userService.checkUserArticle(userId);
        return JsonBuilder.buildSuccess(lhwArticles);
    }


    @GetMapping(path = "is_attention")
    public JsonBuilder isAttention(@RequestParam(value = "user_id") Integer userId, HttpServletRequest request) {
        int myUserId = (int) request.getAttribute("user_id");
        LhwUserFirends attention = userService.isAttention(myUserId, userId);
        String desc = attention == null ? "您还没有关注" : "您已经关注";
        return JsonBuilder.buildSuccess(0, desc, attention != null);
    }

    /**
     * 关注作者api
     *
     * @param userId 这个userid是被关注的作者的userid
     * @return
     */
    @GetMapping(path = "attention_user")
    public JsonBuilder attentionUser(@RequestParam(value = "user_id") Integer userId, HttpServletRequest request) {
        int myUserId = (int) request.getAttribute("user_id");
        LhwUserFirends attention = userService.isAttention(myUserId, userId);
        if (attention != null) {
            return JsonBuilder.buildError(-1, "您已经关注该用户。");
        }
        int row = userService.addAttention(myUserId, userId);
        if (row == 1) {
            return JsonBuilder.buildSuccess(0);
        }
        return JsonBuilder.buildError(-1, "关注失败，请稍后重试……");
    }

    /**
     * 修改关注作者的备注
     *
     * @param userId
     * @param commentName
     * @param request
     * @return
     */
    @GetMapping(path = "attention_name")
    public JsonBuilder updateAttentionUserComment(@RequestParam(value = "user_id") Integer userId,
                                                  @RequestParam(value = "comment_name") String commentName,
                                                  HttpServletRequest request) {
        int myUserId = (int) request.getAttribute("user_id");
        int row = userService.updateUserComment(myUserId, userId, commentName);
        if (row == 1) {
            return JsonBuilder.buildSuccess(0);
        }
        return JsonBuilder.buildError(-1, "更新备注失败，请稍后重试……");
    }

    @RequestMapping(path = "test", method = RequestMethod.POST)
    public JsonBuilder test(@RequestBody Object phone) {
        List<LhwUser> userList = userService.queryAllUser();
        return JsonBuilder.buildSuccess(userList);
    }

}
