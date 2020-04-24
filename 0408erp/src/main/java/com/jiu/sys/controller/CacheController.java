package com.jiu.sys.controller;

import com.jiu.sys.cache.CachePool;
import com.jiu.sys.common.CacheBean;
import com.jiu.sys.common.DataGridView;
import com.jiu.sys.common.ResultObj;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName CacheController
 * @Author Jiu
 * @Create 2020/4/24 13:43
 **/
@RestController
@RequestMapping("/cache")
public class CacheController {

    private static volatile Map<String,Object> CACHE_CONTAINER= CachePool.CACHE_CONTAINER;

    /**
     * 查询所有的缓存
     * @return
     */
    @RequestMapping("loadAllCache")
    public DataGridView loadAllCache(){
        List<CacheBean> list=new ArrayList<>();
        Set<Map.Entry<String, Object>> entries = CACHE_CONTAINER.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            list.add(new CacheBean(entry.getKey(),entry.getValue()));
        }
        return new DataGridView(list);
    }

    /**
     * 删除缓存（单个）
     * @param key
     * @return
     */
    @RequestMapping("deleteCache")
    public ResultObj deleteCache(String key){
        CachePool.removeCacheByKey(key);
        return ResultObj.DELETE_SUCCESS;
    }

    /**
     * 清空所有缓存
     * @return
     */
    @RequestMapping("removeAllCache")
    public ResultObj removeAllCache(){
        CachePool.removeAll();
        return ResultObj.DELETE_SUCCESS;
    }

    /**
     * 同步缓存数据
     * @return
     */
    @RequestMapping("syncCache")
    public ResultObj syncCache(){
        CachePool.syncCacheData();
        return ResultObj.OPERATE_SUCCESS;
    }
}
