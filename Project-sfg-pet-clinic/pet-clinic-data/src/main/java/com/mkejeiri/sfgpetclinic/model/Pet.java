package com.mkejeiri.sfgpetclinic.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder // Allow us to work with build pattern
@Entity
@Table(name = "pets")
public class Pet extends BaseEntity {
	
	
	@Builder 
	public Pet(Long id, String name, PetType petType, Owner owner, LocalDate birthDate, Set<Visit> visits) {
		super(id);
		this.name = name;
		this.petType = petType;
		this.owner = owner;
		this.birthDate = birthDate;
		this.visits = visits;		
		}

	@Column(name = "name")
	private String name;

	// a pet is classified by a unique pet type
	@ManyToOne // this is an FK of the reference table (types) of PetType
	@JoinColumn(name = "pet_type_id")
	private PetType petType;

	@ManyToOne
	@JoinColumn(name = "owner_id") // Fk to owners table
	private Owner owner;

	@Column(name = "birth_date")
	private LocalDate birthDate;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "pet")
	 @Builder.Default private Set<Visit> visits  = new HashSet<Visit>() ;

//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public PetType getPetType() {
//		return petType;
//	}
//
//	public void setPetType(PetType petType) {
//		this.petType = petType;
//	}
//
//	public Owner getOwner() {
//		return owner;
//	}
//
//	public void setOwner(Owner owner) {
//		this.owner = owner;
//	}
//
//	public LocalDate getBirthDate() {
//		return birthDate;
//	}
//
//	public void setBirthDate(LocalDate birthDate) {
//		this.birthDate = birthDate;
//	}
//
//	public Set<Visit> getVisits() {
//		return visits;
//	}
//
//	public void setVisits(Set<Visit> visits) {
//		this.visits = visits;
//	}
}
