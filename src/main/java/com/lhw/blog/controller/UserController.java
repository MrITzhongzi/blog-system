package com.lhw.blog.controller;

import com.lhw.blog.config.CommonParam;
import com.lhw.blog.domain.LhwUser;
import com.lhw.blog.service.UserService;
import com.lhw.blog.tool.IpUtil;
import com.lhw.blog.tool.JWTUtils;
import com.lhw.blog.tool.JsonBuilder;
import com.lhw.blog.tool.SecretUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:  用户模块
 * @author: linger
 * @time: 2020/4/8 1:42 下午
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 用户注册 api
     * @param username
     * @param password
     * @param phone
     * @param nickname
     * @param request
     * @return
     */
    @RequestMapping(path = "register", method = RequestMethod.POST)
    public JsonBuilder userRegister(@RequestParam(value = "username") String username,
                                    @RequestParam(value = "password") String password,
                                    @RequestParam(value = "phone") String phone,
                                    @RequestParam(value = "nickname") String nickname,
                                    HttpServletRequest request){

        // 从数据库查询有没有此用户，有的话返回错误，没有继续

        LhwUser tempUser = userService.queryUserByPhone(phone);
        if(tempUser != null) {
            return JsonBuilder.buildError("该手机号已经注册");
        }
        // 校验 各个字段是否符合要求
        if(username.trim().isEmpty()
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
        if(newPwd == null) {
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
        if(row != 0) {
            Map<String,String> map = new HashMap<>();
            map.put("nickname", nickname);
            map.put("phone", phone);
            return JsonBuilder.buildSuccess("成功", map);
        }

        return JsonBuilder.buildError("失败，请稍后重试");
    }


    @RequestMapping(path = "login", method = RequestMethod.POST)
    public JsonBuilder userRegister(@RequestParam(value="phone") String phone,
                                    @RequestParam(value = "password") String password){


        LhwUser user = userService.queryUserByPhone(phone);
        if(user == null) {
            return JsonBuilder.buildError("用户不存在。");
        }

        String generatePwd = SecretUtils.generatePwd(password);
        if(phone.equals(user.getUserTelephoneNumber()) && generatePwd.equals(user.getUserPassword())) {
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
}
