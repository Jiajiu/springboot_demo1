package com.jiu.sys.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName ResultObj
 * @Author Jiu
 * @Create 2020/4/8 20:30
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultObj {

    public static final ResultObj LOGIN_SUCCESS=new ResultObj(Constant.OK,"登录成功");
    public static final ResultObj LOGIN_ERROR_PASS=new ResultObj(Constant.ERROR,"登录失败，用户名或密码错误");
    public static final ResultObj LOGIN_ERROR_CODE=new ResultObj(Constant.ERROR,"登录事变，请输入正确的验证码");

    public static final ResultObj UPDATE_SUCCESS=new ResultObj(Constant.OK,"更新成功");
    public static final ResultObj UPDATE_FAIL=new ResultObj(Constant.ERROR,"更新失败");

    public static final ResultObj ADD_SUCCESS=new ResultObj(Constant.OK,"添加成功");
    public static final ResultObj ADD_FAIL=new ResultObj(Constant.ERROR,"添加失败");

    public static final ResultObj DELETE_SUCCESS=new ResultObj(Constant.OK,"删除成功");
    public static final ResultObj DELETE_FAIL=new ResultObj(Constant.ERROR,"删除失败");

    public static final ResultObj RESET_PWD_SUCCESS=new ResultObj(Constant.OK,"重置密码成功");
    public static final ResultObj RESET_PWD_FAIL=new ResultObj(Constant.ERROR,"重置密码失败");

    public static final ResultObj DISPATCH_SUCCESS=new ResultObj(Constant.OK,"分配成功");
    public static final ResultObj DISPATCH_FAIL=new ResultObj(Constant.ERROR,"分配失败");

    private Integer code;
    private String msg;
}
