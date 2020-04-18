package com.jiu.sys.service;

import java.util.List;

public interface RoleService {

    /**
     * 根据用户id查询角色
     * @param userId
     * @return
     */
    List<String> queryRolesByUserId(Integer userId);
}
