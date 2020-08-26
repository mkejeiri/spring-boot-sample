package com.mkejeiri.recipe.services;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyLong;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mkejeiri.recipe.domain.Recipe;
import com.mkejeiri.recipe.repositories.RecipeRepository;

@ExtendWith(MockitoExtension.class)
public class RecipeServiceImplTest {
	static final Long ID = 1L;
	@Mock
	RecipeRepository recipeRepository;

	@InjectMocks
	RecipeServiceImpl recipeService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testGetRecipes() {
		// fail("Not yet implemented");

		// Set up data
		Recipe recipe = new Recipe();
		Set<Recipe> recipesData = new HashSet<Recipe>();
		recipesData.add(recipe);

		// when(recipeService.getRecipes()).thenReturn(recipesData);
		when(recipeRepository.findAll()).thenReturn(recipesData);

		Set<Recipe> recipes = recipeService.getRecipes();
		assertEquals(recipes.size(), 1);
		verify(recipeRepository, times(1)).findAll();
	}

	@Test
	final void testFindById() {
		
		Recipe recipe = new Recipe();
		recipe.setId(ID);

		Optional<Recipe> optionalRecipe = Optional.of(recipe);
		when(recipeRepository.findById(anyLong())).thenReturn(optionalRecipe);
		
		Recipe returnedRecipe = recipeService.findById(ID);
		
		assertNotNull("Null recipe returned",returnedRecipe);
		verify(recipeRepository, times(1)).findById(anyLong());
		verify(recipeRepository, never()).findAll();
		//assertEquals(ID, returnedRecipe.getId());

	}

	@Test
	final void testDeleteById() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testDelete() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testSave() {
		fail("Not yet implemented"); // TODO
	}

}
