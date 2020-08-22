package com.mkejeiri.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mkejeiri.sfgpetclinic.services.OwnerService;

@RequestMapping({"/owners","/owners.html"})
@Controller
public class OwnerController {

	private final OwnerService ownerService; 
	
	public OwnerController(OwnerService ownerService) {
		this.ownerService = ownerService;
	}

	@RequestMapping({"", "/", "/index", "/index.html"})
	public String listVets(Model model) {
		
		var owners = ownerService.findAll();
		model.addAttribute("owners",owners);
		return "owners/index";
	}
	
	
	@RequestMapping({"/find"})
	public String findOwners(Model model) {
		
		var owners = ownerService.findAll();
		model.addAttribute("owners",owners);
		return "notimplemented";
	}
	
	//
}
