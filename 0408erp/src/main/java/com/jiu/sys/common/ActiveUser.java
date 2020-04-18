package com.jiu.sys.common;

import com.jiu.sys.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName ActiveUser
 * @Author Jiu
 * @Create 2020/4/8 18:58
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActiveUser {

    private User user;

    private List<String> roles;

    private List<String> permissions;
}
