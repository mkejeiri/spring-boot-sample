package com.mkejeiri.sfgpetclinic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.mkejeiri.sfgpetclinic.model.Owner;
import com.mkejeiri.sfgpetclinic.model.Vet;
import com.mkejeiri.sfgpetclinic.services.OwnerService;
import com.mkejeiri.sfgpetclinic.services.PetService;
import com.mkejeiri.sfgpetclinic.services.VetService;
import com.mkejeiri.sfgpetclinic.services.map.OwnerServiceMap;
import com.mkejeiri.sfgpetclinic.services.map.PetServiceMap;
import com.mkejeiri.sfgpetclinic.services.map.VetServiceMap;

@Component // by making DataLoader a component it becomes a spring beans and get registered
			// into spring context
//and later on the run method get executed when the spring is completely up & ready  
public class DataLoader implements CommandLineRunner {

	private final OwnerService ownerService;
	private final VetService vetService;
	private final PetService petService;

	public DataLoader() {
		this.ownerService = new OwnerServiceMap();
		this.vetService = new VetServiceMap();
		this.petService = new PetServiceMap();
	}

	@Override
	public void run(String... args) throws Exception {
		
		var owner = new Owner();
		owner.setId(1L);
		owner.setFirstName("John");
		owner.setLastName("Doe");		
		ownerService.save(owner);
		
		owner = new Owner();
		owner.setId(2L);
		owner.setFirstName("Sarah");
		owner.setLastName("Coroner");		
		ownerService.save(owner);
		
		System.out.println("Loaded Owners...");
		
		
		var vet = new Vet();
		vet.setId(1L);
		vet.setFirstName("Luc");
		vet.setLastName("Skywalker");
		vetService.save(vet);
		
		vet = new Vet();
		vet.setId(2L);
		vet.setFirstName("Axel");
		vet.setLastName("Rodd");
		vetService.save(vet);
		
		System.out.println("Loaded Vets...");				
	}

}
