package com.jiu.bus.cache;

import com.jiu.bus.domain.Customer;
import com.jiu.bus.domain.Provider;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName BusinessCacheApsect
 * @Author Jiu
 * @Create 2020/4/21 21:00
 **/

@Aspect
@Component
@EnableAspectJAutoProxy
public class BusinessCacheApsect {

    /**
     * 日志输出
     */
    private Log log= LogFactory.getLog(BusinessCacheApsect.class);

    /**
     * 声明一个缓存容器
     */
    private static Map<String,Object> CACHE_CONTAINER=new HashMap<>();

    public static Map<String, Object> getCacheContainer() {
        return CACHE_CONTAINER;
    }

    /**
     * 声明切面表达式
     */
    public static final String POINTCUT_CUSTOMER_ADD="execution(* com.jiu.bus.service.impl.CustomerServiceImpl.save(..))";
    public static final String POINTCUT_CUSTOMER_UPDATE="execution(* com.jiu.bus.service.impl.CustomerServiceImpl.updateById(..))";
    public static final String POINTCUT_CUSTOMER_GET="execution(* com.jiu.bus.service.impl.CustomerServiceImpl.getById(..))";
    public static final String POINTCUT_CUSTOMER_DELETE="execution(* com.jiu.bus.service.impl.CustomerServiceImpl.removeById(..))";
    public static final String POINTCUT_CUSTOMER_BATCHDELETE="execution(* com.jiu.bus.service.impl.CustomerServiceImpl.removeByIds(..))";

    public static final String CACHE_CUSTOMER_PREFIX="customer";

    /**
     * 客户添加切入
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(value = POINTCUT_CUSTOMER_ADD)
    public Object cacheCustomerAdd(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        Customer object = (Customer) joinPoint.getArgs()[0];
        Boolean res = (Boolean) joinPoint.proceed();
        if(res){
            CACHE_CONTAINER.put(CACHE_CUSTOMER_PREFIX+object.getId(),object);
        }
        return res;
    }

    /**
     * 查询切入
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(value = POINTCUT_CUSTOMER_GET)
    public Object cacheCustomerGet(ProceedingJoinPoint joinPoint) throws Throwable {
        // 取出第一个参数
        Integer object = (Integer) joinPoint.getArgs()[0];
        // 从缓存里面取
        Object res1 = CACHE_CONTAINER.get(CACHE_CUSTOMER_PREFIX + object);
        if (res1 != null) {
            log.info("已从缓存里面找到客户对象" + CACHE_CUSTOMER_PREFIX + object);
            return res1;
        } else {
            Customer res2 = (Customer) joinPoint.proceed();
            CACHE_CONTAINER.put(CACHE_CUSTOMER_PREFIX + res2.getId(), res2);
            log.info("未从缓存里面找到客户对象，请去数据库查询并放到缓存"+CACHE_CUSTOMER_PREFIX+res2.getId());
            return res2;
        }
    }

    /**
     * 更新切入
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(value = POINTCUT_CUSTOMER_UPDATE)
    public Object cacheCustomerUpdate(ProceedingJoinPoint joinPoint) throws Throwable {
        // 取出第一个参数
        Customer customerVo = (Customer) joinPoint.getArgs()[0];
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess) {
            Customer customer = (Customer) CACHE_CONTAINER.get(CACHE_CUSTOMER_PREFIX + customerVo.getId());
            if (null == customer) {
                customer = new Customer();
            }
            BeanUtils.copyProperties(customerVo, customer);
            log.info("客户对象缓存已更新" + CACHE_CUSTOMER_PREFIX + customerVo.getId());
            CACHE_CONTAINER.put(CACHE_CUSTOMER_PREFIX + customer.getId(), customer);
        }
        return isSuccess;
    }

    /**
     * 删除切入
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(value = POINTCUT_CUSTOMER_DELETE)
    public Object cacheCustomerDelete(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        Integer id = (Integer) joinPoint.getArgs()[0];
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if(isSuccess){
            //删除缓存
            CACHE_CONTAINER.remove(CACHE_CUSTOMER_PREFIX+id);
            log.info("客户对象缓存已删除"+CACHE_CUSTOMER_PREFIX+id);
        }
        return isSuccess;
    }


    @Around(value = POINTCUT_CUSTOMER_BATCHDELETE)
    public Object cacheCustomerBatchDelete(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        Collection<Serializable> idList = (Collection<Serializable>) joinPoint.getArgs()[0];
        Boolean isSuccess= (Boolean) joinPoint.proceed();
        if(isSuccess){
            for (Serializable id : idList) {
                //删除缓存
                CACHE_CONTAINER.remove(CACHE_CUSTOMER_PREFIX+id);
                log.info("客户对象缓存已删除"+CACHE_CUSTOMER_PREFIX+id);
            }
        }
        return isSuccess;
    }

    /**
     * 声明切面表达式
     */
    private static final String POINTCUT_PROVIDER_ADD = "execution(* com.jiu.bus.service.impl.ProviderServiceImpl.save(..))";
    private static final String POINTCUT_PROVIDER_UPDATE = "execution(* com.jiu.bus.service.impl.ProviderServiceImpl.updateById(..))";
    private static final String POINTCUT_PROVIDER_GET = "execution(* com.jiu.bus.service.impl.ProviderServiceImpl.getById(..))";
    private static final String POINTCUT_PROVIDER_DELETE = "execution(* com.jiu.bus.service.impl.ProviderServiceImpl.removeById(..))";
    private static final String POINTCUT_PROVIDER_BATCHDELETE = "execution(* com.jiu.bus.service.impl.ProviderServiceImpl.removeByIds(..))";

    private static final String CACHE_PROVIDER_PREFIX = "provider:";

    /**
     * 供应商添加切入
     *
     * @throws Throwable
     */
    @Around(value = POINTCUT_PROVIDER_ADD)
    public Object cacheProviderAdd(ProceedingJoinPoint joinPoint) throws Throwable {
        // 取出第一个参数
        Provider object = (Provider) joinPoint.getArgs()[0];
        Boolean res = (Boolean) joinPoint.proceed();
        if (res) {
            CACHE_CONTAINER.put(CACHE_PROVIDER_PREFIX + object.getId(), object);
        }
        return res;
    }

    /**
     * 查询切入
     *
     * @throws Throwable
     */
    @Around(value = POINTCUT_PROVIDER_GET)
    public Object cacheProviderGet(ProceedingJoinPoint joinPoint) throws Throwable {
        // 取出第一个参数
        Integer object = (Integer) joinPoint.getArgs()[0];
        // 从缓存里面取
        Object res1 = CACHE_CONTAINER.get(CACHE_PROVIDER_PREFIX + object);
        if (res1 != null) {
            log.info("已从缓存里面找到供应商对象" + CACHE_PROVIDER_PREFIX + object);
            return res1;
        } else {
            Provider res2 = (Provider) joinPoint.proceed();
            CACHE_CONTAINER.put(CACHE_PROVIDER_PREFIX + res2.getId(), res2);
            log.info("未从缓存里面找到供应商对象，去数据库查询并放到缓存" + CACHE_PROVIDER_PREFIX + res2.getId());
            return res2;
        }
    }

    /**
     * 更新切入
     *
     * @throws Throwable
     */
    @Around(value = POINTCUT_PROVIDER_UPDATE)
    public Object cacheProviderUpdate(ProceedingJoinPoint joinPoint) throws Throwable {
        // 取出第一个参数
        Provider deptVo = (Provider) joinPoint.getArgs()[0];
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess) {
            Provider dept = (Provider) CACHE_CONTAINER.get(CACHE_PROVIDER_PREFIX + deptVo.getId());
            if (null == dept) {
                dept = new Provider();
            }
            BeanUtils.copyProperties(deptVo, dept);
            log.info("供应商对象缓存已更新" + CACHE_PROVIDER_PREFIX + deptVo.getId());
            CACHE_CONTAINER.put(CACHE_PROVIDER_PREFIX + dept.getId(), dept);
        }
        return isSuccess;
    }

    /**
     * 删除切入
     *
     * @throws Throwable
     */
    @Around(value = POINTCUT_PROVIDER_DELETE)
    public Object cacheProviderDelete(ProceedingJoinPoint joinPoint) throws Throwable {
        // 取出第一个参数
        Integer id = (Integer) joinPoint.getArgs()[0];
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess) {
            // 删除缓存
            CACHE_CONTAINER.remove(CACHE_PROVIDER_PREFIX + id);
            log.info("供应商对象缓存已删除" + CACHE_PROVIDER_PREFIX + id);
        }
        return isSuccess;
    }

    /**
     * 批量删除切入
     *
     * @throws Throwable
     */
    @Around(value = POINTCUT_PROVIDER_BATCHDELETE)
    public Object cacheProviderBatchDelete(ProceedingJoinPoint joinPoint) throws Throwable {
        // 取出第一个参数
        @SuppressWarnings("unchecked")
        Collection<Serializable> idList = (Collection<Serializable>) joinPoint.getArgs()[0];
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess) {
            for (Serializable id : idList) {
                // 删除缓存
                CACHE_CONTAINER.remove(CACHE_PROVIDER_PREFIX + id);
                log.info("供应商对象缓存已删除" + CACHE_PROVIDER_PREFIX + id);
            }
        }
        return isSuccess;
    }

}
