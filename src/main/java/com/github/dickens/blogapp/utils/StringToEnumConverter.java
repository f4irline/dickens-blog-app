package com.github.dickens.blogapp.utils;

import com.github.dickens.blogapp.Category;
import org.springframework.core.convert.converter.Converter;

/**
 * Class for converting String to Category for enum.
 *
 * @author Tommi Lepola
 * @since 2019.0326
 * @version 1.0
 */
public class StringToEnumConverter implements Converter<String, Category> {
    /**
     * Returns Category converted from given String.
     *
     * @param from containing String to convert
     * @return Category representing the category
     */
    @Override
    public Category convert(String from) {
        return Category.valueOf(from.toUpperCase());
    }
}
