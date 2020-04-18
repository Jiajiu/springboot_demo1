package com.jiu.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiu.sys.domain.Role;

import java.util.List;

/**
 * @ClassName RoleService
 * @Author Jiu
 * @Create 2020/4/17 14:18
 **/
public interface RoleService extends IService<Role>{

    /**
     * 根据角色Id查询当前角色拥有的所有权限或菜单Id
     * @param roleId
     * @return
     */
    List<Integer> queryRolePermissionIdsByRid(Integer roleId);

    /**
     * 保存角色和菜单权限之间的关系
     * @param rid
     * @param ids
     */
    void saveRolePermission(Integer rid, Integer[] ids);
}
