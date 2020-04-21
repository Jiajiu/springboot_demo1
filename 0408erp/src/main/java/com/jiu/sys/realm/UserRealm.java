package com.jiu.sys.realm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiu.sys.common.ActiveUser;
import com.jiu.sys.common.Constant;
import com.jiu.sys.domain.Permission;
import com.jiu.sys.domain.User;
import com.jiu.sys.service.PermissionService;
import com.jiu.sys.service.RoleService;
import com.jiu.sys.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ClassName UserRealm
 * @Author Jiu
 * @Create 2020/4/8 19:01
 **/
public class UserRealm extends AuthorizingRealm {

    @Autowired
    @Lazy
    private UserService userService;

    @Autowired
    @Lazy
    private PermissionService permissionService;

    @Autowired
    @Lazy
    private RoleService roleService;

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("loginname",token.getPrincipal().toString());
        User user = userService.getOne(queryWrapper);
        if(null!=user){
            ActiveUser activeUser=new ActiveUser();
            activeUser.setUser(user);

            //根据用户id查询precode
            //查询所有菜单
            QueryWrapper<Permission> qw=new QueryWrapper<>();
            //设置只能查询菜单
            qw.eq("type", Constant.TYPE_PERMISSION);
            qw.eq("available",Constant.AVAILABLE_TRUE);

            //根据用户id+权限去查询
            Integer userId=user.getId();
            //根据用户id查询角色
            List<Integer> currentUserRoleIds=roleService.queryUserRoleByUid(userId);
            //根据橘色id得到权限和菜单id
            Set<Integer> pids=new HashSet<>();
            for (Integer rid : currentUserRoleIds) {
                List<Integer> permissionIds = roleService.queryRolePermissionIdsByRid(rid);
                pids.addAll(permissionIds);
            }

            List<Permission> list=new ArrayList<>();
            //根据角色id查询权限
            if(pids.size()>0){
                qw.in("id",pids);
                list=permissionService.list(qw);
            }

            List<String> percodes=new ArrayList<>();
            for (Permission permission : list) {
                percodes.add(permission.getPercode());
            }

            //放到ActiveUser中
            activeUser.setPermissions(percodes);

            ByteSource credentialsSalt=ByteSource.Util.bytes(user.getSalt());
            SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(activeUser,user.getPwd(),credentialsSalt,this.getName());
            return info;
        }
        return null;
    }

    /**
     * 授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        ActiveUser activeUser= (ActiveUser) principals.getPrimaryPrincipal();
        User user=activeUser.getUser();
        List<String> permissions = activeUser.getPermissions();
        if(user.getType().equals(Constant.USER_TYPE_SUPER)){
            info.addStringPermission("*:*");
        }else {
            if(null!=permissions && permissions.size()>0 ){
                info.addStringPermissions(permissions);
            }
        }
        return info;
    }

}
