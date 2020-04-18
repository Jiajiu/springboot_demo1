package com.jiu.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ClassName MyDruidProperties
 * @Author Jiu
 * @Create 2020/4/3 11:41
 **/
@Data
@ConfigurationProperties(prefix = "spring.druid")
public class MyDruidProperties {
    private String driverClassName;
    private String url;
    private String username;
    private String password;

    private Integer initialSize;
    private Integer maxActive;
    private Integer minIdle;
    private Integer maxIdle;
    private String validationQuery;//连接检查语句

    private StatView statView;

    @Data
    static class StatView{
        //监控的属性
        private String loginUsername;
        private String loginPassword;
        private String allow;
        private String deny;
        private String [] urlMapping;
    }
}

