package com.sparta.academy.mfix_mongodb_api.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "database.uri")
@Configuration
public class DBConfig {
}
