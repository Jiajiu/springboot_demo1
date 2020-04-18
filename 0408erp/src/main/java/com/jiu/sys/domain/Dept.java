package com.jiu.sys.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Dept
 * @Author Jiu
 * @Create 2020/4/11 21:35
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_dept")
public class Dept implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "pid")
    private Integer pid;

    @TableField(value = "title")
    private String title;

    @TableField(value = "open")
    private Integer open;

    @TableField(value = "remark")
    private String remark;

    @TableField(value = "address")
    private String address;

    /**
     * 状态【0不可用1可用】
     */
    @TableField(value = "available")
    private Integer available;

    /**
     * 排序码【为了调事显示顺序】
     */
    @TableField(value = "ordernum")
    private Integer ordernum;

    @TableField(value = "createtime")
    private Date createtime;

    private static final long serialVersionUID = 1L;
}