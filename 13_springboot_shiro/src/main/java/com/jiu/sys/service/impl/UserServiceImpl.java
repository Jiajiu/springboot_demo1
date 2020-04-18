package com.jiu.sys.service.impl;

import com.jiu.sys.domain.User;
import com.jiu.sys.mapper.UserMapper;
import com.jiu.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserServiceImpl
 * @Author Jiu
 * @Create 2020/4/4 20:32
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User queryUserByUserName(String username) {
        return userMapper.queryUserByUserName(username);
    }
}
