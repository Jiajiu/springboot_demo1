package com.jiu.mapper;

import com.jiu.domain.RolePermission;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName RolePermissionMapper
 * @Author Jiu
 * @Create 2020/4/5 12:11
 **/
public interface RolePermissionMapper {
    int deleteByPrimaryKey(@Param("perid") Integer perid, @Param("roleid") Integer roleid);

    int insert(RolePermission record);

    int insertSelective(RolePermission record);
}