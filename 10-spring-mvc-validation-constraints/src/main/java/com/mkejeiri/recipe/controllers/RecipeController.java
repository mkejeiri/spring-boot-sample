package com.mkejeiri.recipe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.http.HttpStatus;

import com.mkejeiri.recipe.command.RecipeCommand;
import com.mkejeiri.recipe.exceptions.NotFoundException;
import com.mkejeiri.recipe.services.RecipeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class RecipeController {
	private final RecipeService recipeService;

	public RecipeController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}

	// oldway
	// @RequestMapping(value = "recipe/show/{id}", method = RequestMethod.GET,
	// produces = { MediaType.APPLICATION_JSON_VALUE })

	@GetMapping("recipe/{id}/show") // spring 4.3 annotations
	public String showById(@PathVariable String id, Model model) {
		model.addAttribute("recipe", recipeService.findById(Long.parseLong(id)));
		return "recipe/show";
	}

	@GetMapping("recipe/new")
	public String newRecipe(Model model) {
		model.addAttribute("recipe", new RecipeCommand());

		return "recipe/recipeform";
	}

	@GetMapping("recipe/{id}/update")
	public String updateRecipe(@PathVariable String id, Model model) {
		model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
		return "recipe/recipeform";
	}

	// this is an old way!
	// @RequestMapping(value="recipe", method = RequestMethod.POST)

	@PostMapping("recipe") // spring 4.3 annotations
	// @RequestMapping("recipe")//this is already included in @GetMapping
	/// this method is also used for the update, spring is smart enough to know if
	// we are doing an update base on the provided Id property
	// i.e. id = null or empty string => is an insert, existing id is an update
	public String saveOrUpdate(@ModelAttribute // allow us to do the binding from the form into the RecipeCommand object
	RecipeCommand command) {
		RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);

		// tells spring to redirect to "/recipe/show/id" url
		return "redirect:/recipe/" + savedCommand.getId() + "/show";
	}

	@GetMapping("recipe/{id}/delete")
	// @RequestMapping("recipe/{id}/delete")//this is already included in
	// @GetMapping
	public String deleteById(@PathVariable String id) {

		log.debug("Deleting id: " + id);

		recipeService.deleteById(Long.valueOf(id));
		return "redirect:/";
	}

	@ResponseStatus(HttpStatus.NOT_FOUND) // in case of error @ExceptionHandler will redirect us here.
											// without @ResponseStatus(HttpStatus.NOT_FOUND), we will get a 200
											// HttpStatus.OK in the browser which
											// is semantically wrong and test RecipeControllerTest.testGetRecipeNotFound
											// will fail,
											// because it's expects HttpStatus.NOT_FOUND.
	// even if we already used @ResponseStatus(HttpStatus.NOT_FOUND) annotation in
	// NotFoundException class,
	// the @ExceptionHandler is taking precedence and overrides
	// NotFoundException.class annotation,
	// that why we need to annotate handleNotFound() with
	// @ResponseStatus(HttpStatus.NOT_FOUND).
	@ExceptionHandler(NotFoundException.class)
	public ModelAndView handleNotFound(Exception exception) {

		log.error("Handling not found exception");
		log.error(exception.getMessage());

		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("404error");
		modelAndView.addObject("exception", exception);

		return modelAndView;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST) // in case of error @ExceptionHandler will redirect us here.
	// without @ResponseStatus(HttpStatus.BAD_REQUEST), we will get a 200
	// HttpStatus.OK in the browser which
	// is semantically wrong and test
	// RecipeControllerTest.testGetRecipeNumberFormatException will fail,
	// because it's expects HttpStatus.BAD_REQUEST.@ExceptionHandler is taking
	// precedence over all exception class
	@ExceptionHandler(NumberFormatException.class)
	public ModelAndView handleNumberFormatException(Exception exception) {

		log.error("Handling not NumberFormat Exception");
		log.error(exception.getMessage());

		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("400error");
		modelAndView.addObject("exception", exception);

		return modelAndView;
	}
}
