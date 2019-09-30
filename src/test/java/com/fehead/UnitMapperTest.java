package com.fehead;

import com.fehead.config.vo.UnitVO;
import com.fehead.dao.UnitMapper;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author lmwis
 * @description:
 * @date 2019-08-31 21:01
 * @Version 1.0
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class UnitMapperTest {

    @Autowired
    UnitMapper unitMapper;

    @Test
    public void whenSelectUnitById(){
        UnitVO unitVO = unitMapper.selectUnitInfoById(1);

        System.out.println(new ReflectionToStringBuilder(unitVO));
    }

    @Test
    public void whenSelectUnitLikeName(){
        List<UnitVO> uni = unitMapper.selectUnitInfoByLikeName("易班");

        System.out.println(new ReflectionToStringBuilder(uni.get(0)));
    }

}
