package com.mkejeiri.appjokes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import guru.springframework.norris.chuck.ChuckNorrisQuotes;

//this will create an instance for property JokeServiceImpl.chuckNorrisQuotes, and we have to tell that we need this property as bean (@Bean)
//no need for component scan because AppjokesApplication.java  (through @SpringBootApplication) 
//perform a default scan from 'com.mkejeiri.appjokes' packages
//down to all sub-packages 'com.mkejeiri.appjokes.*' (including com.mkejeiri.appjokes.config)
@Configuration
public class ChuckConfiguration {
	@Bean 
	public ChuckNorrisQuotes myChuckNorrisQuotesInstance() {
		return new ChuckNorrisQuotes();
	}

}
