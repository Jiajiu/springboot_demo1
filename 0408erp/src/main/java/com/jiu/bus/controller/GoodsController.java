package com.jiu.bus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiu.bus.domain.Goods;
import com.jiu.bus.domain.Provider;
import com.jiu.bus.service.GoodsService;
import com.jiu.bus.service.ProviderService;
import com.jiu.bus.vo.GoodsVo;
import com.jiu.sys.common.AppFileUtils;
import com.jiu.sys.common.Constant;
import com.jiu.sys.common.DataGridView;
import com.jiu.sys.common.ResultObj;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName GoodsController
 * @Author Jiu
 * @Create 2020/4/22 11:22
 **/

@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private ProviderService providerService;

    /**
     * 加载全部的商品
     * @param goodsVo
     * @return
     */
    @RequestMapping("loadAllGoods")
    public DataGridView loadAllGoods(GoodsVo goodsVo){
        IPage<Goods> page=new Page<>(goodsVo.getPage(),goodsVo.getLimit());
        QueryWrapper<Goods> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq(goodsVo.getProviderid()!=null&&goodsVo.getProviderid()!=0,"providerid",goodsVo.getProviderid());
        queryWrapper.like(StringUtils.isNotBlank(goodsVo.getGoodsname()),"goodsname",goodsVo.getGoodsname());
        queryWrapper.like(StringUtils.isNotBlank(goodsVo.getProductcode()),"productcode",goodsVo.getProductcode());
        queryWrapper.like(StringUtils.isNotBlank(goodsVo.getPromitcode()),"promitcode",goodsVo.getPromitcode());
        queryWrapper.like(StringUtils.isNotBlank(goodsVo.getDescription()),"description",goodsVo.getDescription());
        queryWrapper.like(StringUtils.isNotBlank(goodsVo.getSize()),"size",goodsVo.getSize());
        this.goodsService.page(page,queryWrapper);
        List<Goods> records = page.getRecords();
        for (Goods goods : records) {
            Provider provider=this.providerService.getById(goods.getProviderid());
            if(null!=provider){
                goods.setProvidername(provider.getProvidername());
            }
        }
        return new DataGridView(page.getTotal(),records);
    }

    /**
     * 添加商品
     * @param goodsVo
     * @return
     */
    @RequestMapping("addGoods")
    public ResultObj addGoods(GoodsVo goodsVo){
        try{
            if(goodsVo.getGoodsimg()!=null && goodsVo.getGoodsimg().endsWith(Constant.FILE_UPLOAD_TEMP)){
                String newName= AppFileUtils.reNameFile(goodsVo.getGoodsimg());
                goodsVo.setGoodsimg(newName);
            }
            this.goodsService.save(goodsVo);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.ADD_FAIL;
        }
    }

    /**
     * 修改商品
     * @param goodsVo
     * @return
     */
    @RequestMapping("updateGoods")
    public ResultObj updateGoods(GoodsVo goodsVo){
        try{
            //不是默认图片
            if(!(goodsVo.getGoodsimg()!=null && goodsVo.getGoodsimg().equals(Constant.DEFAULT_GOODS_IMG))){
                if(goodsVo.getGoodsimg().endsWith(Constant.FILE_UPLOAD_TEMP)){
                    String newName=AppFileUtils.reNameFile(goodsVo.getGoodsimg());
                    //删除 原有的图片
                    String oldPath=this.goodsService.getById(goodsVo.getId()).getGoodsimg();
                    //判断原有图片是否为默认图片
                    AppFileUtils.removeFileByPath(oldPath);

                }
            }
            this.goodsService.updateById(goodsVo);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.UPDATE_FAIL;
        }
    }

    @RequestMapping("deleteGoods")
    public ResultObj deleteGoods(Integer id,String goodsimg){
        try{
            //删除原文件
            AppFileUtils.removeFileByPath(goodsimg);
            this.goodsService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_FAIL;
        }
    }
}
