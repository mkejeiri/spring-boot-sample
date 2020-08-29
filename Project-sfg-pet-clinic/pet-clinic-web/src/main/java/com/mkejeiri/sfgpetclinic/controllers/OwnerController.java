package com.mkejeiri.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mkejeiri.sfgpetclinic.services.OwnerService;

@RequestMapping({"/owners"}) //old way and by default is a get method
@Controller
public class OwnerController {

	private final OwnerService ownerService; 
	
	public OwnerController(OwnerService ownerService) {
		this.ownerService = ownerService;
	}

	//@RequestMapping({"", "/", "/index", "/index.html"})//old way and by default is a get method
	@GetMapping({"", "/", "/index", "/index.html"})
	public String ListOwners(Model model) {
		
		var owners = ownerService.findAll();
		model.addAttribute("owners",owners);
		return "owners/index";
	}
	
	
	//@RequestMapping({"/find"}) //old way and by default is a get method
	 @GetMapping({"/find"})
	public String findOwners(Model model) {
		
//		var owners = ownerService.findAll();
//		model.addAttribute("owners", owners);
		return "notimplemented";
	}
	
	
	 @GetMapping("/{ownerId}")
	    public ModelAndView showOwner(@PathVariable Long ownerId) {
	        ModelAndView mav = new ModelAndView("owners/ownerDetails");
	        mav.addObject(ownerService.findById(ownerId));
	        return mav;
	    } 
	
}
