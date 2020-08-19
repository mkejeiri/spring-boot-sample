package com.mkejeiri.sfgpetclinic.services.map;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.mkejeiri.sfgpetclinic.model.Owner;
import com.mkejeiri.sfgpetclinic.services.OwnerService;

@Service //this annotation allows Interface (e.g. OwnerService) to be brought into Spring context as Spring beans (OwnerServiceMap)
//upon startup, using packages component scan from top to bottom,
//by default the top package is where the class annotated with @SpringBootApplication reside, if any service reside outside that tree we need to use
//explicitly ComponentScan on the class annotated with @SpringBootApplication.
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {

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

	@Override
	public Owner findByLastName(String lastName) {
		// TODO Auto-generated method stub
		return null;
	}

}
