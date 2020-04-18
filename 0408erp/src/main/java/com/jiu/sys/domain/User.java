package com.jiu.sys.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName User
 * @Author Jiu
 * @Create 2020/4/8 21:18
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "name")
    private String name;

    @TableField(value = "loginname")
    private String loginname;

    @TableField(value = "address")
    private String address;

    @TableField(value = "sex")
    private Integer sex;

    @TableField(value = "remark")
    private String remark;

    @TableField(value = "pwd")
    private String pwd;

    @TableField(value = "deptid")
    private Integer deptid;

    @TableField(value = "hiredate")
    private Date hiredate;

    @TableField(value = "mgr")
    private Integer mgr;

    @TableField(value = "available")
    private Integer available;

    @TableField(value = "ordernum")
    private Integer ordernum;

    /**
     * 用户类型[0超级管理员1，管理员，2普通用户]
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 头像地址
     */
    @TableField(value = "imgpath")
    private String imgpath;

    @TableField(value = "salt")
    private String salt;

    /**
     * 部门名称
     */
    @TableField(exist = false)
    private String deptname;

    /**
     * 领导名称
     */
    @TableField(exist = false)
    private String leadername;

}