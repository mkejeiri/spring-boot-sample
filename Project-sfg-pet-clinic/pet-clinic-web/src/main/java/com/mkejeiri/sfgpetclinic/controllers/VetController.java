package com.mkejeiri.sfgpetclinic.controllers;

import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mkejeiri.sfgpetclinic.model.Vet;
import com.mkejeiri.sfgpetclinic.services.VetService;

@Controller
//@RequestMapping({"/vets","/vets.html"})
public class VetController {
	private final VetService vetService;

	public VetController(VetService vetService) {
		this.vetService = vetService;
	}

	// old -> @RequestMapping({ "","/", "/index", "/index.html" })
	@GetMapping({ "/vets", "/vets/", "/vets/index", "/vets/index.html" })
	public String listVets(Model model) {
		var vets = vetService.findAll();
		model.addAttribute("vets", vets);
		return "vets/index";
	}

	@GetMapping("/api/vets")
	public @ResponseBody Set<Vet> getVetsJson() {
		return vetService.findAll();
	}

}
