package com.lhw.blog.mapper;

import com.lhw.blog.domain.LhwUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

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
    @Insert("insert into lhw_user(user_ip, user_name, user_password, user_nickname, user_telephone_number) values (#{userIp}, #{userName},#{userPassword},#{userNickname}, #{userTelephoneNumber});")
    @Options(useGeneratedKeys = true, keyProperty = "userId", keyColumn = "user_id", useCache = false)
    int insert(LhwUser user);

    /**
     * 查询所有用户
     * @return
     */
    @Select("select * from lhw_user")
    List<LhwUser> queryAllUser();


    @Select("select * from lhw_user where user_telephone_number = #{phone} limit 1")
    LhwUser queryUserByPhone(String phone);

}
