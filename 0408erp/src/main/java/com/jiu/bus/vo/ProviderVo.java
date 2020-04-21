package com.jiu.bus.vo;

import com.jiu.bus.domain.Provider;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ClassName ProviderVo
 * @Author Jiu
 * @Create 2020/4/21 21:44
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class ProviderVo extends Provider {

    private Integer page=1;
    private Integer limit=10;

    private Integer [] ids;
}
