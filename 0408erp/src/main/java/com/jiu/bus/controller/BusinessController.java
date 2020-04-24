package com.jiu.bus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName BusinessController
 * @Author Jiu
 * @Create 2020/4/21 17:09
 **/
@Controller
@RequestMapping("/bus")
public class BusinessController {

    /**
     * 跳转到客户管理
     * @return
     */
    @RequestMapping("toCustomerManager")
    public String toCustomerManager(){
        return "business/customer/customerManager";
    }

    /**
     * 跳转到供应商管理
     * @return
     */
    @RequestMapping("toProviderManager")
    public String toProvicderManager(){
        return "business/provider/providerManager";
    }

    /**
     * 跳转到商品管理
     * @return
     */
    @RequestMapping("toGoodsManager")
    public String toGoodsManager(){
        return "business/goods/goodsManager";
    }

    /**
     * 跳转到商品进货管理
     * @return
     */
    @RequestMapping("toInportManager")
    public String toInportManager(){
        return "business/inport/inportManager";
    }

    /**
     * 跳转到退货查询管理
     * @return
     */
    @RequestMapping("toOutportManager")
    public String toOutportManager(){
        return "business/outport/outportManager";
    }

    /**
     * 跳转到商品销售管理
     * @return
     */
    @RequestMapping("toSalesManager")
    public String toSalesManager(){
        return "business/sales/salesManager";
    }

    /**
     * 跳转到销售退货查询管理
     * @return
     */
    @RequestMapping("toSalesBackManager")
    public String toSalesBackManager(){
        return "business/salesback/salesBackManager";
    }
}
