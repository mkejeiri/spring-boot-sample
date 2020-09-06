package com.mkejeiri.recipe.repositories;

import com.mkejeiri.recipe.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, String> {
}
