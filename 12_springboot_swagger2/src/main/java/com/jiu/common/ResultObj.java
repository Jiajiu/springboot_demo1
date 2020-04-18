package com.jiu.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName ResultObj
 * @Author Jiu
 * @Create 2020/4/4 15:08
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultObj {

    private Integer status=200;
    private Object msg;
}
