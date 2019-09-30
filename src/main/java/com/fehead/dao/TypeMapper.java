package com.fehead.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fehead.dao.entity.Type;
import org.apache.ibatis.annotations.Select;

/**
 * @author lmwis
 * @description:
 * @date 2019-09-01 19:54
 * @Version 1.0
 */
public interface TypeMapper extends BaseMapper<Type> {


    @Select("select * from point_type where name=#{name}")
    public Type selectByName(String name);

}
