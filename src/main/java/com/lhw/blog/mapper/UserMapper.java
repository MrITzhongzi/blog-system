package com.lhw.blog.mapper;

import com.lhw.blog.domain.LhwUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: 用户 mapper 用户的增删改查
 * @author: lihongwei
 * @time: 2020/4/8 3:19 下午
 */
@Component
public interface UserMapper {

    @Insert("insert into lhw_user(user_ip, user_name, user_password, user_nickname) values (#{userIp}, #{userName},#{userPassword},#{userNickname})")
    int insert(LhwUser user);

    @Select("select * from lhw_user")
    List<LhwUser> queryAllUser();
}
