package com.mkejeiri.greeting.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import com.mkejeiri.services.GreetingRepository;
import com.mkejeiri.services.GreetingService;
import com.mkejeiri.services.GreetingServiceFactory;

@Configuration //step 1
public class GreetingServiceConfig {

	@Bean //step 2
	GreetingServiceFactory getGreetingServiceFactoryInstance(GreetingRepository greetingRepository) {
		return new GreetingServiceFactory(greetingRepository);
	}
	//Declare all services with their profiles here! (DO NOT FORGET to remove @service, @Primary and @Profile from all of them!)
	@Bean
	@Primary
	@Profile({"en", "default"})
	GreetingService primaryGreetingService(GreetingServiceFactory greetingServiceFactory) {
		return greetingServiceFactory.createGreetingService("en");
	}
	
	
	@Bean
	@Profile("es")
	@Primary
	GreetingService primarySpanishGreetingService(GreetingServiceFactory greetingServiceFactory) {
		return greetingServiceFactory.createGreetingService("es");
	}
	
	@Bean
	@Profile("de")
	@Primary
	GreetingService primaryGermanGreetingService(GreetingServiceFactory greetingServiceFactory) {
		return greetingServiceFactory.createGreetingService("de");
	}
}
