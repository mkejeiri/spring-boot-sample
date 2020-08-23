package com.mkejeiri.sfgpetclinic.services.map;

import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.mkejeiri.sfgpetclinic.model.Owner;
import com.mkejeiri.sfgpetclinic.services.OwnerService;
import com.mkejeiri.sfgpetclinic.services.PetService;
import com.mkejeiri.sfgpetclinic.services.PetTypeService;

@Service // this annotation allows Interface (e.g. OwnerService) to be brought into
			// Spring context as Spring beans (OwnerServiceMap)
//upon startup, using packages component scan from top to bottom,
//by default the top package is where the class annotated with @SpringBootApplication reside, if any service reside outside that tree we need to use
//explicitly ComponentScan on the class annotated with @SpringBootApplication.
@Profile({"default","map"})
public class OwnerMapService extends AbstractMapService<Owner, Long> implements OwnerService {
	private final PetService petService;
	private final PetTypeService petTypeService;

	public OwnerMapService(PetService petService, PetTypeService petTypeService) {
		this.petService = petService;
		this.petTypeService = petTypeService;
	}

	@Override
	public Owner findById(Long id) {
		return super.findById(id);
	}

	@Override
	public Owner save(Owner owner) {
		if (owner != null) {

			if (owner.getPets() != null) {
				owner.getPets().forEach(pet -> {
					if (pet.getPetType() != null) {
						if (pet.getPetType().getId() == null) {
							pet.setPetType(petTypeService.save(pet.getPetType()));
						}
					} else
						throw new RuntimeException("Pet Type is required");
					if (pet.getId() == null) {
						pet.setId(petService.save(pet).getId());
					}					
				});
			}

			return super.save(owner);
		} else {
			return null;
		}

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
