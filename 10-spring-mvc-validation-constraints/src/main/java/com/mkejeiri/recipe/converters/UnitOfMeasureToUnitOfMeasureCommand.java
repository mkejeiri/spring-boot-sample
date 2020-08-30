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
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {

    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasureCommand convert(UnitOfMeasure unitOfMeasure) {

        if (unitOfMeasure != null) {
            final UnitOfMeasureCommand uomc = new UnitOfMeasureCommand();
            uomc.setId(unitOfMeasure.getId());
            uomc.setDescription(unitOfMeasure.getDescription());
            return uomc;
        }
        return null;
    }
}