package com.jiu.bus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiu.bus.domain.Goods;
import com.jiu.bus.domain.Outport;
import com.jiu.bus.domain.Provider;
import com.jiu.bus.service.GoodsService;
import com.jiu.bus.service.OutportService;
import com.jiu.bus.service.ProviderService;
import com.jiu.bus.vo.CustomerVo;
import com.jiu.bus.vo.OutportVo;
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
 * @ClassName OutportController
 * @Author Jiu
 * @Create 2020/4/23 17:21
 **/
@RestController
@RequestMapping("outport")
public class OutportController {

    @Autowired
    private OutportService outportService;

    @Autowired
    private ProviderService providerService;

    @Autowired
    private GoodsService goodsService;

    /**
     * 添加退货信息
     * @param id
     * @param number
     * @param remark
     * @return
     */
    @RequestMapping("addOutport")
    public ResultObj addOutport(Integer id,Integer number,String remark){
        try{
            this.outportService.addOutPort(id,number,remark);
            return ResultObj.OPERATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.OPERATE_FAIL;
        }
    }

    /**
     * 查询显示所有退货信息
     * @param outportVo
     * @return
     */
    @RequestMapping("loadAllOutport")
    public DataGridView loadAllOutport(OutportVo outportVo){
        IPage<Outport> page=new Page<>(outportVo.getPage(),outportVo.getLimit());
        QueryWrapper<Outport> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq(outportVo.getProviderid()!=null&&outportVo.getProviderid()!=0,"providerid",outportVo.getProviderid());
        queryWrapper.eq(outportVo.getGoodsid()!=null&&outportVo.getGoodsid()!=0,"goodsid",outportVo.getGoodsid());
        queryWrapper.ge(outportVo.getStartTime()!=null, "outporttime", outportVo.getStartTime());
        queryWrapper.le(outportVo.getEndTime()!=null, "outporttime", outportVo.getEndTime());
        queryWrapper.like(StringUtils.isNotBlank(outportVo.getOperateperson()), "operateperson", outportVo.getOperateperson());
        queryWrapper.like(StringUtils.isNotBlank(outportVo.getRemark()), "remark", outportVo.getRemark());
        queryWrapper.orderByDesc("outporttime");
        this.outportService.page(page,queryWrapper);
        List<Outport> records = page.getRecords();
        for (Outport outport : records) {
            Provider provider=this.providerService.getById(outport.getProviderid());
            if(null!=provider){
                outport.setProvidername(provider.getProvidername());
            }
            Goods goods=this.goodsService.getById(outport.getGoodsid());
            if(null!=goods){
                outport.setGoodsname(goods.getGoodsname());
                outport.setSize(goods.getSize());
            }
        }
        return new DataGridView(page.getTotal(),records);
    }

    /**
     * 根据id删除退货信息（单个删除）
     * @param id
     * @return
     */
    @RequestMapping("deleteOutport")
    public ResultObj deleteOutport(Integer id){
        try{
            this.outportService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_FAIL;
        }
    }

    /**
     * 批量删除退货信息
     * @param outportVo
     * @return
     */
    @RequestMapping("batchDeleteOutport")
    public ResultObj batchDeleteOutport(OutportVo outportVo){
        try{
            Collection<Serializable> idList=new ArrayList<>();
            for (Integer id : outportVo.getIds()) {
                idList.add(id);
            }
            this.outportService.removeByIds(idList);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_FAIL;
        }
    }
}
