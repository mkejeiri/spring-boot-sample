package com.mkejeiri.sfdi.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile({"EN","default"})
//@Profile("EN") //don't forget to set which active profile in resources/properties file.
@Service("i18nService")
public class I18nEnglishGreetingService implements GreetingService {

	@Override
	public String sayGreeting() {
		return "Hello world-- I18nEnglish Service";
	}
}
