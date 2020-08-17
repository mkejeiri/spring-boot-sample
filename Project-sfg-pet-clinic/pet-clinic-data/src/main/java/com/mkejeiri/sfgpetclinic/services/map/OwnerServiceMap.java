package com.mkejeiri.sfgpetclinic.services.map;

import java.util.Set;

import com.mkejeiri.sfgpetclinic.model.Owner;
import com.mkejeiri.sfgpetclinic.services.CrudService;

public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements CrudService<Owner, Long> {

	@Override
	public Owner findById(Long id) {
		return super.findById(id);
	}

	@Override
	public Owner save(Owner owner) {
		return super.save(owner.getId(), owner);
	}

	@Override
	public Set<Owner> findAll() {
		return super.findAll();
	}

	@Override
	public void delete(Owner owner) {
		super.delete(owner);

	}

	@Override
	public void deleteById(Long id) {
		super.deleteById(id);
	}

}
