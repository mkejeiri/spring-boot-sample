package com.mkejeiri.sfgpetclinic.bootstrap;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.mkejeiri.sfgpetclinic.model.Owner;
import com.mkejeiri.sfgpetclinic.model.Pet;
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

		var john = new Owner();		
		john.setFirstName("John");
		john.setLastName("Doe");
		john.setAddress("winer Road, 30");
		john.setCity("New york city");
		john.setTelephone("+1227544878");
		ownerService.save(john);

		var sarah = new Owner();
		sarah.setFirstName("Sarah");
		sarah.setLastName("Coroner");
		sarah.setAddress("Loser Road, 30");
		sarah.setCity("Atlanta city");
		sarah.setTelephone("+1221557877");
		ownerService.save(sarah);

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
		var savedCatType = petTypeService.save(petType);
		
		petType = new PetType();
		petType.setName("dog");
		var savedDogType =petTypeService.save(petType);
		
		Pet mikesPet = new Pet();
		mikesPet.setPetType(savedDogType);
		mikesPet.setOwner(john);
		mikesPet.setBirthDate(LocalDate.now());		
		mikesPet.setName("Rosco");
		john.getPets().add(mikesPet);
		
		
		Pet sarahPet = new Pet();
		sarahPet.setPetType(savedCatType);
		sarahPet.setOwner(sarah);
		sarahPet.setName("moeu")	;
		sarahPet.setBirthDate(LocalDate.now());
		sarahPet.getOwner().getPets().add(sarahPet);
		
		
		

		System.out.println("Loaded Vets...");
	}

}
