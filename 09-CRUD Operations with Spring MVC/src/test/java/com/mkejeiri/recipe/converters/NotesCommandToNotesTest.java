package com.mkejeiri.recipe.converters;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mkejeiri.recipe.command.NotesCommand;
import com.mkejeiri.recipe.domain.Notes;

class NotesCommandToNotesTest {
	public static final Long ID_VALUE = 1L;
	public static final String RECIPE_NOTES = "Notes";
	NotesCommandToNotes converter;

	@BeforeEach
	void setUp() throws Exception {
		converter = new NotesCommandToNotes();
	}

	@Test
	public void testNullParameter() throws Exception {
		assertNull(converter.convert(null));
	}

	@Test
	public void testEmptyObject() throws Exception {
		assertNotNull(converter.convert(new NotesCommand()));
	}

	@Test
	final void testConvert() throws Exception {
		// given
		NotesCommand notesCommand = new NotesCommand();
		notesCommand.setId(ID_VALUE);
		notesCommand.setRecipeNotes(RECIPE_NOTES);

		// when
		Notes notes = converter.convert(notesCommand);

		// then
		assertNotNull(notes);
		assertEquals(ID_VALUE, notes.getId());
		assertEquals(RECIPE_NOTES, notes.getRecipeNotes());
	}

}
