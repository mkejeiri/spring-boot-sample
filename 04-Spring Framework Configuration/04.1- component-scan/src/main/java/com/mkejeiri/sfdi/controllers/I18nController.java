package com.mkejeiri.sfdi.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;import org.springframework.stereotype.Service;

import com.mkejeiri.services.GreetingService;

@Controller
//I18n : internationalisation 
public class I18nController {
	// Autowired is optional for constructor injection property since spring 4!=>
	// UNLIKE the SETTER and the GETTER injection!!!!
	private final GreetingService greetingService;

	public I18nController(@Qualifier("i18nService") GreetingService greetingService) {
		this.greetingService = greetingService;
	}

	public String getGreeting() {
		return greetingService.sayGreeting();
	}
}
