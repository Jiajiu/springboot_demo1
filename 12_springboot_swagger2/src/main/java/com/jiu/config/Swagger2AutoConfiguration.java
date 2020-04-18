package com.jiu.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName Swagger2AutoConfiguration
 * @Author Jiu
 * @Create 2020/4/4 15:12
 **/
@Configuration
@EnableSwagger2 //启用swagger2
public class Swagger2AutoConfiguration {

    @Bean
    public Docket swaggerSpringMvcPlugin(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .select().apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder().description("API工具")
                .contact(new Contact("jiu","http://jiu.tech","1358329310@qq.com"))//名片
        .version("1.0")//版本
        .license("就")//所有者
        .build();//构造
    }
}
