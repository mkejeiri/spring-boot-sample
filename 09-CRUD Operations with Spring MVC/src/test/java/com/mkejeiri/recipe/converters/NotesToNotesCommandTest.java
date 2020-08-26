package com.mkejeiri.recipe.converters;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mkejeiri.recipe.command.NotesCommand;
import com.mkejeiri.recipe.domain.Notes;

public class NotesToNotesCommandTest {
	public static final Long ID_VALUE = 1L;
	public static final String RECIPE_NOTES = "Notes";
	NotesToNotesCommand converter;

	@BeforeEach
	void setUp() throws Exception {
		converter = new NotesToNotesCommand();
	}

	@Test
	final void testConvert() throws Exception {
		// given
		Notes notes = new Notes();
		notes.setId(ID_VALUE);
		notes.setRecipeNotes(RECIPE_NOTES);

		// when
		NotesCommand notesCommand = converter.convert(notes);

		// then
		assertEquals(ID_VALUE, notesCommand.getId());
		assertEquals(RECIPE_NOTES, notesCommand.getRecipeNotes());
	}
	
	@Test
    public void testNull() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new Notes()));
    }

}
