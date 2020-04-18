package com.jiu.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiu.sys.domain.Loginfo;
import com.jiu.sys.mapper.LoginfoMapper;
import com.jiu.sys.service.LoginfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName LoginfoServiceImpl
 * @Author Jiu
 * @Create 2020/4/9 16:41
 **/
@Service
@Transactional
public class LoginfoServiceImpl extends ServiceImpl<LoginfoMapper, Loginfo> implements LoginfoService{

}
