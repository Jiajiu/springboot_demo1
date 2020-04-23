package com.jiu.bus.service;

import com.jiu.bus.domain.Outport;
import com.baomidou.mybatisplus.extension.service.IService;
    /**
 * @ClassName OutportService
 * @Author Jiu
 * @Create 2020/4/23 9:56
 **/
public interface OutportService extends IService<Outport>{

        /**
         * 添加退货信息
         * @param id    进货单id
         * @param number    退货数量
         * @param remark    退货备注
         */
        void addOutPort(Integer id, Integer number, String remark);
    }
