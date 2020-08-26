package com.mkejeiri.recipe.services;

import java.util.Set;

import com.mkejeiri.recipe.domain.Recipe;

public interface RecipeService {
	Set<Recipe> getRecipes();

	public Recipe findById(Long id);

	public void deleteById(Long id);

	public void delete(Recipe recipe);

	public Recipe save(Recipe recipe);
}
