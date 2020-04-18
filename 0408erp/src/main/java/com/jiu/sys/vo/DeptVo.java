package com.jiu.sys.vo;

import com.jiu.sys.domain.Dept;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ClassName NoticeVo
 * @Author Jiu
 * @Create 2020/4/11 11:40
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class DeptVo extends Dept {

    private Integer page=1;
    private Integer limit=10;

}
