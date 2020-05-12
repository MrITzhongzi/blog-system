package com.lhw.blog.service.impl;

import com.lhw.blog.domain.LhwArticles;
import com.lhw.blog.domain.LhwUser;
import com.lhw.blog.mapper.UserMapper;
import com.lhw.blog.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author: lihongwei
 * @time: 2020/4/8 3:26 下午
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public int insert(LhwUser user) {
        try {
            int num = userMapper.insert(user);
            return num;
        } catch (Exception e) { }
        // 异常
        return 0;
    }

    @Override
    public List<LhwUser> queryAllUser() {

        return userMapper.queryAllUser();
    }

    @Override
    public LhwUser queryUserByPhone(String phone) {

        return userMapper.queryUserByPhone(phone);
    }

    @Override
    public LhwUser queryUserById(String userId) {
        return userMapper.queryUserById(userId);
    }

    @Override
    public int updateUserInfo(LhwUser lhwUser) {
        return userMapper.updateUserInfo(lhwUser);
    }

    @Override
    public int updatePassword(String newPwd, String userId) {
        return userMapper.updatePassword(newPwd, userId);
    }

    @Override
    public int updateHeadImg(int userId, String headImg) {
        return userMapper.updateHeadImg(userId, headImg);
    }

    @Override
    public List<LhwArticles> checkUserArticle(int userId) {
        return userMapper.checkUserArticle(userId);
    }
}
