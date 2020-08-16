package com.mkejeiri.sfgpetclinic.services;

import java.util.Set;

import com.mkejeiri.sfgpetclinic.model.Vet;

public interface VetService {

	Vet findById(Long id);

	Vet save(Vet owner);

	Set<Vet> findAll();
}
