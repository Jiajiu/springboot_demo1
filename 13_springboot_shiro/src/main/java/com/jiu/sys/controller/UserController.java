package com.jiu.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserController {

	/**
	 * 跳转到用户管理的页面
	 * @return
	 */
	@RequestMapping("toUserManager")
	public String toUserManager() {
		return "list";
	}
	
}
