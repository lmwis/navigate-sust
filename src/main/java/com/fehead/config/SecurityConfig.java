package com.fehead.config;

import com.fehead.authentication.JWTAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;

/**
 * @author lmwis
 * @description:
 * @date 2019-08-29 15:49
 * @Version 1.0
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    CORSFilter corsFilter;

    @Autowired
    private AuthenticationSuccessHandler feheadAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler feheadAuthenticationFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(corsFilter,JWTAuthenticationFilter.class)
                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .loginProcessingUrl("/admin/login")
                .successHandler(feheadAuthenticationSuccessHandler)
                .failureHandler(feheadAuthenticationFailureHandler)
                .and()
                .authorizeRequests()
                .antMatchers("/points/list"
                        ,"/point/**","/unit/**","/type/**"
                        ,"/admin/init"
                        ,"/admin/points/list","/admin/units/list").permitAll()
                // swagger start
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/v2/api-docs").permitAll()
                .antMatchers("/configuration/ui").permitAll()
                .antMatchers("/configuration/security").permitAll()
                // swagger end
                .anyRequest()
                .authenticated()
                .and().cors()
                .and()
                .csrf().disable();
    }

    private Filter authenticationTokenFilterBean() throws Exception {
        return new JWTAuthenticationFilter(authenticationManager());
    }
}
