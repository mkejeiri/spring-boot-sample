package com.mkejeiri.recipe.services;

import java.util.Set;

import com.mkejeiri.recipe.commands.UnitOfMeasureCommand;

public interface UnitOfMeasureService {

    Set<UnitOfMeasureCommand> listAllUoms();
}