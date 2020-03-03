package com.xudong.community.mapper;

import com.xudong.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * Created by xuxudong on 2020-03-02 17:21
 */
@Mapper
@Component
public interface UserMapper {
    @Insert("insert into user (name,account_id,token,mgt_create,mgt_modified) values (#{name},#{accountId},#{token},#{mgtCreate},#{mgtModified})")
    void insert(User user);
}
