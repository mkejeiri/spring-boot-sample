package com.mkejeiri.recipe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mkejeiri.recipe.services.RecipeService;
import com.mkejeiri.recipe.services.RecipeServiceImpl;

import lombok.extern.slf4j.Slf4j;

//@Slf4j: this inject a logger, it is the logging facade which gives access to the default Spring Boot logger which is the logback
@Slf4j //  using logging from lombok, we get a log property (see Outline)

@Controller
public class IndexController {

	private final RecipeService recipeService;

	public IndexController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}

//	private final UnitOfMeasureRepository unitOfMeasureRepository;
//	private final CategoryRepository categoryRepository;
//	
//	public IndexController(UnitOfMeasureRepository unitOfMeasureRepository, CategoryRepository categoryRepository) {
//		this.unitOfMeasureRepository = unitOfMeasureRepository;
//		this.categoryRepository = categoryRepository;
//	}

	@RequestMapping({"","/","/index","/index.html"})
	public String getIndexPage(Model model) {
		log.debug("Getting Index Page...");
		
//		System.out.println(categoryRepository.findByDescription("American"));
//		System.out.println(unitOfMeasureRepository.findByDescription("Teaspoon"));
		
		model.addAttribute("recipes",recipeService.getRecipes());
//		System.out.println("some messsage to see");
		return "index";
	}
}
