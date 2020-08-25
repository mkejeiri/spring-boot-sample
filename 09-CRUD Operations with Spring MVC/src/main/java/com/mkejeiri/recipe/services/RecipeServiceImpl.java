package com.mkejeiri.recipe.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.mkejeiri.recipe.bootstrap.RecipeBootstrap;
import com.mkejeiri.recipe.domain.Recipe;
import com.mkejeiri.recipe.repositories.RecipeRepository;

import lombok.extern.slf4j.Slf4j;

//@Slf4j: this inject a logger, it is the logging facade which gives access to the default Spring Boot logger which is the logback
@Slf4j //  using logging from lombok, we get a log property (see Outline)

@Service
public class RecipeServiceImpl implements RecipeService {
	private final RecipeRepository recipeRepository;

	public RecipeServiceImpl(RecipeRepository recipeRepository) {
		log.debug("I'm in RecipeServiceImpl");
		this.recipeRepository = recipeRepository;
	}

	@Override
	public Set<Recipe> getRecipes() {
		var recipes = new HashSet<Recipe>();
		recipeRepository.findAll().iterator().forEachRemaining(recipes::add); // Java 8 Syntax!
		return recipes;
	}

}
