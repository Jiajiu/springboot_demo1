package com.jiu.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.jiu.domain.RolePermission;
import com.jiu.mapper.RolePermissionMapper;
import com.jiu.service.impl.RolePermissionService;
/**
 * @ClassName RolePermissionServiceImpl
 * @Author Jiu
 * @Create 2020/4/5 12:11
 **/
@Service
public class RolePermissionServiceImpl implements RolePermissionService{

    @Resource
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public int deleteByPrimaryKey(Integer perid,Integer roleid) {
        return rolePermissionMapper.deleteByPrimaryKey(perid,roleid);
    }

    @Override
    public int insert(RolePermission record) {
        return rolePermissionMapper.insert(record);
    }

    @Override
    public int insertSelective(RolePermission record) {
        return rolePermissionMapper.insertSelective(record);
    }

}
