package com.mkejeiri.recipe.converters;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mkejeiri.recipe.commands.UnitOfMeasureCommand;
import com.mkejeiri.recipe.domain.UnitOfMeasure;

class UnitOfMeasureCommandToUnitOfMeasureTest {
	public static final String DESCRIPTION = "description";
	public static final Long LONG_VALUE = 1L;

	UnitOfMeasureCommandToUnitOfMeasure converter;

	@BeforeEach
	void setUp() throws Exception {
		converter = new UnitOfMeasureCommandToUnitOfMeasure();
	}

	@Test
	public void testNullParamter() throws Exception {
		assertNull(converter.convert(null));
	}

	@Test
	public void testEmptyObject() throws Exception {
		assertNotNull(converter.convert(new UnitOfMeasureCommand()));
	}

	@Test
	final void testConvert() throws Exception {
		// given
		UnitOfMeasureCommand command = new UnitOfMeasureCommand();
		command.setId(LONG_VALUE);
		command.setDescription(DESCRIPTION);

		// when
		UnitOfMeasure uom = converter.convert(command);

		// then
		assertNotNull(uom);
		assertEquals(LONG_VALUE, uom.getId());
		assertEquals(DESCRIPTION, uom.getDescription());

	}
}
