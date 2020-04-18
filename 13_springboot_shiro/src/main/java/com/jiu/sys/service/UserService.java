package com.jiu.sys.service;

import com.jiu.sys.domain.User;

public interface UserService {
    /**
     * 根据用户名查询用户对象
     * @param username
     * @return
     */
    User queryUserByUserName(String username);
}
