package com.jiu.service.impl;

import com.jiu.domain.RolePermission;
    /**
 * @ClassName RolePermissionService
 * @Author Jiu
 * @Create 2020/4/5 12:11
 **/
public interface RolePermissionService{


    int deleteByPrimaryKey(Integer perid,Integer roleid);

    int insert(RolePermission record);

    int insertSelective(RolePermission record);

}
