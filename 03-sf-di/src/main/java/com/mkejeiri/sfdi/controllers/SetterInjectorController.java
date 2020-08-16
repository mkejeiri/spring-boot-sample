package com.mkejeiri.sfdi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.mkejeiri.sfdi.services.GreetingService;

@Controller
public class SetterInjectorController {
	@Autowired
	@Qualifier("setterGreetingServiceImpl") //uses setterGreetingServiceImpl bean, camelCase PLZ! since it's fetching the name of the bean
	private GreetingService greetingService;

	public void setGreetingService(GreetingService greetingService) {
		this.greetingService = greetingService;
	}
	
	public String getGreeting() {
		return greetingService.sayGreeting();
	}

}
