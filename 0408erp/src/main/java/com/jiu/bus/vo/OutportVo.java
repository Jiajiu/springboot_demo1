package com.jiu.bus.vo;

import com.jiu.bus.domain.Outport;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @ClassName OutportVo
 * @Author Jiu
 * @Create 2020/4/23 18:53
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class OutportVo extends Outport {

    private Integer page=1;
    private Integer limit=10;

    private Integer [] ids;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
}
