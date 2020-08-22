package com.mkejeiri.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mkejeiri.sfgpetclinic.services.VetService;

@Controller
@RequestMapping({ "/vets","/vets.html"})
public class VetController {
	private final VetService vetService;

	public VetController(VetService vetService) {
		this.vetService = vetService;
	}

	@RequestMapping({ "","/",  "/index", "/index.html" })
	public String listVets(Model model) {
		var vets = vetService.findAll();
		model.addAttribute("vets", vets);
		return "vets/index";
	}

}
