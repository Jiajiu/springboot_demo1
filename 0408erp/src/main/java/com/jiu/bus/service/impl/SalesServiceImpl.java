package com.jiu.bus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiu.bus.domain.Goods;
import com.jiu.bus.domain.Sales;
import com.jiu.bus.mapper.GoodsMapper;
import com.jiu.bus.mapper.SalesMapper;
import com.jiu.bus.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName SalesServiceImpl
 * @Author Jiu
 * @Create 2020/4/24 14:49
 **/
@Service
@Transactional
public class SalesServiceImpl extends ServiceImpl<SalesMapper, Sales> implements SalesService{

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public boolean save(Sales entity) {
        //根据商品编号查询商品
        Goods goods=goodsMapper.selectById(entity.getGoodsid());
        goods.setNumber(goods.getNumber()-entity.getNumber());
        goodsMapper.updateById(goods);
        //保存商品进货信息
        return super.save(entity);
    }
}
