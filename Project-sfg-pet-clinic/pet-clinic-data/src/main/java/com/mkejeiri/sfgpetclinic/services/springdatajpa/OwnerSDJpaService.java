package com.mkejeiri.sfgpetclinic.services.springdatajpa;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.mkejeiri.sfgpetclinic.model.Owner;
import com.mkejeiri.sfgpetclinic.repositories.OwnerRepository;
import com.mkejeiri.sfgpetclinic.repositories.PetRepository;
import com.mkejeiri.sfgpetclinic.repositories.PetTypeRepository;
import com.mkejeiri.sfgpetclinic.services.OwnerService;

@Service
@Profile("springdatajpa")
public class OwnerSDJpaService implements OwnerService {
	private final OwnerRepository ownerRepository;
	private final PetRepository petRepository;
	private final PetTypeRepository petTypeRepository;

	public OwnerSDJpaService(OwnerRepository ownerRepository, PetRepository petRepository,
			PetTypeRepository petTypeRepository) {
		this.ownerRepository = ownerRepository;
		this.petRepository = petRepository;
		this.petTypeRepository = petTypeRepository;
	}

	@Override
	public Owner findById(Long id) {
		Optional<Owner> ownerOptional = ownerRepository.findById(id);
		return ownerOptional.orElse(null);
	}

	@Override
	public Owner save(Owner entity) {
		return ownerRepository.save(entity);
	}

	@Override
	public Set<Owner> findAll() {
		Set<Owner> owners = new HashSet<Owner>();
		ownerRepository.findAll().forEach(owners::add); // java 8 syntax
		return owners;
	}

	@Override
	public void delete(Owner entity) {
		ownerRepository.delete(entity);
	}

	@Override
	public void deleteById(Long id) {
		ownerRepository.deleteById(id);
	}

	@Override
	public Owner findByLastName(String lastName) {
		return ownerRepository.findByLastName(lastName);
	}

	@Override
    public List<Owner> findAllByLastNameLike(String lastName) {
        return ownerRepository.findAllByLastNameLikeIgnoreCase(lastName);
    }

}
