package com.mkejeiri.sfgpetclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Builder //Allow us to work with build pattern
@Entity
@Table(name = "types")
public class PetType extends BaseEntity {
	
	@Column(name = "name")
	private String name;

	@Override
	public String toString() {
		return name;
	}

	 
//implemented under the cover by lombok project
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}

}
