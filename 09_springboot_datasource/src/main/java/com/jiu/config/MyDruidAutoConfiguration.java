package com.jiu.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName MyDruidAutoConfiguration
 * @Author Jiu
 * @Create 2020/4/3 11:39
 **/
@Configuration
@ConditionalOnClass(value = {DruidDataSource.class})
@EnableConfigurationProperties(MyDruidProperties.class)
public class MyDruidAutoConfiguration {


    private Log log= LogFactory.getLog(MyDruidAutoConfiguration.class);

    @Autowired
    private MyDruidProperties properties;


    /**
     * 创建DataSource
     */
    @Bean(initMethod = "init")
    public DruidDataSource druidDataSource(){
        DruidDataSource dataSource=new DruidDataSource();
        if(null==properties.getUrl()){
            log.error("url can not be null");
            throw new RuntimeException("url can not be null");
        }
        dataSource.setDriverClassName(properties.getDriverClassName());
        dataSource.setUrl(properties.getUrl());
        dataSource.setUsername(properties.getUsername());
        dataSource.setPassword(properties.getPassword());
        dataSource.setMaxActive(properties.getMaxActive());
        dataSource.setMinIdle(properties.getMinIdle());
        dataSource.setMaxIdle(properties.getMaxIdle());
        dataSource.setInitialSize(properties.getInitialSize());
        dataSource.setValidationQuery(properties.getValidationQuery());
        return dataSource;
    }



    /**
     * 注册监听页面的Servlet
     */
    @Bean
    @ConditionalOnClass(value = {StatViewServlet.class})
    public ServletRegistrationBean<StatViewServlet> registrationBeanStatViewServlet(){
        //创建注册器
        ServletRegistrationBean<StatViewServlet> bean=new ServletRegistrationBean<>();
        //创建Servlet
        StatViewServlet servlet=new StatViewServlet();
        //注册
        bean.setServlet(servlet);

        //注入相关参数
        bean.addInitParameter("loginUsername",properties.getStatView().getLoginUsername());
        bean.addInitParameter("loginPassword",properties.getStatView().getLoginPassword());
        bean.addInitParameter("allow",properties.getStatView().getAllow());
        bean.addInitParameter("deny",properties.getStatView().getDeny());

        if(properties.getStatView().getUrlMapping()==null||properties.getStatView().getUrlMapping().length==0){
            log.error("监控的urlMapping不能为空");
            throw  new RuntimeException("监控的urlMapping不能为空");
        }
        //设置映射
        bean.addUrlMappings(properties.getStatView().getUrlMapping());

        return bean;

    }

}
