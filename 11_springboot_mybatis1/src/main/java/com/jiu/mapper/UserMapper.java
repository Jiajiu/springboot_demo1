package com.jiu.mapper;

import com.jiu.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @ClassName UserMapper
 * @Author Jiu
 * @Create 2020/4/3 20:31
 **/
public interface UserMapper {

//    @Delete("delete from sys_user where id=#{id}")
    int deleteByPrimaryKey(@Param("id") Integer id);

//    @Insert("insert into sys_user(name,address,birth) values(#{name},#{address} ,#{birth} )")
    int insert(User user);

//    @Select("select * from sys_user where id=#{value}")
    User selectByPrimaryKey(@Param("id") Integer id);

//    @Update("update sys_user set name=#{name},address=#{address} ,birth=#{birth} where id=#{id}")
    int updateByPrimaryKey(User user);

//    @Select("select * from sys_user")
    List<User> queryAllUser();
}
