package com.jiu.sys.vo;

import com.jiu.sys.domain.Permission;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ClassName PermissionVo
 * @Author Jiu
 * @Create 2020/4/9 11:22
 **/
@Data
@EqualsAndHashCode
public class PermissionVo extends Permission {

    private Integer page=1;
    private Integer limit=10;
}
