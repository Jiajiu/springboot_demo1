package com.jiu.service.impl;

import com.jiu.domain.User;
import com.jiu.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName UserServiceImpl
 * @Author Jiu
 * @Create 2020/4/3 22:16
 **/
@Service
@Transactional  //所有方法全部参与事务
public class UserServiceImpl implements UserService {
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(User user) {
        return 0;
    }

    @Override
    public User selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKey(User user) {
        return 0;
    }

    @Override
    public List<User> queryAllUser() {
        return null;
    }
}
