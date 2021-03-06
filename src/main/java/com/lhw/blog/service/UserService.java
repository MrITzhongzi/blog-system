package com.lhw.blog.service;

import com.lhw.blog.domain.LhwArticles;
import com.lhw.blog.domain.LhwUser;
import com.lhw.blog.domain.LhwUserFirends;
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

    LhwUser queryUserById(int userId);

    int updateUserInfo(LhwUser lhwUser);

    int updatePassword(String newPwd, int userId);

    int updateHeadImg(int userId, String headImg);

    List<LhwArticles> checkUserArticle(int userId);

    LhwUserFirends isAttention(int myUserId, int otherUserId);

    int addAttention(int myUserId, int otherUserId);

    int updateUserComment(int myUserId, int otherUserId, String commentName);

    List<LhwUser> attentionUserList(int userId);
}
