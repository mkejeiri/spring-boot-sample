package com.mkejeiri.sfgpetclinic.services.springdatajpa;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.mkejeiri.sfgpetclinic.model.Vet;
import com.mkejeiri.sfgpetclinic.repositories.VetRepository;
import com.mkejeiri.sfgpetclinic.services.VetService;

@Service
@Profile("springdatajpa")
public class VetSDJbaService implements VetService {

	private final VetRepository vetRepository;

	public VetSDJbaService(VetRepository vetRepository) {
		this.vetRepository = vetRepository;
	}

	@Override
	public Vet findById(Long id) {
		return vetRepository.findById(id).orElse(null);
	}

	@Override
	public Vet save(Vet entity) {
		return vetRepository.save(entity);
	}

	@Override
	public Set<Vet> findAll() {
		var vets = new HashSet<Vet>();
		vetRepository.findAll().forEach(vets::add);
		return vets;
	}

	@Override
	public void delete(Vet entity) {
		vetRepository.delete(entity);

	}

	@Override
	public void deleteById(Long id) {
		vetRepository.deleteById(id);
	}

}
