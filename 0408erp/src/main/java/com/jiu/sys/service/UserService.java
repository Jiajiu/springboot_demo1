package com.jiu.sys.service;

import com.jiu.sys.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
    /**
 * @ClassName UserService
 * @Author Jiu
 * @Create 2020/4/8 21:18
 **/
public interface UserService extends IService<User>{

        /**
         * 保存用户和角色之间的关系
         * @param uid
         * @param ids
         */
        void saveUserRole(Integer uid, Integer[] ids);
    }
