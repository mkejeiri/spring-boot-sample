package com.mkejeiri.recipe.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String description;
	private Integer prepTime;
	private Integer cookTime;
	private Integer servings;
	private String source;
	private String url;

	// field should be persisted as a large object to a database-supported large
	// object type!
	@Lob // otherwise Hibernate will create/use a string of 255 characters and definitely
			// we need more than that!
	private String directions;

//	@Enumerated(EnumType.ORDINAL) : ordinal is the default, not recommended think adding a new ENUM value in the middle!
	@Enumerated(EnumType.STRING)
	private Difficulty difficulty;

	@Lob
	private Byte[] image;

	@OneToOne(cascade = CascadeType.ALL)
	private Notes notes;

	// mappedBy = "recipe" : "recipe" is the target property in Ingredient class
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
	private Set<Ingredient> ingredients = new HashSet<Ingredient>();

	@ManyToMany
	@JoinTable(name = "recipe_category", joinColumns = @JoinColumn(name = "recipe_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	private Set<Category> categories = new HashSet<Category>();

	public void setNotes(Notes notes) {
		this.notes = notes;
		this.notes.setRecipe(this);
	}

	public Recipe addIngredient(Ingredient ingredient) {
		this.getIngredients().add(ingredient);
		ingredient.setRecipe(this);
		return this;
	}
	
	public Recipe removeIngredient(Ingredient ingredient) {
		this.getIngredients().remove(ingredient);
		return this;
	}

}
