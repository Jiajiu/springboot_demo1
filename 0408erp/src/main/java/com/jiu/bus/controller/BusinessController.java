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
}
