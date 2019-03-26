package com.github.dickens.blogapp.utils;

import com.github.dickens.blogapp.Category;
import org.springframework.core.convert.converter.Converter;

public class StringToEnumConverter implements Converter<String, Category> {
    @Override
    public Category convert(String from) {
        return Category.valueOf(from.toUpperCase());
    }
}
