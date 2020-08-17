package com.mkejeiri.sfgpetclinic.services.map;

import java.util.Set;

import com.mkejeiri.sfgpetclinic.model.Vet;
import com.mkejeiri.sfgpetclinic.services.CrudService;

public class VetServiceMap extends AbstractMapService<Vet, Long> implements CrudService<Vet, Long> {

	@Override
	public Vet findById(Long id) {
		return super.findById(id);
	}

	@Override
	public Vet save(Vet vet) {

		return super.save(vet.getId(), vet);
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
