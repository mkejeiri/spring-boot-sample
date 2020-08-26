package com.mkejeiri.recipe.converters;

import org.springframework.stereotype.Component;

import org.springframework.core.convert.converter.Converter;
import com.mkejeiri.recipe.command.CategoryCommand;
import com.mkejeiri.recipe.command.IngredientCommand;
import com.mkejeiri.recipe.command.NotesCommand;
import com.mkejeiri.recipe.domain.Category;
import com.mkejeiri.recipe.domain.Ingredient;
import com.mkejeiri.recipe.domain.Notes;
import com.sun.istack.Nullable;

import lombok.Synchronized;

@Component
public class NotesToNotesCommand implements Converter<Notes, NotesCommand>{

    @Synchronized
    @Nullable
    @Override
    public NotesCommand convert(Notes source) {
        if (source == null) {
            return null;
        }

        final NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(source.getId());
        notesCommand.setRecipeNotes(source.getRecipeNotes());
        return notesCommand;
    }
}