package com.mkejeiri.recipe.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
//Bidirectional reference with lombok create a circular references, which gives a infinite loop and ultimately a memory lead at the start up... => Error starting ApplicationContex
//ONLY ONE SIDE OF THE RELATIONSHIP FIX THE PB!
@EqualsAndHashCode(exclude = { "recipes" })
@Data
@NoArgsConstructor
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String description;

	@ManyToMany(mappedBy = "categories")
	private Set<Recipe> recipes;

	@Override
	public String toString() {
		return "Category [id=" + id + ", description=" + description + "]";
	}

	@Builder
	public Category(Long id, String description) {
		this.id = id;
		this.description = description;
	}
	
	

}
