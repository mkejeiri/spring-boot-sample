package com.mkejeiri.recipe.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@ControllerAdvice // allows us to use exactly the same exception handling techniques but apply
					// them across the whole application, not just to an individual controller. We
					// can think of them as an annotation driven interceptor.

//DO NOT FORGET TO to wire ControllerAdvice in mockMvc for the test to work!
public class ExceptionHandlerController {

	@ResponseStatus(HttpStatus.BAD_REQUEST) // in case of error @ExceptionHandler will redirect us here.
	// without @ResponseStatus(HttpStatus.BAD_REQUEST), we will get a 200
	// HttpStatus.OK in the browser which
	// is semantically wrong and test
	// RecipeControllerTest.testGetRecipeNumberFormatException will fail,
	// because it's expects HttpStatus.BAD_REQUEST.@ExceptionHandler is taking
	// precedence over all exception class
	@ExceptionHandler(NumberFormatException.class)
    public ModelAndView handleNumberFormat(Exception exception){

        log.error("Handling Number Format Exception");
        log.error(exception.getMessage());

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("400error");
        modelAndView.addObject("exception", exception);

        return modelAndView;
    }

}
