package com.mkejeiri.sfgpetclinic.services;

import com.mkejeiri.sfgpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long>{
	Owner findByLastName(String lastName);
}
