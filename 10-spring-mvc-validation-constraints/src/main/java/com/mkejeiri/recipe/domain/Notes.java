package com.mkejeiri.recipe.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
//Bidirectional reference with lombok create a circular references, which gives a infinite loop and ultimately a memory lead at the start up... => Error starting ApplicationContex
//ONLY ONE SIDE OF THE RELATIONSHIP FIX THE PB!
@EqualsAndHashCode(exclude = { "recipe" })
public class Notes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	private Recipe recipe;
	@Lob
	private String recipeNotes;

//Thanks to @Data from Lombok project we could get ride of this ceremonial code!
//	public Long getId() {
//		return id;
//	}
//
//	public Recipe getRecipe() {
//		return recipe;
//	}
//
//	public void setRecipe(Recipe recipe) {
//		this.recipe = recipe;
//	}
//
//	public String getRecipeNotes() {
//		return recipeNotes;
//	}
//
//	public void setRecipeNotes(String recipeNotes) {
//		this.recipeNotes = recipeNotes;
//	}

}
