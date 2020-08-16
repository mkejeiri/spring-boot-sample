package com.mkejeiri.sfdi.services;

import org.springframework.stereotype.Service;

@Service //spring managed components
public class ConstructorGreetingServiceImpl implements GreetingService {

	@Override
	public String sayGreeting() {
		return "Hello world - Constructor";
	}
}