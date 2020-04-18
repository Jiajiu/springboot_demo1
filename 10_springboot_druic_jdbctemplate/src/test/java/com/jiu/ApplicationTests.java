package com.jiu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

@SpringBootTest
class ApplicationTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Test
    void contextLoads() {
        Map<String,Object> map=jdbcTemplate.queryForMap("select * from sys_user where id=1");
        System.out.println(map);
    }

    @Test
    void contextLoads1() {
        List<Map<String,Object>> list=jdbcTemplate.queryForList("select * from sys_user");
        System.out.println(list);
    }
}
