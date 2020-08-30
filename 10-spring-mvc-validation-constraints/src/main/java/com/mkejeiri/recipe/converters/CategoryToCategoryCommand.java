package com.mkejeiri.recipe.converters;

import org.springframework.stereotype.Component;

import org.springframework.core.convert.converter.Converter;

import com.mkejeiri.recipe.commands.CategoryCommand;
import com.mkejeiri.recipe.domain.Category;
import com.sun.istack.Nullable;

import lombok.Synchronized;

@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand> {

    @Synchronized
    @Nullable
    @Override
    public CategoryCommand convert(Category source) {
        if (source == null) {
            return null;
        }

        final CategoryCommand categoryCommand = new CategoryCommand();

        categoryCommand.setId(source.getId());
        categoryCommand.setDescription(source.getDescription());

        return categoryCommand;
    }
}