package com.luke.sc.repo.db;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

 @Component
 @ConfigurationProperties("app.datasource.durid")
public class DruidCfg extends  DataSourceProperties {

}
