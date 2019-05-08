package com.luke.sc.repo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableConfigurationProperties
public class App {


    private static Logger logger = LoggerFactory.getLogger(App.class) ;



    public static void main(String[] args) {
        logger.info("==============security 2 start ====================");
        ConfigurableApplicationContext context = SpringApplication.run(App.class,args) ;
        context.getBean("dataSourceProperties") ;
        logger.info("==============security 2 end ====================");



    }
}
