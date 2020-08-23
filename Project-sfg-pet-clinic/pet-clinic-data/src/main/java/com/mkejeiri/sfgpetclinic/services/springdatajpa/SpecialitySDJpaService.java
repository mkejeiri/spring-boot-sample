package com.mkejeiri.sfgpetclinic.services.springdatajpa;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.mkejeiri.sfgpetclinic.model.Speciality;
import com.mkejeiri.sfgpetclinic.repositories.SpecialityRepository;
import com.mkejeiri.sfgpetclinic.services.SpecialityService;

@Service
@Profile("springdatajpa")
public class SpecialitySDJpaService implements SpecialityService {
	private final SpecialityRepository specialityRepository;

	public SpecialitySDJpaService(SpecialityRepository specialityRepository) {
		this.specialityRepository = specialityRepository;
	}

	@Override
	public Speciality findById(Long id) {
		return specialityRepository.findById(id).orElseGet(null);
	}

	@Override
	public Speciality save(Speciality entity) {
		return specialityRepository.save(entity);
	}

	@Override
	public Set<Speciality> findAll() {
		var specialities = new HashSet<Speciality>();
		specialityRepository.findAll().forEach(specialities::add);
		return specialities;
	}

	@Override
	public void delete(Speciality entity) {
		specialityRepository.delete(entity);

	}

	@Override
	public void deleteById(Long id) {
		specialityRepository.deleteById(id);
	}

}
