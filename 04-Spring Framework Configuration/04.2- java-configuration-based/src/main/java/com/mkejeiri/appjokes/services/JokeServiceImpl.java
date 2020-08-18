package com.mkejeiri.appjokes.services;

import org.springframework.stereotype.Service;

import guru.springframework.norris.chuck.ChuckNorrisQuotes;

@Service
public class JokeServiceImpl implements JokeService {

	private final ChuckNorrisQuotes chuckNorrisQuotes;

//	public JokeServiceImpl() {
//		//spring boot will wired this only once instead of using it inside a method
//		this.chuckNorrisQuotes = new ChuckNorrisQuotes();
//	}

	public JokeServiceImpl(ChuckNorrisQuotes chuckNorrisQuotes) {
		this.chuckNorrisQuotes = chuckNorrisQuotes;
	}

	@Override
	public String getJoke() {
		return chuckNorrisQuotes.getRandomQuote();
	}

}
