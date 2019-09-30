package com.fehead.aspect;

import com.fehead.dao.UriCountMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lmwis
 * @description:
 * @date 2019-09-05 13:32
 * @Version 1.0
 */

@Aspect
@Component
public class UriCounterAspect {

    @Autowired
    UriCountMapper uriCountMapper;

    @Pointcut("@annotation(UriCounter)")
    public void recodeWhenAccess(){

    }

    @Before("recodeWhenAccess()")
    public void recodeWhenAccess1(JoinPoint joinPoint){
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        System.out.println(request.getRequestURI());
//        System.out.println("记录访问1");
    }

}
