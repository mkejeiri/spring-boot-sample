package com.mkejeiri.sfdi.controllers;

import org.springframework.stereotype.Controller;

import com.mkejeiri.services.GreetingService;

@Controller
public class MyController {

	private final GreetingService greetingService;

	public MyController(GreetingService greetingService) {		
		this.greetingService = greetingService;
	}
	
	public String getGreeting() {
		return greetingService.sayGreeting();
	}
	
//	public String SayHello() {
//		System.out.println("Hello world!");
//		return "Hi folks from SayHello !";
//	}
}
