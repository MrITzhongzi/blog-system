package com.lhw.blog.mapper;

import com.lhw.blog.domain.LhwArticles;
import com.lhw.blog.domain.LhwUser;
import com.lhw.blog.domain.LhwUserFirends;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @description: 用户 mapper 用户的增删改查
 * @author: lihongwei
 * @time: 2020/4/8 3:19 下午
 */

public interface UserMapper {

    /**
     * 插入用户
     * @param user
     * @return
     */
    @Insert("insert into lhw_user(user_ip, user_name, user_password, user_nickname, user_telephone_number) values (#{userIp}, #{userName},#{userPassword},#{userNickname}, #{userTelephoneNumber})")
    @Options(useGeneratedKeys = true, keyProperty = "userId", keyColumn = "user_id")
    int insert(LhwUser user);

    /**
     * 查询所有用户
     * @return
     */
    @Select("select * from lhw_user")
    List<LhwUser> queryAllUser();


    /**
     * 根据手机号查找用户
     * @param phone
     * @return
     */
    @Select("SELECT * FROM lhw_user WHERE user_telephone_number = #{phone}")
    LhwUser queryUserByPhone(String phone);

    /**
     * 根据 用户id 查找用户
     * @param userId
     * @return
     */
    @Select("SELECT * FROM lhw_user WHERE user_id = #{userId}")
    LhwUser queryUserById(int userId);

    /**
     * 更新用户信息
     * @param lhwUser
     * @return
     */
    @Update("UPDATE lhw_user set user_email = #{userEmail} ,user_profile_photo = #{userProfilePhoto}, user_registration_time = #{userRegistrationTime},user_birthday = #{userBirthday}, user_age = #{userAge} WHERE user_id = #{userId}")
    int updateUserInfo(LhwUser lhwUser);

    /**
     * 修改密码
     * @param newPwd
     * @param userId
     * @return
     */
    @Update("UPDATE lhw_user set user_password = #{newPwd} WHERE user_id = #{userId}")
    int updatePassword(String newPwd, int userId);

    /**
     * 更改用户头像
     * @param userId
     * @param headImg
     * @return
     */
    @Update("UPDATE lhw_user SET user_profile_photo = #{headImg} WHERE user_id = #{userId}")
    int updateHeadImg(int userId, String headImg);

    @Select("SELECT * FROM lhw_articles WHERE user_id = #{userId}")
    List<LhwArticles> checkUserArticle(int userId);

    @Select("SELECT * FROM lhw_user_firends WHERE user_id = #{myUserId} and user_friends_id = #{otherUserId}")
    LhwUserFirends isAttention(int myUserId, int otherUserId);

    /**
     * 关注某作者
     * @param myUserId
     * @param otherUserId
     * @return
     */
    @Insert("INSERT INTO lhw_user_firends (user_id, user_friends_id) VALUES (#{myUserId}, #{otherUserId})")
    int addAttention(int myUserId, int otherUserId);

    @Update("UPDATE lhw_user_firends SET user_note = #{commentName} WHERE user_id = #{myUserId} and user_friends_id = #{otherUserId}")
    int updateUserComment(int myUserId, int otherUserId, String commentName);

    @Select("SELECT * FROM lhw_user WHERE user_id = #{userId}")
    List<LhwUser> attentionUserList(int userId);

}
