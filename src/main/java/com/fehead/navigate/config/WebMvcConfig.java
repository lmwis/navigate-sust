package com.fehead.navigate.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author lmwis
 * @description:
 * @date 2019-09-06 15:40
 * @Version 1.0
 */
@EnableWebMvc
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

//    @Bean
//    public HttpPutFormContentFilter httpPutFormContentFilter() {
//        return new HttpPutFormContentFilter();
//    }
}
