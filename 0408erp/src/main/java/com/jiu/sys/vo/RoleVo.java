package com.jiu.sys.vo;

import com.jiu.sys.domain.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ClassName RoleVo
 * @Author Jiu
 * @Create 2020/4/17 14:26
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class RoleVo extends Role {

    private Integer page=1;
    private Integer limit=10;
}
