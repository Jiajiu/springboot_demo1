package com.jiu.bus.service;

import com.jiu.bus.domain.Salesback;
import com.baomidou.mybatisplus.extension.service.IService;
    /**
 * @ClassName SalesbackService
 * @Author Jiu
 * @Create 2020/4/24 20:32
 **/
public interface SalesbackService extends IService<Salesback>{

        void addSalesBack(Integer id, Integer number, String remark);
    }
