package com.jiu.sys.service.impl;

import com.jiu.sys.domain.Role;
import com.jiu.sys.mapper.RoleMapper;
import com.jiu.sys.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName RoleServiceImpl
 * @Author Jiu
 * @Create 2020/4/4 20:32
 **/
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<String> queryRolesByUserId(Integer userId) {
        List<Role> list=roleMapper.queryRoleByUserId(userId);
        List<String> roles=new ArrayList<>();
        for (Role role : list) {
            roles.add(role.getRolename());
        }
        return roles;
    }
}
