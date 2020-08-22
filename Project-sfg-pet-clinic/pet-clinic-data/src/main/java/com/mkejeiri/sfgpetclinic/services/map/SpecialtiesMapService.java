package com.mkejeiri.sfgpetclinic.services.map;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.mkejeiri.sfgpetclinic.model.Pet;
import com.mkejeiri.sfgpetclinic.model.Speciality;
import com.mkejeiri.sfgpetclinic.services.SpecialtiesService;

@Service
public class SpecialtiesMapService  extends AbstractMapService<Speciality, Long> implements SpecialtiesService {
	@Override
	public Speciality findById(Long id) {
		return super.findById(id);
	}

	@Override
	public Speciality save(Speciality speciality) {
		return super.save(speciality);
	}

	@Override
	public Set<Speciality> findAll() {
		return super.findAll();
	}

	@Override
	public void delete(Speciality speciality) {
		super.delete(speciality);

	}

	@Override
	public void deleteById(Long id) {
		super.deleteById(id);

	}

}
