package com.fehead.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fehead.config.vo.UnitVO;
import com.fehead.dao.entity.Unit;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * @author lmwis
 * @description:
 * @date 2019-08-31 20:31
 * @Version 1.0
 */
public interface UnitMapper extends BaseMapper<Unit> {



    @Select(value = "select * from unit_info left join sust_point on unit_info.point_id=sust_point.id where unit_info.id=#{id}")
    public UnitVO selectUnitInfoById(int id);
    @Select(value = "select * from unit_info left join sust_point " +
            "on unit_info.point_id=sust_point.id where unit_info.name " +
            "like '%${name}%' and unit_info.status=1")
    public List<UnitVO> selectUnitInfoByLikeName(@Param(value="name")String name);
}
