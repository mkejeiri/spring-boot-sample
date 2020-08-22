package com.mkejeiri.sfgpetclinic.repositories;

import org.springframework.data.repository.CrudRepository;

import com.mkejeiri.sfgpetclinic.model.Pet;

public interface PetRepository extends CrudRepository<Pet, Long>{

}
