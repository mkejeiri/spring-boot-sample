package com.mkejeiri.sfdi.controllers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mkejeiri.services.ConstructorGreetingServiceImpl;
import com.mkejeiri.services.GreetingService;

class SetterInjectorControllerTest {

	SetterInjectorController  controller;
	@BeforeEach
	void setUp()  {
		controller = new SetterInjectorController();
		controller.setGreetingService(new ConstructorGreetingServiceImpl()) ;
	}

	@Test
	void getGreeting() {
		System.out.println(controller.getGreeting());
	}

}
