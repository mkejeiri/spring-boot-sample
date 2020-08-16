package com.mkejeiri.sfdi.controllers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mkejeiri.sfdi.services.GreetingService;
import com.mkejeiri.sfdi.services.ConstructorGreetingServiceImpl;

class ContructorInjectedControllerTest {

	ConstructorInjectedController controller;
	@BeforeEach
	void setUp() {
		controller = new ConstructorInjectedController(new ConstructorGreetingServiceImpl());
	}

	@Test
	void getGreeting() {
		System.out.println(controller.getGreeting());
	}

}
