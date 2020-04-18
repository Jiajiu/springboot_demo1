package com.jiu.sys.mapper;

import com.jiu.sys.domain.Permission;

import java.util.List;

public interface PermissionMapper {

    int deleteByPrimaryKey(Integer perid);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer perid);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    /**
     * 根据用户id查询权限
     * @param userId
     * @return
     */
    List<Permission> queryPermissionByUserId(Integer userId);
}
