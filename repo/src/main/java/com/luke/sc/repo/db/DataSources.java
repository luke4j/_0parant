package com.luke.sc.repo.db;

import com.alibaba.druid.pool.DruidDataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Component
public class DataSources {

//    @Resource
//    C3p0Cfg c3p0Cfg ;

    @Resource
    DruidCfg druidCfg ;


//    @Bean
//    @Primary
//    @ConfigurationProperties("app.datasource.c3p0.configuration")
//    public DataSource c3p0DataSource() {
//        return c3p0Cfg.dataSourceProperties().initializeDataSourceBuilder()
//                .type(ComboPooledDataSource.class).build();
//    }

    @Bean
    @ConfigurationProperties("app.datasource.druid.configuration")
    public DataSource druidDataSource() {
        return druidCfg.dataSourceProperties().initializeDataSourceBuilder()
                .type(DruidDataSource.class).build();
    }




}
