package com.jiu.sys.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Permission
 * @Author Jiu
 * @Create 2020/4/9 11:13
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_permission")
public class Permission implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "pid")
    private Integer pid;

    /**
     * 权限类型[menu/permission]
     */
    @TableField(value = "type")
    private String type;

    @TableField(value = "title")
    private String title;

    /**
     * 权限编码[只有type= permission才有  user:view]
     */
    @TableField(value = "percode")
    private String percode;

    @TableField(value = "icon")
    private String icon;

    @TableField(value = "href")
    private String href;

    @TableField(value = "target")
    private String target;

    @TableField(value = "open")
    private Integer open;

    @TableField(value = "ordernum")
    private Integer ordernum;

    /**
     * 状态【0不可用1可用】
     */
    @TableField(value = "available")
    private Integer available;

    private static final long serialVersionUID = 1L;
}