package com.jiu.bus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiu.bus.domain.Provider;
import com.jiu.bus.mapper.ProviderMapper;
import com.jiu.bus.service.ProviderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;

/**
 * @ClassName ProviderServiceImpl
 * @Author Jiu
 * @Create 2020/4/21 21:38
 **/
@Service
@Transactional
public class ProviderServiceImpl extends ServiceImpl<ProviderMapper, Provider> implements ProviderService{


    @Override
    public boolean save(Provider entity) {
        return super.save(entity);
    }

    @Override
    public boolean updateById(Provider entity) {
        return super.updateById(entity);
    }

    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    public Provider getById(Serializable id) {
        return super.getById(id);
    }

    @Override
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        return super.removeByIds(idList);
    }

    //    @Override
//    public int batchInsert(List<Provider> list) {
//        return baseMapper.batchInsert(list);
//    }

}
