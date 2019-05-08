package com.luke.sc.repo.db;

import com.alibaba.druid.pool.DruidDataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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



    @Bean
    @ConfigurationProperties(prefix = "app.datasource.c3p0")
    public DataSourceProperties c3p0DataSourceProperties(){
        return new DataSourceProperties() ;
    }
    @Bean
    @Resource(name = "c3p0DataSourceProperties" )
    public DataSource c3p0DataSource(DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder()
                .type(ComboPooledDataSource.class).build();
    }


    @Bean
    @Primary
    @ConfigurationProperties(prefix = "app.datasource.druid")
    public DataSourceProperties dataSourceProperties(){
        return new DataSourceProperties() ;
    }
    @Bean
    @Primary
    public DataSource druidDataSource(DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder()
                .type(DruidDataSource.class).build();
    }




}
