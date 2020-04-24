package com.jiu.bus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiu.bus.domain.Goods;
import com.jiu.bus.domain.Sales;
import com.jiu.bus.domain.Salesback;
import com.jiu.bus.mapper.GoodsMapper;
import com.jiu.bus.mapper.SalesMapper;
import com.jiu.bus.mapper.SalesbackMapper;
import com.jiu.bus.service.SalesbackService;
import com.jiu.sys.common.WebUtils;
import com.jiu.sys.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @ClassName SalesbackServiceImpl
 * @Author Jiu
 * @Create 2020/4/24 20:32
 **/
@Service
@Transactional
public class SalesbackServiceImpl extends ServiceImpl<SalesbackMapper, Salesback> implements SalesbackService{

    @Autowired
    private SalesMapper salesMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public void addSalesBack(Integer id, Integer number, String remark) {
        //1.根据销售货单Id查询进货单信息
        Sales sales = this.salesMapper.selectById(id);
        //得到退货后剩余的销售货数量
        sales.setNumber(sales.getNumber()-number);
        this.salesMapper.updateById(sales);
        //2.根据商品Id查询商品信息
        Goods goods = this.goodsMapper.selectById(sales.getGoodsid());
        //3.修改商品库存
        goods.setNumber(goods.getNumber()+number);
        //4.更新库存信息
        this.goodsMapper.updateById(goods);
        //5.添加退货单信息
        Salesback entity=new Salesback();
        entity.setGoodsid(sales.getGoodsid());
        entity.setNumber(number);
        User user= (User) WebUtils.getSession().getAttribute("user");
        entity.setOperateperson(user.getName());
        entity.setSalebackprice(sales.getSaleprice());
        entity.setSalesbacktime(new Date());
        entity.setPaytype(sales.getPaytype());
        entity.setCustomerid(sales.getCustomerid());
        entity.setRemark(remark);
        this.getBaseMapper().insert(entity);
    }
}
