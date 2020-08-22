package com.mkejeiri.recipe.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.mkejeiri.recipe.domain.Recipe;
import com.mkejeiri.recipe.repositories.RecipeRepository;

@Service
public class RecipeServiceImpl implements RecipeService {
	private final RecipeRepository recipeRepository;

	public RecipeServiceImpl(RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
	}

	@Override
	public Set<Recipe> getRecipes() {
		var recipes = new HashSet<Recipe>();
		recipeRepository.findAll().iterator().forEachRemaining(recipes::add); //Java 8 Syntax!
		return recipes;
	}

}
