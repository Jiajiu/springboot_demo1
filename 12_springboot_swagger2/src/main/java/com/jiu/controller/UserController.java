package com.jiu.controller;

import com.jiu.common.ResultObj;
import com.jiu.domain.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName UserController
 * @Author Jiu
 * @Create 2020/4/4 18:24
 **/
@RestController
@RequestMapping("user")
@Api(value = "控制器",description = "用户管理")
public class UserController {

    /**
     * 全查询
     * @return
     */
    @ApiOperation(value = "用户查询",notes = "select")
    @GetMapping("queryAllUser")
    public List<User> queryAllUser(){
        List<User> list=new ArrayList<>();
        for (int i = 1; i <=5 ; i++) {
            list.add(new User(1,"小明"+i,"广州"+i,new Date()));
        }
        return list;
    }

    /**
     * 根据Id查询一个用户
     * @return
     */
    @ApiOperation(value = "根据Id查询用户")
    @GetMapping("queryUserById")
    public User queryUserById(Integer id){
        return new User(id,"小明","广州",new Date());
    }

    /**
     * 添加用户
     * @param user
     * @return
     */
    @ApiOperation(value = "添加用户")
    @PostMapping("addUser")
    public ResultObj addUser(@RequestBody User user){//@RequestBody验证前端所有数据非空的同时要求必须提交json格式的数据
        System.out.println(user);
        return new ResultObj(200,"添加成功");
    }

    /**
     * 添加用户2
     * @param user
     * @return
     */
    @ApiOperation(value = "添加用户2")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name="userId",value = "用户标识",required = true,paramType = "整数",dataType ="整数"),
            @ApiImplicitParam(name="userName",value = "用户名称",required = true,paramType = "字符串",dataType ="字符串")

            })
    @PostMapping("addUser2")
    public ResultObj addUser2(User user){
        System.out.println(user);
        return new ResultObj(200,"添加成功");
    }

    /**
     * 修改用户
     * @param user
     * @return
     */
    @ApiOperation(value = "修改用户")
    @PostMapping("updateUser")
    public ResultObj updateUser(@RequestBody User user){
        System.out.println(user);
        return new ResultObj(200,"修改成功");
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping("deleteUser")
    public ResultObj deleteUser(@RequestParam("userId")Integer id){
        System.out.println(id);
        return new ResultObj(200,"删除成功");
    }
}
