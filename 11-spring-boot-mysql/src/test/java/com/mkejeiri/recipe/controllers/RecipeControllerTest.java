package com.mkejeiri.recipe.controllers;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mkejeiri.recipe.commands.RecipeCommand;
import com.mkejeiri.recipe.domain.Recipe;
import com.mkejeiri.recipe.exceptions.NotFoundException;
import com.mkejeiri.recipe.services.RecipeService;

@ExtendWith(MockitoExtension.class)
class RecipeControllerTest {
	public static final Long ID = 1L;
	@Mock
	RecipeService recipeService;

	RecipeController controller;

	MockMvc mockMvc;	

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		controller = new RecipeController(recipeService);
		mockMvc = MockMvcBuilders.standaloneSetup(controller)
				.setControllerAdvice(new ExceptionHandlerController()) //rely not on spring context to keep unit test lightweight
				.build();
	}

	 @Test
	    public void testGetRecipeNotFound() throws Exception {

	        when(recipeService.findById(anyLong()))
	        .thenThrow(NotFoundException.class);

	        mockMvc.perform(get("/recipe/1/show"))
	        .andExpect(status().isNotFound())
	        .andExpect(view().name("404error"));
	    }
	 
	 @Test //need to wire ControllerAdvice in mockMvc
	    public void testGetRecipeNumberFormatException() throws Exception {

		 //this commented out because the exception will pop up because getting into RecipeService 
		 //when(recipeService.findById(anyLong())).thenThrow(NotFoundException.class);		 
	        mockMvc.perform(get("/recipe/aaa/show"))
	        .andExpect(status().isBadRequest())
	        .andExpect(view().name("400error"));
	    }
	
	
	@Test
	final void testGetRecipe() throws Exception {
		Recipe recipe = new Recipe();
		recipe.setId(ID);
		// recipeService.save(recipe);
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

		when(recipeService.findById(anyLong())).thenReturn(recipe);
		mockMvc.perform(get("/recipe/"+ ID + "/show" )).andExpect(status().isOk())
		.andExpect(view().name("recipe/show"))
		.andExpect(model().attributeExists("recipe"));
	}

	@Test
	public void testGetUpdateView() throws Exception {
		RecipeCommand command = new RecipeCommand();
		command.setId(2L);

		when(recipeService.findCommandById(anyLong())).thenReturn(command);

		mockMvc.perform(get("/recipe/1/update"))
		.andExpect(status().isOk())
		.andExpect(view().name("recipe/recipeform"))
		.andExpect(model().attributeExists("recipe"));
	}
	
	
	@Test
    public void testDeleteAction() throws Exception {
		
		mockMvc.perform(get("/recipe/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
        
        verify(recipeService, times(1)).deleteById(anyLong());
    }
	
	@Test
	public void testPostNewRecipeFormValidationFail() throws Exception {
		RecipeCommand command = new RecipeCommand();
		command.setId(2L);
		command.setDescription("some description");
		command.setDirections("some directions");

		//an unnecessary stub: is a stubbed method call that was never realized during test execution
		//more : https://www.baeldung.com/mockito-unnecessary-stubbing-exception
		//when(recipeService.saveRecipeCommand(any())).thenReturn(command);

		mockMvc.perform(post("/recipe")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("id", ""))
		
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("recipe"))
		.andExpect(view().name("recipe/recipeform"));
	}
	
	@Test
    public void testPostNewRecipeForm() throws Exception {
        RecipeCommand command = new RecipeCommand();
        command.setId(2L);

        when(recipeService.saveRecipeCommand(any())).thenReturn(command);

        mockMvc.perform(post("/recipe")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("description", "some string")
                .param("directions", "some directions")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/2/show"));
    }
}
