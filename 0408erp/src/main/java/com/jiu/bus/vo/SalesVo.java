package com.jiu.bus.vo;

import com.jiu.bus.domain.Sales;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @ClassName InportVo
 * @Author Jiu
 * @Create 2020/4/23 9:59
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class SalesVo extends Sales {

    private Integer page=1;
    private Integer limit=10;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
}
