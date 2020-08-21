package com.mkejeiri.sfgpetclinic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.mkejeiri.sfgpetclinic.model.Owner;
import com.mkejeiri.sfgpetclinic.model.PetType;
import com.mkejeiri.sfgpetclinic.model.Vet;
import com.mkejeiri.sfgpetclinic.services.OwnerService;
import com.mkejeiri.sfgpetclinic.services.PetService;
import com.mkejeiri.sfgpetclinic.services.PetTypeService;
import com.mkejeiri.sfgpetclinic.services.VetService;

@Component // by making DataLoader a component it becomes a spring beans and get registered
			// into spring context
//and later on the run method get executed when the spring is completely up & ready  
public class DataLoader implements CommandLineRunner {

	private final OwnerService ownerService;
	private final VetService vetService;
	private final PetService petService;
	private final PetTypeService petTypeService;
	
	//@Autowired //this was required in Spring 4.2 and no longer required in Spring 5
	public DataLoader(OwnerService ownerService, VetService vetService, PetService petService, PetTypeService petTypeService) {
		this.ownerService = ownerService;
		this.vetService = vetService;
		this.petService = petService;
		this.petTypeService = petTypeService;
	}

	@Override
	public void run(String... args) throws Exception {

		var owner = new Owner();		
		owner.setFirstName("John");
		owner.setLastName("Doe");
		ownerService.save(owner);

		owner = new Owner();
		owner.setFirstName("Sarah");
		owner.setLastName("Coroner");
		ownerService.save(owner);

		System.out.println("Loaded Owners...");

		var vet = new Vet();
		vet.setFirstName("Luc");
		vet.setLastName("Skywalker");
		vetService.save(vet);

		vet = new Vet();
		vet.setFirstName("Axel");
		vet.setLastName("Rodd");
		vetService.save(vet);
		
		var petType = new PetType();
		petType.setName("cat");		
		var savedPetType = petTypeService.save(petType);
		
		petType = new PetType();
		petType.setName("dog");
		savedPetType =petTypeService.save(petType);

		System.out.println("Loaded Vets...");
	}

}
