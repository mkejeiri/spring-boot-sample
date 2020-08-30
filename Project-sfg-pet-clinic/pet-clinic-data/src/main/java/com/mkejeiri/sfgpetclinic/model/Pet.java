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

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
//@NoArgsConstructor
@AllArgsConstructor 
@Entity
@Table(name = "pets")
public class Pet extends BaseEntity {
	
	
	public Pet() {}

	@Builder // Allow us to work with build pattern
	public Pet(Long id, String name, PetType petType, Owner owner, LocalDate birthDate, Set<Visit> visits) {
		super(id);
		this.name = name;
		this.petType = petType;
		this.owner = owner;
		this.birthDate = birthDate;
		if (visits !=null && visits.size() > 0) {
			this.visits = visits;
		}
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
	//2 techniques to deal with date formatting : on class level (see Pet.class) OR 
	//controller level (on each request see  @InitBinder on VisitController) 
	//this Date Time Formatting technique will works only on Pet class
	//this technique gives control on a property while  @InitBinder  gives control on the type (more global)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthDate;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "pet")
	private Set<Visit> visits  = new HashSet<Visit>() ;

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
