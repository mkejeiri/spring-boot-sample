package com.mkejeiri.sfgpetclinic.services.map;

import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.mkejeiri.sfgpetclinic.model.Speciality;
import com.mkejeiri.sfgpetclinic.services.SpecialityService;

@Service
@Profile({"default","map"})
public class SpecialityMapService  extends AbstractMapService<Speciality, Long> implements SpecialityService {
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
