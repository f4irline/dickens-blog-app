package com.github.dickens.blogapp.search;

import com.github.dickens.blogapp.utils.StringToEnumConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class HibernateSearchConfig implements WebMvcConfigurer {
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**")
                .allowedOrigins("https://localhost:3000", "http://localhost:3000")
                .allowedMethods("GET", "POST", "OPTIONS", "DELETE", "PUT", "PATCH");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToEnumConverter());
    }
}
