package com.jiu.bus.domain;

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
 * @ClassName Inport
 * @Author Jiu
 * @Create 2020/4/23 9:56
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "bus_inport")
public class Inport implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "paytype")
    private String paytype;

    @TableField(value = "inporttime")
    private Date inporttime;

    @TableField(value = "operateperson")
    private String operateperson;

    @TableField(value = "number")
    private Integer number;

    @TableField(value = "remark")
    private String remark;

    @TableField(value = "inportprice")
    private Double inportprice;

    @TableField(value = "providerid")
    private Integer providerid;

    @TableField(value = "goodsid")
    private Integer goodsid;

    @TableField(exist = false)
    private String providername;
    @TableField(exist = false)
    private String goodsname;
    @TableField(exist = false)
    private String size;

}