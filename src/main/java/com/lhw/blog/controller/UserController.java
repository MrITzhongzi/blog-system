package com.lhw.blog.controller;

import com.lhw.blog.domain.LhwUser;
import com.lhw.blog.service.UserService;
import com.lhw.blog.tool.JsonBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description: 用户模块
 * @author: lihongwei
 * @time: 2020/4/8 1:42 下午
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping(path = "register", method = RequestMethod.POST)
    public JsonBuilder userRegister(@RequestParam(value = "username", required = true) String username,
                                    @RequestParam(value = "password", required = true) String password,
                                    @RequestParam(value = "phone", required = true) String phone,
                                    @RequestParam(value = "nickname", required = true) String nickname){

        // 从数据库查询有没有此用户，有的话返回错误，没有继续
        List<LhwUser> userList = userService.queryAllUser();
        if(userList.size() > 0) {
            return JsonBuilder.buildError("用户已经存在");
        }
        // 校验 各个字段是否符合要求

        // 密码加密，存入数据库

        // 使用jwt成成密钥


        return JsonBuilder.buildSuccess();
    }
}
