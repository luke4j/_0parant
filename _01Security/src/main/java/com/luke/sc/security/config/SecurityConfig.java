package com.luke.sc.security.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/css/**", "/js/**", "/fonts/**").permitAll()  // 允许访问资源
                .antMatchers("/", "/home", "/about").permitAll() //允许访问这三个路由
                .antMatchers("/admin/**").hasAnyRole("ADMIN")   // 满足该条件下的路由需要ROLE_ADMIN的角色
                .antMatchers("/user/**").hasAnyRole("USER")     // 满足该条件下的路由需要ROLE_USER的角色
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll() ;
//                .and()
//                .exceptionHandling()
//                .accessDeniedHandler(accessDeniedHandler);           //自定义异常处理
    }


    @Resource
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("user").roles("USER")
                .and()
                .withUser("admin").password("admin").roles("ADMIN");
    }
}
