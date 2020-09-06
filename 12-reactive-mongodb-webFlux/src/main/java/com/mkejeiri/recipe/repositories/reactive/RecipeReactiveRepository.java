package com.mkejeiri.recipe.repositories.reactive;

import com.mkejeiri.recipe.domain.Recipe;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;


public interface RecipeReactiveRepository extends ReactiveMongoRepository<Recipe, String>{
}
