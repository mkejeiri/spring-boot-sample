package com.mkejeiri.greeting.controllers;

//import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.mkejeiri.services.GreetingService;

@Controller
public class MyController {

    private GreetingService greetingService;

    public MyController(/*@Qualifier("greetingServiceImpl")*/ GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    public String hello(){
        System.out.println("Hello!!! ");

        return greetingService.sayGreeting();
    }
}