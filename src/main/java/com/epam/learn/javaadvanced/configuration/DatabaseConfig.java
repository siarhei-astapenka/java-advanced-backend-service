package com.epam.learn.javaadvanced.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@Configuration
@EnableR2dbcRepositories
public class DatabaseConfig {
}
