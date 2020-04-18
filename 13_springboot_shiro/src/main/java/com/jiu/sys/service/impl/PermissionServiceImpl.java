package com.jiu.sys.service.impl;

import com.jiu.sys.domain.Permission;
import com.jiu.sys.mapper.PermissionMapper;
import com.jiu.sys.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName PermissionServiceImpl
 * @Author Jiu
 * @Create 2020/4/4 20:32
 **/
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<String> queryPermissionByUserId(Integer userId) {
        List<Permission> list=permissionMapper.queryPermissionByUserId(userId);
        List<String> permissions=new ArrayList<>();
        for (Permission permission : list) {
            permissions.add(permission.getPercode());
        }
        return permissions;
    }
}
