package com.mkejeiri.recipe.converters;

import org.springframework.stereotype.Component;

import org.springframework.core.convert.converter.Converter;
import com.mkejeiri.recipe.command.CategoryCommand;
import com.mkejeiri.recipe.command.IngredientCommand;
import com.mkejeiri.recipe.domain.Category;
import com.mkejeiri.recipe.domain.Ingredient;
import com.sun.istack.Nullable;

import lombok.Synchronized;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

	private final UnitOfMeasureToUnitOfMeasureCommand uomConverter;

	public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand uomConverter) {
		this.uomConverter = uomConverter;
	}

	@Synchronized
	@Nullable
	@Override
	public IngredientCommand convert(Ingredient ingredient) {
		if (ingredient == null) {
			return null;
		}

		IngredientCommand ingredientCommand = new IngredientCommand();
		ingredientCommand.setId(ingredient.getId());
		ingredientCommand.setAmount(ingredient.getAmount());
		if (ingredient.getRecipe() != null) {
			ingredientCommand.setRecipeId(ingredient.getRecipe().getId());
		}

		ingredientCommand.setDescription(ingredient.getDescription());
		ingredientCommand.setUom(uomConverter.convert(ingredient.getUom()));
		return ingredientCommand;
	}
}