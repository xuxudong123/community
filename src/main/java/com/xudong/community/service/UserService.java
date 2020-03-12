package com.xudong.community.service;

import com.xudong.community.mapper.UserMapper;
import com.xudong.community.model.User;
import com.xudong.community.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xuxudong on 2020-03-09 15:38
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> users = userMapper.selectByExample(userExample);
       if(users.size() == 0){
           //插入
           user.setMgtCreate(System.currentTimeMillis());
           user.setMgtModified(user.getMgtCreate());
           userMapper.insert(user);
       }else {
           //更新
           User dbUser = users.get(0);
           User updateUser = new User();
           updateUser.setMgtModified(System.currentTimeMillis());
           updateUser.setAvatarUrl(user.getAvatarUrl());
           updateUser.setName(user.getName());
           updateUser.setToken(user.getToken());

           UserExample example = new UserExample();
           example.createCriteria().andIdEqualTo(dbUser.getId());
           userMapper.updateByExampleSelective(updateUser, example);
       }
    }
}
