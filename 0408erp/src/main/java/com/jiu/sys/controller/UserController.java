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
import com.jiu.sys.domain.User;
import com.jiu.sys.service.DeptService;
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
}
