package com.mkejeiri.recipe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.mkejeiri.recipe.commands.IngredientCommand;
import com.mkejeiri.recipe.commands.UnitOfMeasureCommand;
import com.mkejeiri.recipe.services.IngredientService;
import com.mkejeiri.recipe.services.RecipeService;
import com.mkejeiri.recipe.services.UnitOfMeasureService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
@Controller
public class IngredientController {

	private final IngredientService ingredientService;
	private final RecipeService recipeService;
	private final UnitOfMeasureService unitOfMeasureService;

	private WebDataBinder webDataBinder;

	public IngredientController(IngredientService ingredientService, RecipeService recipeService,
			UnitOfMeasureService unitOfMeasureService) {
		this.ingredientService = ingredientService;
		this.recipeService = recipeService;
		this.unitOfMeasureService = unitOfMeasureService;
	}

	@InitBinder("ingredient") // we wired up a ingredient data binder otherwise in post
	// we aren't sure which get the bound and the validation doesn't work properly
	public void initBinder(WebDataBinder webDataBinder) {
		this.webDataBinder = webDataBinder;
	}

	@GetMapping("/recipe/{recipeId}/ingredients")
	public String listIngredients(@PathVariable String recipeId, Model model) {
		log.debug("Getting ingredient list for recipe id: " + recipeId);

		// use command object to avoid lazy load errors in Thymeleaf.
		model.addAttribute("recipe", recipeService.findCommandById(recipeId));

		return "recipe/ingredient/list";
	}

	@GetMapping("recipe/{recipeId}/ingredient/{id}/show")
	public String showRecipeIngredient(@PathVariable String recipeId, @PathVariable String id, Model model) {
		model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(recipeId, id));
		return "recipe/ingredient/show";
	}

	@GetMapping("recipe/{recipeId}/ingredient/new")
	public String newRecipe(@PathVariable String recipeId, Model model) {

		recipeService.findCommandById(recipeId).block();

		// need to return back parent id for hidden form property
		IngredientCommand ingredientCommand = new IngredientCommand();
		model.addAttribute("ingredient", ingredientCommand);

		// init uom
		ingredientCommand.setUom(new UnitOfMeasureCommand());

		return "recipe/ingredient/ingredientform";
	}

	@GetMapping("recipe/{recipeId}/ingredient/{id}/update")
	public String updateRecipeIngredient(@PathVariable String recipeId, @PathVariable String id, Model model) {
		model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(recipeId, id).block());
		return "recipe/ingredient/ingredientform";
	}

	@PostMapping("recipe/{recipeId}/ingredient")
	public String saveOrUpdate(// @Valid : this doesn't work we went WebDataBinder approach!
			@ModelAttribute("ingredient") // for every incoming request into controller method we fill in command with
											// "ingredient" model property data coming from the form
			IngredientCommand command) {

		// using WebDataBinder triggers the validation and check BindingResult!
		webDataBinder.validate();
		BindingResult bindingResult = webDataBinder.getBindingResult();

		if (bindingResult.hasErrors()) {

			bindingResult.getAllErrors().forEach(objectError -> {
				log.debug(objectError.toString());
			});

			return "recipe/ingredient/ingredientform";
		}

		IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command).block();

		log.debug("saved ingredient id:" + savedCommand.getId());

		return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredient/" + savedCommand.getId() + "/show";
	}

	@GetMapping("recipe/{recipeId}/ingredient/{id}/delete")
	public String deleteIngredient(@PathVariable String recipeId, @PathVariable String id) {

		log.debug("deleting ingredient id:" + id);
		ingredientService.deleteById(recipeId, id).block();

		return "redirect:/recipe/" + recipeId + "/ingredients";
	}

	// with every request "uomList" property get filled in inside the exchanged
	// model every time it returns to a view, so "uomList" property becomes
	// populated and
	// available to the view! => NOT A FLUX SPECIFIC works also in spring-MVC as
	// well!
	@ModelAttribute("uomList") // for going out answering a request!
	public Flux<UnitOfMeasureCommand> populateUomList() {
		return unitOfMeasureService.listAllUoms();
	}
}
