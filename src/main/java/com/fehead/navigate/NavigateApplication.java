package com.fehead.navigate;

import com.fehead.navigate.dao.TestMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Hello world!
 *
 */

@SpringBootApplication
@RestController
@MapperScan("com.fehead.navigate.dao")
@EnableSwagger2
public class NavigateApplication {

    @Autowired
    TestMapper testMapper;

    @GetMapping("hello")
    public Object hello(){
        return testMapper.selectList(null);
    }

    public static void main( String[] args ) {
        System.out.println( "Hello World!" );

        SpringApplication.run(NavigateApplication.class,args);
    }
}
