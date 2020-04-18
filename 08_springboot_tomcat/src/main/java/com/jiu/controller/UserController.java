package com.jiu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName UserController
 * @Author Jiu
 * @Create 2020/4/3 10:48
 **/

@Controller
@RequestMapping("user")
public class UserController {

    @RequestMapping("queryAllUser")
    public String queryAllUser(){
        return "list";
    }
}
