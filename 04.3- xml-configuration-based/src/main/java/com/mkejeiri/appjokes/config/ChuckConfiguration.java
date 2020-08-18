package com.mkejeiri.appjokes.config;

import guru.springframework.norris.chuck.ChuckNorrisQuotes;

//Spring can no longer see this class as a configuration class!, all's done through xml! 
public class ChuckConfiguration {
	public ChuckNorrisQuotes myChuckNorrisQuotesInstance() {
		return new ChuckNorrisQuotes();
	}

}
