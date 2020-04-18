package com.jiu.sys.common;

/**
 * @ClassName Constant
 * @Author Jiu
 * @Create 2020/4/8 20:31
 **/
public interface Constant {

    /**
     * 状态码
     */
    public static final Integer OK=200;
    public static final Integer ERROR=-1;

    /**
     * 默认密码
     */
    public static final String USER_DEFAULT_PWD="123456";


    /**
     * 菜单权限类型
     */
    public static final String TYPE_MENU="menu";
    public static final String TYPE_PERMISSION="permission";

    /**
     * 可用状态
     */
    public static final Object AVAILABLE_TRUE=1;
    public static final Object AVAILABLE_FALSE=0;

    /**
     * 用户类型
     */
    public static final Integer USER_TYPE_SUPER=0;
    public static final Integer USER_TYPE_NORMAL=1;

    /**
     * 展开状态
     */
    public static final Integer OPEN_TRUE=1;
    public static final Integer OPEN_FALSE=0;
}
