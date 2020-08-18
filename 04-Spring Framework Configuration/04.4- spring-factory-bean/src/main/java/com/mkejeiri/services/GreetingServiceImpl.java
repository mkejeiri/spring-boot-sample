package com.mkejeiri.services;

import org.springframework.stereotype.Service;

@Service
public class GreetingServiceImpl implements GreetingService {

    public static final String HELLO_DI = "Hello Factory Bean!!!! - Original";

    @Override
    public String sayGreeting() {
        return HELLO_DI;
    }
}