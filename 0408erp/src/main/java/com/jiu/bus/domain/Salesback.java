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
 * @ClassName Salesback
 * @Author Jiu
 * @Create 2020/4/24 20:32
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "bus_salesback")
public class Salesback implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "customerid")
    private Integer customerid;

    @TableField(value = "paytype")
    private String paytype;

    @TableField(value = "salesbacktime")
    private Date salesbacktime;

    @TableField(value = "salebackprice")
    private Double salebackprice;

    @TableField(value = "operateperson")
    private String operateperson;

    @TableField(value = "number")
    private Integer number;

    @TableField(value = "remark")
    private String remark;

    @TableField(value = "goodsid")
    private Integer goodsid;

    @TableField(exist = false)
    private String customername;
    @TableField(exist = false)
    private String goodsname;
    @TableField(exist = false)
    private String size;
    private static final long serialVersionUID = 1L;
}