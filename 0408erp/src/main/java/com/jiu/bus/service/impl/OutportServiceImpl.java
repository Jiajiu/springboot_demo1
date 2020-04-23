package com.jiu.bus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiu.bus.domain.Goods;
import com.jiu.bus.domain.Inport;
import com.jiu.bus.domain.Outport;
import com.jiu.bus.mapper.GoodsMapper;
import com.jiu.bus.mapper.InportMapper;
import com.jiu.bus.mapper.OutportMapper;
import com.jiu.bus.service.OutportService;
import com.jiu.sys.common.WebUtils;
import com.jiu.sys.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * @ClassName OutportServiceImpl
 * @Author Jiu
 * @Create 2020/4/23 9:56
 **/
@Service
@Transactional
public class OutportServiceImpl extends ServiceImpl<OutportMapper, Outport> implements OutportService{

    @Autowired
    private InportMapper inportMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public void addOutPort(Integer id, Integer number, String remark) {
        //1.根据进货单Id查询进货单信息
        Inport inport = this.inportMapper.selectById(id);
        //得到退货后剩余的进货数量
        inport.setNumber(inport.getNumber()-number);
        this.inportMapper.updateById(inport);
        //2.根据商品Id查询商品信息
        Goods goods = this.goodsMapper.selectById(inport.getGoodsid());
        //3.修改商品库存
        goods.setNumber(goods.getNumber()-number);
        //4.更新库存信息
        this.goodsMapper.updateById(goods);
        //5.添加退货单信息
        Outport entity=new Outport();
        entity.setGoodsid(inport.getGoodsid());
        entity.setNumber(number);
        User user= (User) WebUtils.getSession().getAttribute("user");
        entity.setOperateperson(user.getName());
        entity.setOutportprice(inport.getInportprice());
        entity.setOutporttime(new Date());
        entity.setPaytype(inport.getPaytype());
        entity.setProviderid(inport.getProviderid());
        entity.setRemark(remark);
        this.getBaseMapper().insert(entity);
    }

    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        return super.removeByIds(idList);
    }

}
