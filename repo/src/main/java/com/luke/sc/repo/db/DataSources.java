package com.luke.sc.repo.db;

import com.alibaba.druid.pool.DruidDataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Component
public class DataSources {


    private static Logger logger = LoggerFactory.getLogger(DataSources.class) ;

    @Resource
    C3p0Cfg c3p0Cfg ;

    @Resource
    DruidCfg druidCfg ;

    @Bean
    public DataSource c3p0DataSource() {
        return c3p0Cfg.initializeDataSourceBuilder()
                .type(ComboPooledDataSource.class).build();
    }


    @Bean
    @Primary
    public DataSource druidDataSource() {
        return druidCfg.initializeDataSourceBuilder()
                .type(DruidDataSource.class).build();
    }




}
