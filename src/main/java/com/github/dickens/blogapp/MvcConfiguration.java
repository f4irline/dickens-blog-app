package com.github.dickens.blogapp;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer {
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**")
                .allowedOrigins("https://localhost:3000", "http://localhost:3000")
                .allowedMethods("GET", "POST", "OPTIONS", "DELETE", "PUT", "PATCH");
    }
}
