package com.mkejeiri.recipe.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
//Bidirectional reference with lombok create a circular references, which gives a infinite loop and ultimately a memory lead at the start up... => Error starting ApplicationContex
//ONLY ONE SIDE OF THE RELATIONSHIP FIX THE PB!
@EqualsAndHashCode(exclude = { "recipes" })
@Data
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

}
