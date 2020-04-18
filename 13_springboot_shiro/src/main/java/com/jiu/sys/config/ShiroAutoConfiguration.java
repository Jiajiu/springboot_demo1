package com.jiu.sys.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.jiu.sys.realm.UserRealm;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ShiroAutoConfiguration
 * @Author Jiu
 * @Create 2020/4/4 20:57
 **/
@Configuration
@EnableConfigurationProperties(ShiroProperties.class)
public class ShiroAutoConfiguration {

    @Autowired
    private ShiroProperties shiroProperties;

    /**
     * 创建凭证匹配器
     */
    @Bean
    public HashedCredentialsMatcher credentialsMatcher(){
        HashedCredentialsMatcher credentialsMatcher=new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName(shiroProperties.getHashAlgorithmName());
        credentialsMatcher.setHashIterations(shiroProperties.getHashIterations());
        return  credentialsMatcher;
    }

    /**
     * 创建realm
     */
    @Bean
    public UserRealm userRealm(CredentialsMatcher credentialsMatcher){
        UserRealm userRealm=new UserRealm();
        //注入凭证匹配器
        userRealm.setCredentialsMatcher(credentialsMatcher);
        return userRealm;
    }

    /**
     * 声明安全管理器
     */
    @Bean("securityManager")
    public SecurityManager securityManager(UserRealm userRealm){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    /**
     * 配置过滤器 Shiro 的Web过滤器 id必须和web.xml里面的shiroFilter的 targetBeanName的值一样
     */
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean bean=new ShiroFilterFactoryBean();
        //注入安全管理器
        bean.setSecurityManager(securityManager);
        //注入登陆页面
        bean.setLoginUrl(shiroProperties.getLoginUrl());
        //注入未授权的页面地址
        bean.setUnauthorizedUrl(shiroProperties.getUnauthorizedUrl());
        //注入过滤器
        Map<String, String> filterChainDefinition=new HashMap<>();

        //注入放行地址
        if(shiroProperties.getAnonUrls()!=null&&shiroProperties.getAnonUrls().length>0){
            String[] anonUrls = shiroProperties.getAnonUrls();
            for (String anonUrl : anonUrls) {
                filterChainDefinition.put(anonUrl,"anon");
            }
        }
        //注入登出的地址
        if(shiroProperties.getLogoutUrl()!=null){
            filterChainDefinition.put(shiroProperties.getLogoutUrl(),"logout");
        }
        //注拦截的地址
        String[] authcUrls = shiroProperties.getAuthcUrls();
        if(authcUrls!=null&&authcUrls.length>0){
            for (String authcUrl : authcUrls) {
                filterChainDefinition.put(authcUrl,"authc");
            }
        }
        bean.setFilterChainDefinitionMap(filterChainDefinition);

        return bean;
    }


    /**
     * 注册过滤器
     */
    @Bean
    public FilterRegistrationBean<DelegatingFilterProxy> filterRegistrationBeanDelegatingFilterProxy(){
        FilterRegistrationBean<DelegatingFilterProxy> bean=new FilterRegistrationBean<>();
        //创建过滤器
        DelegatingFilterProxy proxy=new DelegatingFilterProxy();
        bean.setFilter(proxy);
        bean.addInitParameter("targetFilterLifecycle","true");
        bean.addInitParameter("targetBeanName","shiroFilter");
//        bean.addUrlPatterns();
        List<String> servletNames=new ArrayList<>();
        servletNames.add(DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_BEAN_NAME);
        bean.setServletNames(servletNames);
        return bean;
    }


    /**
     * 这里是为了能在html页面引用shiro标签，上面两个函数必须添加，不然会报错
     */
    @Bean(name = "shiroDialect")
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }
}
