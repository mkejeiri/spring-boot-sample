/**
 * 
 */
package com.mkejeiri.sfdi.controllers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mkejeiri.sfdi.services.ConstructorGreetingServiceImpl;

class PropertyInjectorControllerTest {
	PropertyInjectorController controller;

	@BeforeEach
	void setUp() {
		controller = new PropertyInjectorController();
		controller.greetingService = new ConstructorGreetingServiceImpl();
	}

	@Test
	void getGreeting() {
		System.out.println(controller.getGreeting());
	}

}
