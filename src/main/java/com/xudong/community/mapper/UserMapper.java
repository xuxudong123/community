package com.xudong.community.mapper;

import com.xudong.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * Created by xuxudong on 2020-03-02 17:21
 */
@Mapper
@Component
public interface UserMapper {
    @Insert("insert into user (name,account_id,token,mgt_create,mgt_modified,avatar_url) values (#{name},#{accountId},#{token},#{mgtCreate},#{mgtModified},#{avatarUrl})")
    void insert(User user);

    @Select("select * from user where token = #{token}")
    User getUserByToken(@Param("token") String token);

    @Select("select * from user where id = #{id}")
    User getUserById(@Param("id") Integer id);
}
