package com.mkejeiri.recipe.services;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mkejeiri.recipe.command.RecipeCommand;
import com.mkejeiri.recipe.converters.RecipeCommandToRecipe;
import com.mkejeiri.recipe.converters.RecipeToRecipeCommand;
import com.mkejeiri.recipe.domain.Recipe;
import com.mkejeiri.recipe.exceptions.NotFoundException;
import com.mkejeiri.recipe.repositories.RecipeRepository;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class RecipeServiceImplTest {
	static final Long ID = 1L;
	
	RecipeService recipeService;
	
    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    
	@BeforeEach
	public void setUp() throws Exception {
        recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
	}
    
	@Test
    public void getRecipeByIdTest() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        Recipe recipeReturned = recipeService.findById(1L);

        assertNotNull("Null recipe returned", recipeReturned);
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();
    }

    @Test()
    public void getRecipeByIdTestNotFound() throws Exception {

        Optional<Recipe> recipeOptional = Optional.empty();

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);   

        
        Assertions.assertThrows(NotFoundException.class, () -> {
        	recipeService.findById(1L);
          });
    }

    @Test
    public void getRecipeCommandByIdTest() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);

        when(recipeToRecipeCommand.convert(any())).thenReturn(recipeCommand);

        RecipeCommand commandById = recipeService.findCommandById(1L);

        assertNotNull("Null recipe returned", commandById);
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();
    }

    @Test
    public void getRecipesTest() throws Exception {

        Recipe recipe = new Recipe();
        HashSet<Recipe> receipesData = new HashSet<Recipe>();
        receipesData.add(recipe);

        when(recipeService.getRecipes()).thenReturn(receipesData);

        Set<Recipe> recipes = recipeService.getRecipes();

        assertEquals(recipes.size(), 1);
        verify(recipeRepository, times(1)).findAll();
        verify(recipeRepository, never()).findById(anyLong());
    }

    @Test
    public void testDeleteById() throws Exception {

        //given
        Long idToDelete = Long.valueOf(2L);

        //when
        recipeService.deleteById(idToDelete);

        //no 'when', since method has void return type

        //then
        verify(recipeRepository, times(1)).deleteById(anyLong());
    }
	
//	@BeforeClass
//	public static void setUpBeforeClass() throws Exception {
//	}
//
//	@AfterClass
//	public static void tearDownAfterClass() throws Exception {
//	}
//
//	@Before
//	public void setUp() throws Exception {
//	}
//
//	@After
//	public void tearDown() throws Exception {
//	}

//	@Test
//	public final void testGetRecipes()  throws Exception{
//		// fail("Not yet implemented");
//
//		// Set up data
//		Recipe recipe = new Recipe();
//		Set<Recipe> recipesData = new HashSet<Recipe>();
//		recipesData.add(recipe);
//
//		// when(recipeService.getRecipes()).thenReturn(recipesData);
//		when(recipeRepository.findAll()).thenReturn(recipesData);
//
//		Set<Recipe> recipes = recipeService.getRecipes();
//		assertEquals(recipes.size(), 1);
//		verify(recipeRepository, times(1)).findAll();
//	}
//
//	@Test
//	final void testFindById()  throws Exception{
//		
//		Recipe recipe = new Recipe();
//		recipe.setId(ID);
//
//		Optional<Recipe> optionalRecipe = Optional.of(recipe);
//		when(recipeRepository.findById(anyLong())).thenReturn(optionalRecipe);
//		
//		Recipe returnedRecipe = recipeService.findById(ID);
//		
//		assertNotNull("Null recipe returned",returnedRecipe);
//		verify(recipeRepository, times(1)).findById(anyLong());
//		verify(recipeRepository, never()).findAll();
//		//assertEquals(ID, returnedRecipe.getId());
//
//	}
//
//	@Test
//	final void testDeleteById() throws Exception{
//		
//		//given
//		Long idToDelete = 2L;
//		recipeService.deleteById(idToDelete);
//		
//		//!!!!NO when : it a void, doesn't return a value
//		
//		//then
//		verify(recipeRepository, times(1)).deleteById(anyLong());		
//	}
//
//	@Test
//	final void testDelete() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	@Test
//	final void testSave() {
//		fail("Not yet implemented"); // TODO
//	}

}
