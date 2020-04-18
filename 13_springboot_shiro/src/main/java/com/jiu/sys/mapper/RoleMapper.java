package com.jiu.sys.mapper;

import com.jiu.sys.domain.Role;

import java.util.List;

public interface RoleMapper {

    int deleteByPrimaryKey(Integer roleid);

    int insert(Role role);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer roleid);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    /**
     * 根据用户id查询用户角色
     * @param userId
     * @return
     */
    List<Role> queryRoleByUserId(Integer userId);
}
