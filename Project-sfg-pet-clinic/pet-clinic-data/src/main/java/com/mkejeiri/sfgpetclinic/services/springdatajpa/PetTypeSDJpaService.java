package com.mkejeiri.sfgpetclinic.services.springdatajpa;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.mkejeiri.sfgpetclinic.model.PetType;
import com.mkejeiri.sfgpetclinic.repositories.PetTypeRepository;
import com.mkejeiri.sfgpetclinic.services.PetTypeService;

@Service
@Profile("springdatajpa")
public class PetTypeSDJpaService implements PetTypeService {
	private final PetTypeRepository petTypeRepository;

	public PetTypeSDJpaService(PetTypeRepository petTypeRepository) {
		this.petTypeRepository = petTypeRepository;
	}

	@Override
	public PetType findById(Long id) {
		return petTypeRepository.findById(id).orElseGet(null);
	}

	@Override
	public PetType save(PetType entity) {
		return petTypeRepository.save(entity);
	}

	@Override
	public Set<PetType> findAll() {
		var petTypes = new HashSet<PetType>();
		petTypeRepository.findAll().forEach(petTypes::add);
		return petTypes;
	}

	@Override
	public void delete(PetType entity) {
		petTypeRepository.delete(entity);

	}

	@Override
	public void deleteById(Long id) {
		petTypeRepository.deleteById(id);
	}

}
