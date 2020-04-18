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
 * @ClassName Notice
 * @Author Jiu
 * @Create 2020/4/11 11:39
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_notice")
public class Notice implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "title")
    private String title;

    @TableField(value = "content")
    private String content;

    @TableField(value = "createtime")
    private Date createtime;

    @TableField(value = "opername")
    private String opername;

    private static final long serialVersionUID = 1L;
}