package com.jiu.sys.service;

import java.util.List;

public interface PermissionService {

    /**
     * 根据用户id查询权限
     * @param userId
     * @return
     */
    List<String> queryPermissionByUserId(Integer userId);
}
