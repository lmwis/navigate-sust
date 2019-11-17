package com.fehead.navigate.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fehead.navigate.dao.entity.User;
import org.apache.ibatis.annotations.Select;

/**
 * @author lmwis
 * @description:
 * @date 2019-08-29 16:11
 * @Version 1.0
 */
public interface UserMapper extends BaseMapper<User> {

    @Select("select id,username,tel,password_id from user_info where username=#{username}")
    public User selectByUsername(String username);
    @Select("select * from user_info where tel=#{tel}")
    public User selectByUserTel(String tel);
}
