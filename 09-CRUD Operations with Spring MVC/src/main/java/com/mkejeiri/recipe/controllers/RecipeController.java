package com.mkejeiri.recipe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.mkejeiri.recipe.command.RecipeCommand;
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
		model.addAttribute("recipe", recipeService.findById(new Long(id)));
		return "recipe/show";
	}

	@RequestMapping("recipe/new")
	public String newRecipe(Model model) {
		model.addAttribute("recipe", new RecipeCommand());

		return "recipe/recipeform";
	}

	@RequestMapping("recipe/{id}/update")
	public String updateRecipe(@PathVariable String id, Model model) {
		model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
		return "recipe/recipeform";
	}

	// this is an old way!
	// @RequestMapping(value="recipe", method = RequestMethod.POST)

	@PostMapping() // spring 4.3 annotations
	@RequestMapping("recipe")
	///this method is also used for the update, spring is smart enough to know if we are doing an update base on the provided Id property
	//i.e. id = null or empty string => is an insert, existing id is an update
	public String saveOrUpdate(@ModelAttribute // allow us to do the binding from the form into the RecipeCommand object
	RecipeCommand command) {
		RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);

		// tells spring to redirect to "/recipe/show/id" url
		return "redirect:/recipe/" + savedCommand.getId() + "/show";
	}
	
	@GetMapping
    @RequestMapping("recipe/{id}/delete")
    public String deleteById(@PathVariable String id){

        log.debug("Deleting id: " + id);

        recipeService.deleteById(Long.valueOf(id));
        return "redirect:/";
    }
}
