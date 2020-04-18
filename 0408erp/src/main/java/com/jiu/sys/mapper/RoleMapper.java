package com.jiu.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiu.sys.domain.Role;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName RoleMapper
 * @Author Jiu
 * @Create 2020/4/17 14:18
 **/
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 根据角色Id删除sys_role_permission
     * @param id
     */
    void deleteRolePermissionByRid(Serializable id);

    /**
     * 根据角色Id删除sys_role_user
     * @param id
     */
    void deleteRoleUserByRid(Serializable id);

    /**
     * 根据角色Id查询当前角色拥有的所有权限或菜单Id
     * @param roleId
     * @return
     */
    List<Integer> queryRolePermissionIdsByRid(Integer roleId);

    /**
     * 保存角色和菜单权限之间的关系
     * @param rid
     * @param pid
     */
    void saveRolePermission(@Param("rid") Integer rid, @Param("pid") Integer pid);

    /**
     * 根据用户id删除用户角色中间表的数据
     * @param id
     */
    void deleteRoleUserByUid(@Param("id")Serializable id);

    /**
     * 根据用户id查询角色并选中已拥有的角色
     * @param id
     * @return
     */
    List<Integer> queryUserRoleIdsByUid(Integer id);

    /**
     * 保存用户和角色之间的关系
     * @param uid
     * @param rid
     */
    void insertUserRole(@Param("uid") Integer uid, @Param("rid") Integer rid);
}