package com.mkejeiri.recipe.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.mockito.Matchers.anyLong;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mkejeiri.recipe.domain.Recipe;
import com.mkejeiri.recipe.services.RecipeService;

@ExtendWith(MockitoExtension.class)
class RecipeControllerTest {
	public static final Long ID = 1L;
	@Mock
	private RecipeService recipeService;

	@InjectMocks
	private RecipeController controller;

	@Mock
	private Model model;

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	final void testGetRecipe() throws Exception {
		Recipe recipe = new Recipe();
		recipe.setId(ID);
		// recipeService.save(recipe);
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

		when(recipeService.findById(anyLong())).thenReturn(recipe);
		mockMvc.perform(get("/recipe/show/" + ID))
		.andExpect(status().isOk())
		.andExpect(view().name("recipe/show"))
		.andExpect(model().attributeExists("recipe"));
	}

}
