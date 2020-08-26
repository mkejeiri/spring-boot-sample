package com.mkejeiri.recipe.services;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.mkejeiri.recipe.bootstrap.RecipeBootstrap;
import com.mkejeiri.recipe.command.RecipeCommand;
import com.mkejeiri.recipe.converters.RecipeCommandToRecipe;
import com.mkejeiri.recipe.converters.RecipeToRecipeCommand;
import com.mkejeiri.recipe.domain.Recipe;
import com.mkejeiri.recipe.repositories.RecipeRepository;

import lombok.extern.slf4j.Slf4j;

//@Slf4j: this inject a logger, it is the logging facade which gives access to the default Spring Boot logger which is the logback
@Slf4j // using logging from lombok, we getsees a log property (see Outline)

@Service
public class RecipeServiceImpl implements RecipeService {
	private final RecipeRepository recipeRepository;
	private final RecipeCommandToRecipe recipeCommandToRecipe;
	private final RecipeToRecipeCommand recipeToRecipeCommand;

	public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe,
			RecipeToRecipeCommand recipeToRecipeCommand) {
		this.recipeRepository = recipeRepository;
		this.recipeCommandToRecipe = recipeCommandToRecipe;
		this.recipeToRecipeCommand = recipeToRecipeCommand;
	}

	@Override
	public Set<Recipe> getRecipes() {
		var recipes = new HashSet<Recipe>();
		recipeRepository.findAll().iterator().forEachRemaining(recipes::add); // Java 8 Syntax!
		return recipes;
	}

	@Override
	public Recipe findById(Long id) {
		return recipeRepository.findById(id).orElseGet(null);
	}

	@Override
	public void deleteById(Long id) {
		recipeRepository.deleteById(id);
	}

	@Override
	public void delete(Recipe recipe) {
		recipeRepository.delete(recipe);

	}

	@Override
	public Recipe save(Recipe recipe) {
		return recipeRepository.save(recipe);
	}

	@Override
	@Transactional //enforce the spring JPA to start of a transaction in case of 
	//any lazy loading occurs out of the context of the transaction 
	public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand) {
		
		//detached from Hibernate context
		//Spring JPA will do the merge if it's a an existing ID entity otherwise it will do the add
		Recipe recipeDetached = recipeCommandToRecipe.convert(recipeCommand);
		var savedRecipe = recipeRepository.save(recipeDetached);
		log.debug("Saved RecipeId : " + savedRecipe.getId());
		return recipeToRecipeCommand.convert(savedRecipe);
	}

}
