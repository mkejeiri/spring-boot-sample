package com.mkejeiri.recipe.controllers;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;

import com.mkejeiri.recipe.config.WebConfig;
import com.mkejeiri.recipe.domain.Recipe;
import com.mkejeiri.recipe.services.RecipeService;

import reactor.core.publisher.Flux;

public class RouterFunctionTest {

	WebTestClient webTestClient;

	@Mock
	RecipeService recipeService;

	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		WebConfig webConfig = new WebConfig();

		// building the router function for testing purposes!
		RouterFunction<?> routerFunction = webConfig.routes(recipeService);

		webTestClient = WebTestClient.bindToRouterFunction(routerFunction).build();
	}

	@Test
	public void testGetRecipes() throws Exception {

		when(recipeService.getRecipes())
		.thenReturn(Flux.just()/* Empty flux */);

		webTestClient.get().uri("/api/recipes")
		.accept(MediaType.APPLICATION_JSON)
		.exchange()
		.expectStatus().isOk();
	}

	@Test
	public void testGetRecipesWithData() throws Exception {

		when(recipeService.getRecipes()).thenReturn(Flux.just(new Recipe(), new Recipe()));

		webTestClient.get().uri("/api/recipes")
		.accept(MediaType.APPLICATION_JSON)
		.exchange().expectStatus().isOk()
		.expectBodyList(Recipe.class);
	}
}
