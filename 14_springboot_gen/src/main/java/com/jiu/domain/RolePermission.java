package com.jiu.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName RolePermission
 * @Author Jiu
 * @Create 2020/4/5 12:11
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolePermission implements Serializable {
    private Integer perid;

    private Integer roleid;

    private static final long serialVersionUID = 1L;
}