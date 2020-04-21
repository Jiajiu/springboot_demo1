package com.jiu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName Application
 *  * @Author Jiu
 *  * @Create 2020/4/8 20:37
 */
@SpringBootApplication
@MapperScan(value = {"com.jiu.sys.mapper","com.jiu.bus.mapper"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
