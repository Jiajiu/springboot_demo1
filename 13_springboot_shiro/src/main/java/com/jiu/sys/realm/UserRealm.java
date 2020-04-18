package com.jiu.sys.realm;

import com.jiu.sys.common.ActiveUser;
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

import java.util.List;

/**
 * @ClassName UserRealm
 * @Author Jiu
 * @Create 2020/4/4 20:42
 **/
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    PermissionService permissionService;

    @Override
    public String getName(){
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
        String username=token.getPrincipal().toString();
        //根据用户名查询用户
        User user=this.userService.queryUserByUserName(username);
        if(null!=user){
            //查询角色
            List<String> roles=this.roleService.queryRolesByUserId(user.getUserid());
            //查询权限
            List<String> permissions=this.permissionService.queryPermissionByUserId(user.getUserid());
            //构造ActiveUse
            ActiveUser activeUser=new ActiveUser(user,roles,permissions);
            //创建盐
            ByteSource credentialsSalt=ByteSource.Util.bytes(user.getUsername()+user.getAddress());
            SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(activeUser,user.getUserpwd(),credentialsSalt,this.getName());
            return info;
        }else {
            return null;
        }
    }
    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        ActiveUser activeUser= (ActiveUser) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();

        List<String> roles=activeUser.getRoles();
        List<String> permissions=activeUser.getPermissions();
        if(null!=roles && roles.size()>0){
            info.addRoles(roles);
        }
        if(null!=permissions && permissions.size()>0){
            info.addStringPermissions(permissions);
        }
        return info;
    }


}
