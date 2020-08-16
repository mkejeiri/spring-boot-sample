package com.mkejeiri.sfgpetclinic.services;

import java.util.Set;

import com.mkejeiri.sfgpetclinic.model.Pet;

public interface PetService {
	Pet findById(Long id);

	Pet save(Pet owner);

	Set<Pet> findAll();
}
