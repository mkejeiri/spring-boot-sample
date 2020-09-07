package com.mkejeiri.recipe.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.support.WebExchangeBindException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

	// WebExchangeBindException will be raise in case of the form expected data
	// type mismatch (e.g. string instead of int), this will happen at
	// the framework level, i.e. before we could reach the controller
	// post method, (e.g. the BindingResult for validation)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(WebExchangeBindException.class)
	public String handleNumberFormat(Exception exception, Model model) {

		log.error("Handling Binding Exception");
		log.error(exception.getMessage());

		model.addAttribute("exception", exception);

		return "400error";
	}
}
