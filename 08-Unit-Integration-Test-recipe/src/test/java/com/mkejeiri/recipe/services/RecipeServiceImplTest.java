package com.mkejeiri.recipe.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mkejeiri.recipe.domain.Recipe;
import com.mkejeiri.recipe.repositories.RecipeRepository;

public class RecipeServiceImplTest {
	RecipeServiceImpl recipeService;
	
	@Mock
	RecipeRepository recipeRepository;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		recipeService = new RecipeServiceImpl(recipeRepository); 
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testGetRecipes() {
		//fail("Not yet implemented");
		
		//Set up data
		Recipe recipe = new Recipe();
		Set<Recipe> recipesData = new HashSet<Recipe>();
		recipesData.add(recipe);
		
		//when(recipeService.getRecipes()).thenReturn(recipesData);
		when(recipeRepository.findAll()).thenReturn(recipesData);
		
		Set<Recipe> recipes = recipeService.getRecipes();
		assertEquals(recipes.size(), 1);
		verify(recipeRepository, times(1)).findAll();
	}

}
