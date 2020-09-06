package com.mkejeiri.recipe.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
public class UnitOfMeasure {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String description;

//Thanks to @Data from Lombok project we could get ride of this ceremonial code!	
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getDescription() {
//		return description;
//	}
//
//	public void setDescription(String description) {
//		this.description = description;
//	}

	@Override
	public String toString() {
		return "UnitOfMeasure [id=" + id + ", description=" + description + "]";
	}

	@Builder
	public UnitOfMeasure(Long id, String description) {
		this.id = id;
		this.description = description;
	}

}
