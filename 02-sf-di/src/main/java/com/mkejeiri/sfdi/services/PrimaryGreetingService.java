package com.mkejeiri.sfdi.services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary //Primary bean service, or default if not specified with the qualifier!
public class PrimaryGreetingService implements GreetingService {

	@Override
	public String sayGreeting() {
		return "Hello world-- From Primary bean service or default if not specified with the qualifier!";
	}

}
