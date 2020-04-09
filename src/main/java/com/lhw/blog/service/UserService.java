package com.lhw.blog.service;

import com.lhw.blog.domain.LhwUser;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 用户 service
 * @author: lihongwei
 * @time: 2020/4/8 3:25 下午
 */
public interface UserService {

    int insert(LhwUser user);

    List<LhwUser> queryAllUser();

    LhwUser queryUserByPhone(String phone);
}
