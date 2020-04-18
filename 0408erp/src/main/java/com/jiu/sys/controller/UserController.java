package com.jiu.sys.controller;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiu.sys.common.Constant;
import com.jiu.sys.common.DataGridView;
import com.jiu.sys.common.PinyinUtils;
import com.jiu.sys.common.ResultObj;
import com.jiu.sys.domain.Dept;
import com.jiu.sys.domain.Role;
import com.jiu.sys.domain.User;
import com.jiu.sys.service.DeptService;
import com.jiu.sys.service.RoleService;
import com.jiu.sys.service.UserService;
import com.jiu.sys.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName UserController
 * @Author Jiu
 * @Create 2020/4/8 18:57
 **/

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private DeptService deptService;

    @Autowired
    private RoleService roleService;

    /**
     * 查询全部用户
     * @param userVo
     * @return
     */
    @RequestMapping("loadAllUser")
    public DataGridView loadAllUser(UserVo userVo){
        IPage<User> page=new Page<>(userVo.getPage(),userVo.getLimit());
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(userVo.getName()),"loginname",userVo.getName()).or().eq(StringUtils.isNotBlank(userVo.getName()),"name",userVo.getName());
        queryWrapper.like(StringUtils.isNotBlank(userVo.getAddress()),"address",userVo.getAddress());
        queryWrapper.eq("type", Constant.USER_TYPE_NORMAL);
        queryWrapper.eq(userVo.getDeptid()!=null,"deptid",userVo.getDeptid());
        this.userService.page(page,queryWrapper);
        List<User> list=page.getRecords();
        for (User user : list) {
            Integer deptid = user.getDeptid();
            if(deptid!=null){
                Dept one = deptService.getById(deptid);
                user.setDeptname(one.getTitle());
            }
            Integer mgr = user.getMgr();
            if(mgr!=null){
                User one = this.userService.getById(mgr);
                user.setLeadername(one.getName());
            }
        }
        return new DataGridView(page.getTotal(),list);
    }

    /**
     * 加载最大的排序码
     * @return
     */
    @RequestMapping("loadUserMaxOrderNum")
    public Map<String,Object> loadUserMaxOrderNum(){
        Map<String, Object> map=new HashMap<>();
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        IPage<User> page=new Page<>(1,1);
        queryWrapper.orderByDesc("ordernum");
        List<User> list = this.userService.page(page, queryWrapper).getRecords();
        if(list.size()>0){
            map.put("value",list.get(0).getOrdernum()+1);
        }else {
            map.put("value",1);
        }
        return map;
    }

    /**
     * 根据部门id查询用户
     * @param deptid
     * @return
     */
    @RequestMapping("loadUsersByDeptId")
    public DataGridView loadUsersByDeptId(Integer deptid){
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq(deptid!=null,"deptid",deptid);
        queryWrapper.eq("available",Constant.AVAILABLE_TRUE);
        queryWrapper.eq("type",Constant.USER_TYPE_NORMAL);
        List<User> list = this.userService.list(queryWrapper);
        return new DataGridView(list);
    }

    /**
     * 把用户名转成拼音
     * @param username
     * @return
     */
    @RequestMapping("changeChineseToPinyin")
    public Map<String,Object> changeChineseToPinyin(String username){
        Map<String,Object> map=new HashMap<>();
        if(null!=username){
            map.put("value", PinyinUtils.getPingYin(username));
        }else{
            map.put("value","");
        }
        return map;
    }

    @RequestMapping("addUser")
    public ResultObj addUser(UserVo userVo){
        try{
            //设置类型
            userVo.setType(Constant.USER_TYPE_NORMAL);
            userVo.setHiredate(new Date());
            String salt= IdUtil.simpleUUID().toUpperCase();
            userVo.setSalt(salt);
            //设置密码
            userVo.setPwd(new Md5Hash(Constant.USER_DEFAULT_PWD,salt,2).toString());
            this.userService.save(userVo);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.ADD_FAIL;
        }
    }

    /**
     * 根据用户id查询一个用户
     * @param id
     * @return
     */
    @RequestMapping("loadUserById")
    public DataGridView loadUserById(Integer id){
        return new DataGridView(this.userService.getById(id));
    }

    /**
     * 修改用户
     * @param userVo
     * @return
     */
    @RequestMapping("updateUser")
    public ResultObj updateUser(UserVo userVo){
        try{
            this.userService.updateById(userVo);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.UPDATE_FAIL;
        }
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @RequestMapping("deleteUser")
    public ResultObj deleteUser(Integer id){
        try{
            this.userService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_FAIL;
        }
    }

    /**
     * 重置用户密码
     * @param id
     * @return
     */
    @RequestMapping("resetPwd")
    public ResultObj resetPwd(Integer id){
        try{
            User user=new User();
            user.setId(id);
            String salt= IdUtil.simpleUUID().toUpperCase();
            user.setSalt(salt);
            //设置密码
            user.setPwd(new Md5Hash(Constant.USER_DEFAULT_PWD,salt,2).toString());
            this.userService.updateById(user);
            return ResultObj.RESET_PWD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.RESET_PWD_FAIL;
        }
    }

    /**
     * 根据用户id查询角色并选中已拥有的角色
     * @param id
     * @return
     */
    @RequestMapping("initRoleByUserId")
    public DataGridView initRoleByUserId(Integer id){
        //1.查询所有可用的角色
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("available",Constant.AVAILABLE_TRUE);
        List<Map<String, Object>> listMaps = this.roleService.listMaps(queryWrapper);
        //2.查询当前用户拥有的角色Id
       List<Integer> currentUserRoleIds= this.roleService.queryUserRoleByUid(id);
       for (Map<String, Object> map : listMaps) {
            Boolean LAY_CHECKED=false;
            Integer roleId= (Integer) map.get("id");
            for (Integer rid : currentUserRoleIds) {
                if(rid.equals(roleId)){
                    LAY_CHECKED=true;
                    break;
                }
           }
            map.put("LAY_CHECKED",LAY_CHECKED);
        }
        return new DataGridView((long)listMaps.size(),listMaps);
    }

    /**
     * 保存用户和角色之间的关系
     * @param uid
     * @param ids
     * @return
     */
    @RequestMapping("saveUserRole")
    public ResultObj saveUserRole(Integer uid,Integer[] ids){
        try{
            this.userService.saveUserRole(uid,ids);
            return ResultObj.DISPATCH_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DISPATCH_FAIL;
        }
    }
}
