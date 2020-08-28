package com.mkejeiri.recipe.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.mockito.Mockito.*;
import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Matchers.anyLong;

import com.mkejeiri.recipe.command.IngredientCommand;
import com.mkejeiri.recipe.command.RecipeCommand;
import com.mkejeiri.recipe.domain.Recipe;
import com.mkejeiri.recipe.services.IngredientService;
import com.mkejeiri.recipe.services.RecipeService;
import com.mkejeiri.recipe.services.UnitOfMeasureService;

class IngredientControllerTest {
	@Mock
	IngredientService ingredientService;
	@Mock
	RecipeService recipeService;
	
	@Mock
    UnitOfMeasureService unitOfMeasureService;

	IngredientController controller;

	MockMvc mockMvc;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		controller = new IngredientController(ingredientService, recipeService, unitOfMeasureService);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	final void testListIngredients() throws Exception {
		// given
		RecipeCommand recipeCommand = new RecipeCommand();
		when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);

		// when
		mockMvc.perform(get("/recipe/1/ingredients")).andExpect(status().isOk())
				.andExpect(view().name("recipe/ingredient/list")).andExpect(model().attributeExists("recipe"));

		// then
		verify(recipeService, times(1)).findCommandById(anyLong());
	}

	@Test
	public void testShowIngredient() throws Exception {
		// given
		IngredientCommand ingredientCommand = new IngredientCommand();

		// when
		when(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(ingredientCommand);

		// then
		mockMvc.perform(get("/recipe/1/ingredient/2/show")).andExpect(status().isOk())
				.andExpect(view().name("recipe/ingredient/show")).andExpect(model().attributeExists("ingredient"));
	}
	
	  @Test
	    public void testUpdateIngredientForm() throws Exception {
	        //given
	        IngredientCommand ingredientCommand = new IngredientCommand();

	        //when
	        when(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(ingredientCommand);
	        when(unitOfMeasureService.listAllUoms()).thenReturn(new HashSet<>());

	        //then
	        mockMvc.perform(get("/recipe/1/ingredient/2/update"))
	                .andExpect(status().isOk())
	                .andExpect(view().name("recipe/ingredient/ingredientform"))
	                .andExpect(model().attributeExists("ingredient"))
	                .andExpect(model().attributeExists("uomList"));
	    }

	    @Test
	    public void testSaveOrUpdate() throws Exception {
	        //given
	        IngredientCommand command = new IngredientCommand();
	        command.setId(3L);
	        command.setRecipeId(2L);

	        //when
	        when(ingredientService.saveIngredientCommand(any())).thenReturn(command);

	        //then
	        mockMvc.perform(post("/recipe/2/ingredient")
	                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
	                .param("id", "")
	                .param("description", "some string")
	        )
	                .andExpect(status().is3xxRedirection())
	                .andExpect(view().name("redirect:/recipe/2/ingredient/3/show"));

	    }
	    
	    @Test
	    public void testDeleteIngredient() throws Exception {

	        //then
	        mockMvc.perform(get("/recipe/2/ingredient/3/delete"))
	                .andExpect(status().is3xxRedirection())
	                .andExpect(view().name("redirect:/recipe/2/ingredients"));

	        verify(ingredientService, times(1)).deleteById(anyLong(), anyLong());
	        //verify(recipeService, times(1)).save(any(Recipe.class));

	    }
}
