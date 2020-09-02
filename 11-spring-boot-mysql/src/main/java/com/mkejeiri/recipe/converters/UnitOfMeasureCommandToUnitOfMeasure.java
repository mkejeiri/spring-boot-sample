package com.mkejeiri.recipe.converters;

import org.springframework.stereotype.Component;

import org.springframework.core.convert.converter.Converter;

import com.mkejeiri.recipe.commands.CategoryCommand;
import com.mkejeiri.recipe.commands.IngredientCommand;
import com.mkejeiri.recipe.commands.NotesCommand;
import com.mkejeiri.recipe.commands.RecipeCommand;
import com.mkejeiri.recipe.commands.UnitOfMeasureCommand;
import com.mkejeiri.recipe.domain.Category;
import com.mkejeiri.recipe.domain.Ingredient;
import com.mkejeiri.recipe.domain.Notes;
import com.mkejeiri.recipe.domain.Recipe;
import com.mkejeiri.recipe.domain.UnitOfMeasure;
import com.sun.istack.Nullable;

import lombok.Synchronized;

@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure>{

    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasure convert(UnitOfMeasureCommand source) {
        if (source == null) {
            return null;
        }

        final UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(source.getId());
        uom.setDescription(source.getDescription());
        return uom;
    }
}