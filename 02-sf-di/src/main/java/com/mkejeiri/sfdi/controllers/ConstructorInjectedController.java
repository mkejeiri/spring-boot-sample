package com.mkejeiri.sfdi.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.mkejeiri.sfdi.services.GreetingService;

@Controller
public class ConstructorInjectedController {
	//Autowired is optional for constructor injection property since spring 4!=> UNLIKE the SETTER and the GETTER injection!!!!
	private final GreetingService greetingService;

	//@Qualifier allow us to know which type of bean to inject here constructorGreetingServiceImpl 
	//camelCase PLZ! since it's fetching the name of the bean
	public ConstructorInjectedController(@Qualifier("constructorGreetingServiceImpl") GreetingService greetingService) {
		this.greetingService = greetingService;
	};

	public String getGreeting() {
		return greetingService.sayGreeting();
	}
}
