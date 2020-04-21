package com.jiu.bus.vo;

import com.jiu.bus.domain.Customer;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ClassName CustomerVo
 * @Author Jiu
 * @Create 2020/4/21 17:07
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class CustomerVo extends Customer {

    private Integer page=1;
    private Integer limit=10;

    private Integer [] ids;
}
