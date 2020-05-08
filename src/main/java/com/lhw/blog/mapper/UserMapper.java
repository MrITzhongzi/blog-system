package com.lhw.blog.mapper;

import com.lhw.blog.domain.LhwUser;
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
    LhwUser queryUserById(String userId);

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
    int updatePassword(String newPwd, String userId);

}
