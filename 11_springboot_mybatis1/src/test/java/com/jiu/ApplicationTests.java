package com.jiu;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jiu.domain.User;
import com.jiu.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ApplicationTests {

    @Autowired
    private UserMapper userMapper;
    @Test
    void contextLoads() {
        System.out.println(userMapper);
        List<User> users=userMapper.queryAllUser();
        for (User user : users) {
            System.out.println(user);
        }

        User user = userMapper.selectByPrimaryKey(1);
        System.out.println(user);
    }

    @Test
    void page(){
        Page<Object> page= PageHelper.startPage(1,2);
        List<User> users=userMapper.queryAllUser();
        for (User user : users) {
            System.out.println(user);
        }
        System.out.println("总条数:"+page.getTotal());
    }

}
