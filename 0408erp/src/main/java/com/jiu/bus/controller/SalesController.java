package com.jiu.bus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiu.bus.domain.Customer;
import com.jiu.bus.domain.Goods;
import com.jiu.bus.domain.Sales;
import com.jiu.bus.service.CustomerService;
import com.jiu.bus.service.GoodsService;
import com.jiu.bus.service.SalesService;
import com.jiu.bus.vo.SalesVo;
import com.jiu.sys.common.DataGridView;
import com.jiu.sys.common.ResultObj;
import com.jiu.sys.common.WebUtils;
import com.jiu.sys.domain.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @ClassName SalesController
 * @Author Jiu
 * @Create 2020/4/24 14:52
 **/
@RestController
@RequestMapping("/sales")
public class SalesController {

    @Autowired
    private SalesService salesService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private GoodsService goodsService;

    /**
     * 加载销售的全部商品
     * @param salesVo
     * @return
     */
    @RequestMapping("loadAllSales")
    public DataGridView loadAllSales(SalesVo salesVo){
        IPage<Sales> page=new Page<>(salesVo.getPage(),salesVo.getLimit());
        QueryWrapper<Sales> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq(salesVo.getCustomerid()!=null&&salesVo.getCustomerid()!=0,"customerid",salesVo.getCustomerid());
        queryWrapper.eq(salesVo.getGoodsid()!=null&&salesVo.getGoodsid()!=0,"goodsid",salesVo.getGoodsid());
        queryWrapper.ge(salesVo.getStartTime()!=null,"salestime",salesVo.getStartTime());
        queryWrapper.le(salesVo.getEndTime()!=null,"salestime",salesVo.getEndTime());
        queryWrapper.like(StringUtils.isNotBlank(salesVo.getOperateperson()),"operateperson",salesVo.getOperateperson());
        queryWrapper.like(StringUtils.isNotBlank(salesVo.getRemark()),"remark",salesVo.getRemark());
        queryWrapper.orderByDesc("salestime");
        this.salesService.page(page,queryWrapper);
        List<Sales> records = page.getRecords();
        for (Sales sales : records) {
            Customer customer=this.customerService.getById(sales.getCustomerid());
            if(null!=customer){
                sales.setCustomername(customer.getCustomername());
            }
            Goods goods=this.goodsService.getById(sales.getGoodsid());
            if(null!=goods){
                sales.setGoodsname(goods.getGoodsname());
                sales.setSize(goods.getSize());
            }
        }
        return new DataGridView(page.getTotal(),records);
    }

    /**
     * 添加销售信息
     * @param salesVo
     * @return
     */
    @RequestMapping("addSales")
    public ResultObj addSales(SalesVo salesVo){
        try{
            salesVo.setSalestime(new Date());
            User user= (User) WebUtils.getSession().getAttribute("user");
            salesVo.setOperateperson(user.getName());
            this.salesService.save(salesVo);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.ADD_FAIL;
        }
    }

    /**
     * 删除销售信息
     * @param id
     * @return
     */
    @RequestMapping("deleteSales")
    public ResultObj deleteSales(Integer id){
        try{
            this.salesService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_FAIL;
        }
    }
}
