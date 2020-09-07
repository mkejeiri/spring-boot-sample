package com.mkejeiri.recipe.config;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.mkejeiri.recipe.domain.Recipe;
import com.mkejeiri.recipe.services.RecipeService;

//Spring WebTestClient exposes a RestApi, oriented toward µservices, 
//we should configure a route for a get request at this url "/api/recipes"
@Configuration
public class WebConfig {

	@Bean
	public RouterFunction<?> routes(RecipeService recipeService) {
		return RouterFunctions.route(GET("/api/recipes"), 
				serverRequest -> ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(recipeService.getRecipes(), Recipe.class));

	}
}
