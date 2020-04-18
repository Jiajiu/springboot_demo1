package com.jiu.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiu.sys.domain.Role;
import com.jiu.sys.mapper.RoleMapper;
import com.jiu.sys.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName RoleServiceImpl
 * @Author Jiu
 * @Create 2020/4/17 14:18
 **/
@Service
@Transactional
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService{

    @Override
    public boolean removeById(Serializable id) {
        //根据角色Id删除sys_role_permission
        this.getBaseMapper().deleteRolePermissionByRid(id);
        //根据角色Id删除sys_role_user
        this.getBaseMapper().deleteRoleUserByRid(id);
        return super.removeById(id);
    }

    /**
     * 根据角色Id查询当前角色拥有的所有权限或菜单Id
     * @param roleId
     * @return
     */
    @Override
    public List<Integer> queryRolePermissionIdsByRid(Integer roleId) {
        return this.getBaseMapper().queryRolePermissionIdsByRid(roleId);
    }

    /**
     * 保存角色和菜单权限之间的关系
     * @param rid
     * @param ids
     */
    @Override
    public void saveRolePermission(Integer rid, Integer[] ids) {
        RoleMapper roleMapper=this.getBaseMapper();
        //先根据rid删除sys_role_permission中的数据，再插入新数据
        roleMapper.deleteRolePermissionByRid(rid);
        if(ids!=null&&ids.length>0){
            for (Integer pid : ids) {
                roleMapper.saveRolePermission(rid,pid);
            }
        }

    }
}
