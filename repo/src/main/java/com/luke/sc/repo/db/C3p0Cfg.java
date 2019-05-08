package com.luke.sc.repo.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("app.datasource.c3p0")
public class C3p0Cfg extends DataSourceProperties {

    private static final Logger logger = LoggerFactory.getLogger(C3p0Cfg.class) ;



}
