package com.jiu.bus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiu.bus.domain.Goods;
import com.jiu.bus.domain.Inport;
import com.jiu.bus.mapper.GoodsMapper;
import com.jiu.bus.mapper.InportMapper;
import com.jiu.bus.service.InportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * @ClassName InportServiceImpl
 * @Author Jiu
 * @Create 2020/4/23 9:56
 **/
@Service
@Transactional
public class InportServiceImpl extends ServiceImpl<InportMapper, Inport> implements InportService{

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public boolean save(Inport entity) {
        //根据商品编号查询商品
        Goods goods=goodsMapper.selectById(entity.getGoodsid());
        goods.setNumber(goods.getNumber()+entity.getNumber());
        goodsMapper.updateById(goods);
        //保存商品进货信息
        return super.save(entity);
    }

    @Override
    public boolean updateById(Inport entity) {
        //根据进货id查询进货
        Inport inport=this.baseMapper.selectById(entity.getId());
        //根据商品Id查询商品
        Goods goods=this.goodsMapper.selectById(entity.getGoodsid());
        //保存到库存 当前库存-进货单修改之前的数量+修改之后的数量
        goods.setNumber(goods.getNumber()-inport.getNumber()+entity.getNumber());
        this.goodsMapper.updateById(goods);
        //更新进货单信息
        return super.updateById(entity);
    }

    @Override
    public boolean removeById(Serializable id) {
        //根据进货id查询进货信息
        Inport inport=this.baseMapper.selectById(id);
        //根据商品Id查询查询商品信息
        Goods goods=this.goodsMapper.selectById(inport.getGoodsid());
        //库存 当前库存-删除进货商品的数量
        goods.setNumber(goods.getNumber()-inport.getNumber());
        this.goodsMapper.updateById(goods);
        return super.removeById(id);
    }
}
