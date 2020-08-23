package com.mkejeiri.sfgpetclinic.services.map;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.mkejeiri.sfgpetclinic.model.Vet;
import com.mkejeiri.sfgpetclinic.services.SpecialityService;
import com.mkejeiri.sfgpetclinic.services.VetService;
@Service
public class VetMapService extends AbstractMapService<Vet, Long> implements VetService {

	private final SpecialityService specialityService;
	public VetMapService(SpecialityService specialityService) {
		this.specialityService = specialityService;
	}

	@Override
	public Vet findById(Long id) {
		return super.findById(id);
	}

	@Override
	public Vet save(Vet vet) {

		if (vet.getSpecialities() != null) {
			vet.getSpecialities().forEach(speciality -> {
				if (speciality.getId() == null) {
					speciality.setId(specialityService.save(speciality).getId());
				}					
			});
		}
		
		return super.save(vet);
	}

	@Override
	public Set<Vet> findAll() {
		return super.findAll();
	}

	@Override
	public void delete(Vet vet) {
		super.delete(vet);

	}

	@Override
	public void deleteById(Long id) {
		super.deleteById(id);

	}

}
