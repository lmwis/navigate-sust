package com.fehead.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.fehead.properties.LoginType;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @author lmwis on 2019-07-16 10:52
 */
@Component("lmwisAuthenticationSuccessHandler")
public class FeheadAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(FeheadAuthenticationSuccessHandler.class);

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    SecurityProperties securityProperties;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException {

        logger.info("登陆成功:" + ((UserDetails) authentication.getPrincipal()).getUsername());

        String token = Jwts.builder()
                .setSubject(((org.springframework.security.core.userdetails.User) authentication.getPrincipal()).getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000))
                .signWith(SignatureAlgorithm.HS512, "MyJwtSecret")
                .compact();

        setCORS(response);

        response.setContentType("application/json;charset=UTF-8");
        response.addHeader("token", "Bearer " + token);
        response.setHeader("Access-Control-Expose-Headers", "token");
        response.getWriter().write(objectMapper.writeValueAsString(authentication));

    }

    public static void setCORS(HttpServletResponse response){
        response.addHeader("Access-Control-Allow-Origin", "*"); //跨域设置
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
    }
}
