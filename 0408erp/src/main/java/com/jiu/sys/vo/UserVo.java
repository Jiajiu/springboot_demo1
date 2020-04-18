package com.jiu.sys.vo;

import com.jiu.sys.domain.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ClassName UserVo
 * @Author Jiu
 * @Create 2020/4/17 20:32
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class UserVo extends User {

    private Integer page=1;

    private Integer limit=10;

}
