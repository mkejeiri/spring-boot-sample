package com.mkejeiri.sfgpetclinic.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@SuppressWarnings("serial")
@MappedSuperclass //i.e. not mapped to the db, only subclasses will have a table
public class Person extends BaseEntity {

	@Column(name = "first_name") //Hibernate by default use snake case, this is kept here for demo purposes!
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
