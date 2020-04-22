package com.jiu.bus.vo;

import com.jiu.bus.domain.Goods;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ClassName ProviderVo
 * @Author Jiu
 * @Create 2020/4/21 21:44
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class GoodsVo extends Goods {

    private Integer page=1;
    private Integer limit=10;
}
