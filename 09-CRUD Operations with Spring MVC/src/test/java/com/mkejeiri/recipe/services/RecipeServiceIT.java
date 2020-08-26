package com.mkejeiri.recipe.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.mkejeiri.recipe.command.RecipeCommand;
import com.mkejeiri.recipe.converters.RecipeCommandToRecipe;
import com.mkejeiri.recipe.converters.RecipeToRecipeCommand;
import com.mkejeiri.recipe.domain.Recipe;
import com.mkejeiri.recipe.repositories.RecipeRepository;

import static org.junit.Assert.assertEquals;


//todo convert this Junit 4 to 5 
@RunWith(SpringRunner.class)


//@DataJpaTest //is a light weight test and it won't be able to find the service implementation 
//RecipeServiceImpl doesn't get wised up!, only repository does!!!
//that why we use @SpringBootTest next to bring all spring context with the DI
@SpringBootTest
public class RecipeServiceIT {

	public static final String NEW_DESCRIPTION = "New Description";

	@Autowired
	RecipeService recipeService;

	@Autowired
	RecipeRepository recipeRepository;

	@Autowired
	RecipeCommandToRecipe recipeCommandToRecipe;

	@Autowired
	RecipeToRecipeCommand recipeToRecipeCommand;

	@Transactional //we mark testSaveOfDescription as transaction because we are working with 
	//the entities outside the transactional context because we are accessing a lazily loaded properties 
	//and we need to keep that inside the same transactional context!  
	@Test
	public void testSaveOfDescription() throws Exception {
		// given
		Iterable<Recipe> recipes = recipeRepository.findAll();
		
		//pick the first one doens't matter
		Recipe testRecipe = recipes.iterator().next();
		
		//RecipeCommand is a command object (dto), POJO manipulated by the front end!
		RecipeCommand testRecipeCommand = recipeToRecipeCommand.convert(testRecipe);

		// when
		testRecipeCommand.setDescription(NEW_DESCRIPTION);
		RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(testRecipeCommand);

		// then
		assertEquals(NEW_DESCRIPTION, savedRecipeCommand.getDescription());
		assertEquals(testRecipe.getId(), savedRecipeCommand.getId());
		assertEquals(testRecipe.getCategories().size(), savedRecipeCommand.getCategories().size());
		assertEquals(testRecipe.getIngredients().size(), savedRecipeCommand.getIngredients().size());
	}
}