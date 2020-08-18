package com.mkejeiri.sfdi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.mkejeiri.services.GreetingService;

@Controller
public class PropertyInjectorController {

	@Autowired // tells spring to inject an instance of GreetingServiceImpl, we should also
				// tells that GreetingServiceImpl is a Service
	@Qualifier("propertyGreetingServiceImpl") // use propertyGreetingServiceImpl bean, camelCase PLZ! since it's fetching the name of the bean
	public GreetingService greetingService;

	public String getGreeting() {
		return greetingService.sayGreeting();
	}
}
