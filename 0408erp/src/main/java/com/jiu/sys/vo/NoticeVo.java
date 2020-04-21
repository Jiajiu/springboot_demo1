package com.jiu.sys.vo;

import com.jiu.sys.domain.Notice;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @ClassName NoticeVo
 * @Author Jiu
 * @Create 2020/4/11 11:40
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class NoticeVo extends Notice {

    private Integer page=1;
    private Integer limit=10;

    /**
     * 接收多个id
     */
    private Integer[] ids;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
}
