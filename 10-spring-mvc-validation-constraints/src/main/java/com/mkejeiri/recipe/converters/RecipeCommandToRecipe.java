package com.mkejeiri.recipe.converters;

import org.springframework.stereotype.Component;

import org.springframework.core.convert.converter.Converter;

import com.mkejeiri.recipe.commands.RecipeCommand;
import com.mkejeiri.recipe.domain.Recipe;
import com.sun.istack.Nullable;

import lombok.Synchronized;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

	private final CategoryCommandToCategory categoryConveter;
	private final IngredientCommandToIngredient ingredientConverter;
	private final NotesCommandToNotes notesConverter;

	public RecipeCommandToRecipe(CategoryCommandToCategory categoryConveter,
			IngredientCommandToIngredient ingredientConverter, NotesCommandToNotes notesConverter) {
		this.categoryConveter = categoryConveter;
		this.ingredientConverter = ingredientConverter;
		this.notesConverter = notesConverter;
	}

	@Synchronized
	@Nullable
	@Override
	public Recipe convert(RecipeCommand source) {
		if (source == null) {
			return null;
		}

		final Recipe recipe = new Recipe();
		recipe.setId(source.getId());
		recipe.setCookTime(source.getCookTime());
		recipe.setPrepTime(source.getPrepTime());
		recipe.setDescription(source.getDescription());
		recipe.setDifficulty(source.getDifficulty());
		recipe.setDirections(source.getDirections());
		recipe.setServings(source.getServings());
		recipe.setSource(source.getSource());
		recipe.setUrl(source.getUrl());		
		//Demo purposes: We don't have a use case to support that because we only render from the db to front-end!
		recipe.setImage(source.getImage());

		var notes = notesConverter.convert(source.getNotes());

		if (notes != null) {
			recipe.setNotes(notes);
		}

		if (source.getCategories() != null && source.getCategories().size() > 0) {
			source.getCategories().forEach(category -> recipe.getCategories().add(categoryConveter.convert(category)));
		}

		if (source.getIngredients() != null && source.getIngredients().size() > 0) {
			source.getIngredients()
					.forEach(ingredient -> recipe.getIngredients().add(ingredientConverter.convert(ingredient)));
		}

		return recipe;
	}
}