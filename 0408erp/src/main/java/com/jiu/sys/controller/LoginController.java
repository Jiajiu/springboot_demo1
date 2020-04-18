package com.jiu.sys.controller;

import com.jiu.sys.common.ActiveUser;
import com.jiu.sys.common.ResultObj;
import com.jiu.sys.common.WebUtils;
import com.jiu.sys.domain.Loginfo;
import com.jiu.sys.service.LoginfoService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @ClassName LoginController
 * @Author Jiu
 * @Create 2020/4/8 20:37
 * 前端控制器
 **/
@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    private LoginfoService loginfoService;

    /**
     * 登录
     * @param loginname
     * @param pwd
     * @return
     */
    @RequestMapping("login")
    public ResultObj login(String loginname,String pwd){
        Subject subject= SecurityUtils.getSubject();
        AuthenticationToken token=new UsernamePasswordToken(loginname,pwd);
        try{
            subject.login(token);
            ActiveUser activeUser= (ActiveUser) subject.getPrincipal();
            WebUtils.getSession().setAttribute("user",activeUser.getUser());
            //记录登录日志
            Loginfo entity=new Loginfo();
            entity.setLoginname(activeUser.getUser().getName()+"-"+activeUser.getUser().getLoginname());
            entity.setLoginip(WebUtils.getRequest().getRemoteAddr());
            entity.setLogintime(new Date());
            loginfoService.save(entity);
            return ResultObj.LOGIN_SUCCESS;
        }catch (AuthenticationException e){
            e.printStackTrace();
            return ResultObj.LOGIN_ERROR_PASS;
        }
    }

    /**
     * 退出
     * @return
     */
    @RequestMapping("logout")
    public String logout(){
        WebUtils.getSession().invalidate();
        return "system/index/login";
    }
}
