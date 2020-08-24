package com.mkejeiri.recipe.controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import com.mkejeiri.recipe.domain.Recipe;
import com.mkejeiri.recipe.services.RecipeService;

public class IndexControllerTest {
	
	@Mock
	private RecipeService recipeService;
	
	@Mock
	private Model model;
	
	public IndexController indexController; 
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		indexController = new IndexController(recipeService);		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testGetIndexPage() {
		//fail("Not yet implemented"); // TODO
		
		//given
		
		Set<Recipe> recipes = new HashSet<Recipe>();
		//this one won't be added since a HashSet has unique element (2 recipe with ID = 0))
		//need to setId to get a second one 
		recipes.add(new Recipe());		
		Recipe recipe = new Recipe();
		recipe.setId(1L);		
		recipes.add(recipe);
						
		//when
		when(recipeService.getRecipes()).thenReturn(recipes);
		
		//this an argument matcher: it create an  argument for the class set		
		@SuppressWarnings("unchecked")
		ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);
		
//		ArgumentCaptor<Person> varArgs = ArgumentCaptor.forClass(Person.class);
//		   verify(mock).varArgMethod(varArgs.capture());
//		   List expected = asList(new Person("John"), new Person("Jane"));
//		   assertEquals(expected, varArgs.getAllValues());

		
		//then		
		String viewName = indexController.getIndexPage(model);
		assertEquals("index", viewName);
		verify(recipeService, times(1)).getRecipes();
//		verify(model, times(1)).addAttribute(org.mockito.Mockito.eq("recipes"), org.mockito.Mockito.anySet());
		verify(model, times(1)).addAttribute(org.mockito.Mockito.eq("recipes"), argumentCaptor.capture());
		Set<Recipe> setInController = argumentCaptor.getValue();
		assertEquals(2, setInController.size());
		
	}
	
	@Test
	public void testMockMvc() throws Exception {
		MockMvc mockMvc =  MockMvcBuilders.standaloneSetup(indexController).build();
		mockMvc.perform(get("/"))
		.andExpect(status().isOk())
		.andExpect(view().name("index"));		
	}
}
