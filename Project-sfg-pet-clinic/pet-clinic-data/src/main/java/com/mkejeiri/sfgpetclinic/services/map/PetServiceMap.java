package com.mkejeiri.sfgpetclinic.services.map;

import java.util.Set;

import com.mkejeiri.sfgpetclinic.model.Pet;
import com.mkejeiri.sfgpetclinic.services.CrudService;

public class PetServiceMap extends AbstractMapService<Pet, Long> implements CrudService<Pet, Long> {

	@Override
	public Pet findById(Long id) {
		return super.findById(id);
	}

	@Override
	public Pet save(Pet pet) {
		return super.save(pet.getId(), pet);
	}

	@Override
	public Set<Pet> findAll() {
		return super.findAll();
	}

	@Override
	public void delete(Pet pet) {
		super.delete(pet);

	}

	@Override
	public void deleteById(Long id) {
		super.deleteById(id);

	}

}