package com.mkejeiri.recipe.repositories;

import org.springframework.data.repository.CrudRepository;

import com.mkejeiri.recipe.domain.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
	

}
