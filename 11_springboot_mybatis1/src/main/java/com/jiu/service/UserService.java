package com.jiu.service;

import com.jiu.domain.User;

import java.util.List;

/**
 * @ClassName UserService
 * @Author Jiu
 * @Create 2020/4/3 22:15
 **/
public interface UserService {

    int deleteByPrimaryKey(Integer id);

    int insert(User user);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(User user);

    List<User> queryAllUser();
}
