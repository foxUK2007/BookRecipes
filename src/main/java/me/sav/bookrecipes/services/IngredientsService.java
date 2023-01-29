package me.sav.bookrecipes.services;

import me.sav.bookrecipes.model.Ingredients;
import me.sav.bookrecipes.model.Recipes;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public interface IngredientsService {

    void addIngredients(Ingredients ingredients);


    Ingredients getIngredient(int ingredientsID);

    Path createIngredientsReport(Ingredients ingredients) throws IOException;

    void deleteIngredients(int ingredientsID);

    Map<Integer,Ingredients> getAllIngredients();

    void putIngredients(int ingredientsID, Ingredients newIngredients);

}
