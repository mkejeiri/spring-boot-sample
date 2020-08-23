package com.mkejeiri.sfgpetclinic.bootstrap;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.mkejeiri.sfgpetclinic.model.Owner;
import com.mkejeiri.sfgpetclinic.model.Pet;
import com.mkejeiri.sfgpetclinic.model.PetType;
import com.mkejeiri.sfgpetclinic.model.Speciality;
import com.mkejeiri.sfgpetclinic.model.Vet;
import com.mkejeiri.sfgpetclinic.model.Visit;
import com.mkejeiri.sfgpetclinic.services.OwnerService;
import com.mkejeiri.sfgpetclinic.services.PetService;
import com.mkejeiri.sfgpetclinic.services.PetTypeService;
import com.mkejeiri.sfgpetclinic.services.SpecialityService;
import com.mkejeiri.sfgpetclinic.services.VetService;
import com.mkejeiri.sfgpetclinic.services.VisitService;

@Component // by making DataLoader a component it becomes a spring beans and get registered
			// into spring context
//and later on the run method get executed when the spring is completely up & ready  
public class DataLoader implements CommandLineRunner {

	private final OwnerService ownerService;
	private final VetService vetService;
	private final PetService petService;
	private final PetTypeService petTypeService;
	private final SpecialityService specialtyService;
	private final VisitService visitService;

	// @Autowired //this was required in Spring 4.2 and no longer required in Spring
	// 5
	public DataLoader(OwnerService ownerService, VetService vetService, PetService petService,
			PetTypeService petTypeService, SpecialityService specialtyService, VisitService visitService) {
		this.ownerService = ownerService;
		this.vetService = vetService;
		this.petService = petService;
		this.petTypeService = petTypeService;
		this.specialtyService = specialtyService;
		this.visitService = visitService;
	}

	@Override
	public void run(String... args) throws Exception {
		if (vetService.findAll().size() == 0) {
			loadData();
		} else {
			System.out.println("Data's already loaded!");
		}
	}

	private void loadData() {
		var radiology = new Speciality();
		radiology.setDescription("Radiology");
		var savedRadiology = specialtyService.save(radiology);

		var surgery = new Speciality();
		surgery.setDescription("Surgery");
		var savedSurgery = specialtyService.save(surgery);

		var dentistry = new Speciality();
		dentistry.setDescription("Dentistry");
		var savedDentistry = specialtyService.save(dentistry);

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

		var vet1 = new Vet();
		vet1.setFirstName("Luc");
		vet1.setLastName("Skywalker");
		vet1.getSpecialities().add(savedRadiology);
		vetService.save(vet1);

		var vet2 = new Vet();
		vet2.setFirstName("Axel");
		vet2.setLastName("Rodd");
		vet1.getSpecialities().add(savedSurgery);
		vetService.save(vet2);

		var petType = new PetType();
		petType.setName("cat");
		var savedCatType = petTypeService.save(petType);
		
		petType = new PetType();
		petType.setName("dog");
		var savedDogType = petTypeService.save(petType);

		Pet mikesPet = new Pet();
		mikesPet.setPetType(savedDogType);
		mikesPet.setOwner(john);
		mikesPet.setBirthDate(LocalDate.now());
		mikesPet.setName("Rosco");
		john.getPets().add(mikesPet);
		var savedMikesPet = petService.save(mikesPet);
		var dogVisit = new Visit(LocalDate.now(), "Mike dog visit",savedMikesPet);		
		visitService.save(dogVisit);

		Pet sarahPet = new Pet();
		sarahPet.setPetType(savedCatType);
		sarahPet.setOwner(sarah);
		sarahPet.setName("moeu");
		sarahPet.setBirthDate(LocalDate.now());
		sarahPet.getOwner().getPets().add(sarahPet);
		var savedSarahPet = petService.save(sarahPet);
		
		//Cat visit
		var visitCat = new Visit(LocalDate.now(), "sarah cat visit",savedSarahPet);		
		visitService.save(visitCat);

		System.out.println("Loaded Vets...");
	}

}
