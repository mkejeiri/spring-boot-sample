package com.mkejeiri.recipe.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CategoryTest {
	
	Category category;

	@BeforeEach
	void setUp() throws Exception {
		category = new Category();
	}

	@Test
	final void testGetId() {
//		fail("Not yet implemented"); // TODO
		Long expected = 4L;
		category.setId(expected);
		var actual = category.getId();
		assertEquals(actual, expected);

	}

	@Test
	final void testGetDescription() {

	}

	@Test
	final void testGetRecipes() {
	}

}
