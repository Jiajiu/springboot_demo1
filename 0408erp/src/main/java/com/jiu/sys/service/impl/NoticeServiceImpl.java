package com.jiu.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiu.sys.domain.Notice;
import com.jiu.sys.mapper.NoticeMapper;
import com.jiu.sys.service.NoticeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName NoticeServiceImpl
 * @Author Jiu
 * @Create 2020/4/11 11:39
 **/
@Service
@Transactional
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService{

}
