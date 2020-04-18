package com.jiu.sys.mapper;

import com.jiu.sys.domain.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    int deleteByPrimaryKey(Integer userid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 根据用户名查询用户对象
     * @param username
     * @return
     */
    User queryUserByUserName(@Param("username") String username);
}
