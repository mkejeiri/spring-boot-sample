package com.mkejeiri.sfgpetclinic.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mkejeiri.sfgpetclinic.model.Owner;

public interface OwnerRepository extends CrudRepository<Owner, Long> {

	Owner findByLastName(String lastName);
	List<Owner> findAllByLastNameLikeIgnoreCase(String lastName);

}
