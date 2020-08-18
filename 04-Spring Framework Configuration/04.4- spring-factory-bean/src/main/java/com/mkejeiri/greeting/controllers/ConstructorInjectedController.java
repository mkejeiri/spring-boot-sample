package com.mkejeiri.greeting.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.mkejeiri.services.GreetingService;

@Controller
public class ConstructorInjectedController {

    private GreetingService greetingService;

    //DI through constructor doesn't need to mention @Autowired!
    public ConstructorInjectedController(@Qualifier("constructorGreetingService") GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    public String sayHello(){
        return greetingService.sayGreeting();
    }
}
