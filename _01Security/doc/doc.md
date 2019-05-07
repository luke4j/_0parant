[TOC]

# spring-boot-security

# 静态示例

## pom.xml添加启动器

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

## 添加启动注解

```java
@SpringBootApplication
@EnableWebSecurity
public class App{
    ....
}
```

## java配置

```java
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
            .csrf().disable()      //设置不启动csrf保护
            .authorizeRequests()	//字面意思很明确，受权请求
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
            .passwordEncoder(new BCryptPasswordEncoder())
            .withUser("user").password(new BCryptPasswordEncoder().encode("user")).roles("USER")
            .and()
            .withUser("admin").password(new BCryptPasswordEncoder().encode("admin")).roles("ADMIN");
        //如果是数据库里面取的数据就要加上
        /*
         *   auth
         *      .userDetailsService(userService)
         *     .passwordEncoder(new BCryptPasswordEncoder());
    	 */
    }
}
```

## 自定义处理异常

```java
package com.luke.sc.security.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    private static final Logger logger = LoggerFactory.getLogger(MyAccessDeniedHandler.class) ;
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e)
            throws IOException, ServletException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            logger.info("================User '" + auth.getName()
                    + "' attempted to access the protected URL: "
                    + httpServletRequest.getRequestURI());
        }

        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/403");

    }
}
```