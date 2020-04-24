package com.jiu.bus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiu.bus.domain.Customer;
import com.jiu.bus.domain.Goods;
import com.jiu.bus.domain.Salesback;
import com.jiu.bus.service.CustomerService;
import com.jiu.bus.service.GoodsService;
import com.jiu.bus.service.SalesbackService;
import com.jiu.bus.vo.SalesBackVo;
import com.jiu.sys.common.DataGridView;
import com.jiu.sys.common.ResultObj;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @ClassName SalesBackController
 * @Author Jiu
 * @Create 2020/4/24 20:34
 **/
@RestController
@RequestMapping("/salesBack")
public class SalesBackController {

    @Autowired
    private SalesbackService salesbackService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private CustomerService customerService;

    @RequestMapping("addSalesBackGoods")
    public ResultObj addSalesBackGoods(Integer id ,Integer number,String remark){
        try{
            this.salesbackService.addSalesBack(id,number,remark);
            return ResultObj.OPERATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.OPERATE_FAIL;
        }
    }
    /**
     * 查询显示所有销售退货信息
     * @param salesBackVo
     * @return
     */
    @RequestMapping("loadAllSalesBack")
    public DataGridView loadAllSalesBack(SalesBackVo salesBackVo){
        IPage<Salesback> page=new Page<>(salesBackVo.getPage(),salesBackVo.getLimit());
        QueryWrapper<Salesback> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq(salesBackVo.getCustomerid()!=null&&salesBackVo.getCustomerid()!=0,"customerid",salesBackVo.getCustomerid());
        queryWrapper.eq(salesBackVo.getGoodsid()!=null&&salesBackVo.getGoodsid()!=0,"goodsid",salesBackVo.getGoodsid());
        queryWrapper.ge(salesBackVo.getStartTime()!=null, "salesbacktime", salesBackVo.getStartTime());
        queryWrapper.le(salesBackVo.getEndTime()!=null, "salesbacktime", salesBackVo.getEndTime());
        queryWrapper.like(StringUtils.isNotBlank(salesBackVo.getOperateperson()), "operateperson", salesBackVo.getOperateperson());
        queryWrapper.like(StringUtils.isNotBlank(salesBackVo.getRemark()), "remark", salesBackVo.getRemark());
        queryWrapper.orderByDesc("salesbacktime");
        this.salesbackService.page(page,queryWrapper);
        List<Salesback> records = page.getRecords();
        for (Salesback salesBack : records) {
            Customer customer=this.customerService.getById(salesBack.getCustomerid());
            if(null!=customer){
                salesBack.setCustomername(customer.getCustomername());
            }
            Goods goods=this.goodsService.getById(salesBack.getGoodsid());
            if(null!=goods){
                salesBack.setGoodsname(goods.getGoodsname());
                salesBack.setSize(goods.getSize());
            }
        }
        return new DataGridView(page.getTotal(),records);
    }

    /**
     * 根据id删除客户退货的信息（单个删除)
     * @param id
     * @return
     */
    @RequestMapping("deleteSaleBack")
    public ResultObj deleteSaleBack(Integer id){
        try{
            this.salesbackService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_FAIL;
        }
    }

    /**
     * 批量删除退货信息
     * @param salesBackVo
     * @return
     */
    @RequestMapping("batchDeleteSaleBack")
    public ResultObj batchDeleteSaleBack(SalesBackVo salesBackVo){
        try{
            Collection<Serializable> idList=new ArrayList<>();
            for (Integer id : salesBackVo.getIds()) {
                idList.add(id);
            }
            this.salesbackService.removeByIds(idList);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_FAIL;
        }
    }
}
