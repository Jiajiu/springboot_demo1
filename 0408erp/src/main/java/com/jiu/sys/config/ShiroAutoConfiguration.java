package com.jiu.sys.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.jiu.sys.realm.UserRealm;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ShiroAutoConfiguration
 * @Author Jiu
 * @Create 2020/4/8 19:10
 **/
@Configuration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@ConditionalOnClass(value = {SecurityManager.class})
@EnableConfigurationProperties(ShiroProperties.class)
public class ShiroAutoConfiguration {

    @Autowired
    private ShiroProperties properties;
    /**
     * 声明凭证匹配器
     * @return
     */
    @Bean("credentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher credentialsMatcher=new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName(properties.getHashAlgorithmName());
        credentialsMatcher.setHashIterations(properties.getHashIterations());
        return credentialsMatcher;
    }

    /**
     * 声明userRealm
     * @return
     */
    @Bean("userRealm")
    public UserRealm userRealm(CredentialsMatcher credentialsMatcher){
        UserRealm userRealm=new UserRealm();
        //注入凭证匹配器
        userRealm.setCredentialsMatcher(credentialsMatcher);
        return userRealm;
    }

    /**
     * 声明SecurityManager
     * @param userRealm
     * @return
     */
    @Bean("securityManager")
    public SecurityManager securityManager(UserRealm userRealm){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        //注入userRea
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    /**
     * 配置过滤器 Shiro 的Web过滤器 id必须和web.xml里面的shiroFilter的 targetBeanName的值一样
     * @param securityManager
     * @return
     */
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean bean=new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(securityManager);
        //设置未登录时要跳转的页面
        bean.setLoginUrl(properties.getLoginUrl());
        Map<String,String> filterChainDefinitionMap=new HashMap<>();
        //设置放行的路径
        if(properties.getAnonUrls()!=null && properties.getAnonUrls().length>0){
            String [] anonUrl=properties.getAnonUrls();
            for (String anon : anonUrl) {
                filterChainDefinitionMap.put(anon,"anon");
            }
        }
        //设置登出的路径
        if(properties.getLogoutUrl()!=null){
            filterChainDefinitionMap.put(properties.getLogoutUrl(),"logout");
        }

        //设置拦截的路径
        String[] authcUrls = properties.getAuthcUrls();
        if(authcUrls!=null && authcUrls.length>0){
            for (String authcUrl : authcUrls) {
                filterChainDefinitionMap.put(authcUrl,"authc");
            }
        }
//        Map<String, Filter> filters=new HashMap<>();
//        filters.put("authc",new ShiroLoginFilter);
//        //配置过滤器
//        bean.setFilters(filters);
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return bean;
    }

    /**
     * 注册shiro的过滤器，相当于之前在web.xml里面的配置
     * @return
     */
    @Bean
    public FilterRegistrationBean<DelegatingFilterProxy> delegatingFilterProxyFilterRegistrationBean(){
        FilterRegistrationBean<DelegatingFilterProxy> bean=new FilterRegistrationBean<>();
        DelegatingFilterProxy proxy=new DelegatingFilterProxy();
        proxy.setTargetFilterLifecycle(true);
        proxy.setTargetBeanName("shiroFilter");
        bean.setFilter(proxy);
        return bean;
    }

    /*加入注解的使用，不加入这个注解不生效--start*/

    /**
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor=new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator=new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }
    /* 加入注解的使用，不加入这个注解不生效--end*/

    /**
     * 这里是为了能在html页面引用shiro标签，上面两个函数必须添加，不然会报错
     */
    @Bean(name = "shiroDialect")
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }
}


