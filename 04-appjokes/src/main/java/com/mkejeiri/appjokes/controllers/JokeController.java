package com.mkejeiri.appjokes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mkejeiri.appjokes.services.JokeService;

@Controller
public class JokeController {
	
	private final JokeService jokeService;

	@Autowired
	public JokeController(JokeService jokeService) {
		this.jokeService = jokeService;
	}

//	@RequestMapping("/joke")
//	@RequestMapping({"/","joke", "joke2"}) //any of the string will do for the routing!
	@RequestMapping({"/",""})
	public String showJoke(Model model) {
		model.addAttribute("joke", jokeService.getJoke());
		return "chuckview";
	}
}
