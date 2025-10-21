package com.demo.lesson02.pkg_annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public String appName() {
        return "<h1>First Java Spring Boot Project</h1> <h2>By Krisaleth</h2>";
    }
}
