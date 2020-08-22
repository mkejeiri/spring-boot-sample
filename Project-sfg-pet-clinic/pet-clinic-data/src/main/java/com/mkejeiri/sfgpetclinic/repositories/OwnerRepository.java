package com.mkejeiri.sfgpetclinic.repositories;

import org.springframework.data.repository.CrudRepository;

import com.mkejeiri.sfgpetclinic.model.Owner;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
}
