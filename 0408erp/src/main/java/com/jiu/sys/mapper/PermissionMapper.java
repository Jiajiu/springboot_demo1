package com.jiu.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiu.sys.domain.Permission;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;

/**
 * @ClassName PermissionMapper
 * @Author Jiu
 * @Create 2020/4/9 11:13
 **/
public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * 根据权限或菜单id删除权限表个角色的关系表里面的数据
     * @param id
     */
    void deleteRolePermissionByPid(@Param("id")Serializable id);
}