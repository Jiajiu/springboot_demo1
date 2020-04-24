package com.jiu.sys.cache;

import com.jiu.bus.domain.Customer;
import com.jiu.bus.domain.Goods;
import com.jiu.bus.domain.Provider;
import com.jiu.bus.mapper.CustomerMapper;
import com.jiu.bus.mapper.GoodsMapper;
import com.jiu.bus.mapper.ProviderMapper;
import com.jiu.sys.common.SpringUtils;
import com.jiu.sys.domain.Dept;
import com.jiu.sys.domain.User;
import com.jiu.sys.mapper.DeptMapper;
import com.jiu.sys.mapper.UserMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName CachePool
 * @Author Jiu
 * @Create 2020/4/24 13:30
 **/
public class CachePool {

    /**
     * 所有的缓存数据放到这哥CACHE_CONTAINER,类似于redis
     */
    public static volatile Map<String,Object> CACHE_CONTAINER=new HashMap<>();

    /**
     * 根据key删除缓存
     * @param key
     */
    public static void removeCacheByKey(String key){
        if(CACHE_CONTAINER.containsKey(key)){
            CACHE_CONTAINER.remove(key);
        }
    }

    /**
     * 清空全部缓存
     */
    public static void removeAll(){
        CACHE_CONTAINER.clear();
    }

    /**
     * 同步缓存数据
     */
    public static void syncCacheData(){
        //同步部门数据
        DeptMapper deptMapper = SpringUtils.getBean(DeptMapper.class);
        List<Dept> deptList = deptMapper.selectList(null);
        for (Dept dept : deptList) {
            CACHE_CONTAINER.put("dept:"+dept.getId(),dept);
        }
        //同步用户数据
        UserMapper userMapper = SpringUtils.getBean(UserMapper.class);
        List<User> userList = userMapper.selectList(null);
        for (User user : userList) {
            CACHE_CONTAINER.put("user:"+user.getId(),user);
        }
        //同步客户数据
        CustomerMapper customerMapper = SpringUtils.getBean(CustomerMapper.class);
        List<Customer> customerList = customerMapper.selectList(null);
        for (Customer customer : customerList) {
            CACHE_CONTAINER.put("customer:"+customer.getId(),customer);
        }
        //同步供应商数据
        ProviderMapper providerMapper = SpringUtils.getBean(ProviderMapper.class);
        List<Provider> providerList = providerMapper.selectList(null);
        for (Provider provider : providerList) {
            CACHE_CONTAINER.put("provider:"+provider.getId(),provider);
        }
        //同步商品数据
        GoodsMapper goodsMapper = SpringUtils.getBean(GoodsMapper.class);
        List<Goods> goodsList = goodsMapper.selectList(null);
        for (Goods goods : goodsList) {
            CACHE_CONTAINER.put("goods:"+goods.getId(),goods);
        }
    }
}
