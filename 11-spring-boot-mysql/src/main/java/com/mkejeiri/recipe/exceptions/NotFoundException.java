package com.mkejeiri.recipe.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
//This annotation will change the status code coming to browser from 500 to 404 not found
//it allows us to pass also the test RecipeControllerTest.testGetRecipeNotFound
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException  extends RuntimeException {

	public NotFoundException() {
		super();
	}

	public NotFoundException(String message) {
		super(message);
	}

	public NotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotFoundException(Throwable cause) {
		super(cause);
	}
	
	
	
	

}
