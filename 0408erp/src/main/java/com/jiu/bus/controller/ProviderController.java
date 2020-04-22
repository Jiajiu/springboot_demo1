package com.jiu.bus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiu.bus.domain.Provider;
import com.jiu.bus.service.ProviderService;
import com.jiu.bus.vo.ProviderVo;
import com.jiu.sys.common.Constant;
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
 * @ClassName ProviderController
 * @Author Jiu
 * @Create 2020/4/21 21:42
 **/
@RestController
@RequestMapping("/provider")
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    /**
     * 加载全部供应商
     * @param providerVo
     * @return
     */
    @RequestMapping("loadAllProvider")
    public DataGridView loadAllProvider(ProviderVo providerVo){
        IPage<Provider> page=new Page<>(providerVo.getPage(),providerVo.getLimit());
        QueryWrapper<Provider> queryWrapper=new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(providerVo.getProvidername()),"providername",providerVo.getProvidername());
        queryWrapper.like(StringUtils.isNotBlank(providerVo.getPhone()),"phone",providerVo.getPhone());
        queryWrapper.like(StringUtils.isNotBlank(providerVo.getConnectionperson()),"connectionperson",providerVo.getConnectionperson());
        this.providerService.page(page,queryWrapper);
        return new DataGridView(page.getTotal(),page.getRecords());
    }

    /**
     * 添加供应商
     * @param providerVo
     * @return
     */
    @RequestMapping("addProvider")
    public ResultObj addProvider(ProviderVo providerVo){
        try{
            this.providerService.save(providerVo);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.ADD_FAIL;
        }
    }


    /**
     * 修改供应商
     * @param providerVo
     * @return
     */
    @RequestMapping("updateProvider")
    public ResultObj updateProvider(ProviderVo providerVo){
        try{
            this.providerService.updateById(providerVo);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.UPDATE_FAIL;
        }
    }

    /**
     * 根据id删除供应商（单个删除）
     * @param id
     * @return
     */
    @RequestMapping("deleteProvider")
    public ResultObj deleteProvider(Integer id){
        try{
            this.providerService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_FAIL;
        }
    }

    /**
     * 批量删除供应商
     * @param providerVo
     * @return
     */
    @RequestMapping("batchDeleteProvider")
    public ResultObj batchDeleteProvider(ProviderVo providerVo){
        try{
            Collection<Serializable> idList=new ArrayList<>();
            for (Integer id : providerVo.getIds()) {
                idList.add(id);
            }
            this.providerService.removeByIds(idList);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_FAIL;
        }
    }

    /**
     * 加载查询条件供应商的下拉列表
     * @return
     */
    @RequestMapping("loadAllProviderForSelect")
    public DataGridView loadAllProviderForSelect(){
        QueryWrapper<Provider> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("available", Constant.AVAILABLE_TRUE);
        List<Provider> list = this.providerService.list(queryWrapper);
        return new DataGridView(list);
    }

}
