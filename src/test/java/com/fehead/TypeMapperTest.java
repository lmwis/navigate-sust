package com.fehead;

import com.fehead.navigate.dao.TypeMapper;
import com.fehead.navigate.dao.entity.Type;
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
 * @date 2019-09-01 19:55
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TypeMapperTest {

    @Autowired
    TypeMapper typeMapper;

    @Test
    public void whenSelect() {
        List<Type> types = typeMapper.selectList(null);
        types.forEach(k -> {
            System.out.println(new ReflectionToStringBuilder(k));
        });
    }

    @Test
    public void whenSelectByName() {
        Type type = typeMapper.selectByName("读书");
        System.out.println(new ReflectionToStringBuilder(type));
    }

}
