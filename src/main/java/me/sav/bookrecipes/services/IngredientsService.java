package me.sav.bookrecipes.services;

import me.sav.bookrecipes.model.Ingredients;
import me.sav.bookrecipes.model.Recipes;

import java.util.List;

public interface IngredientsService {

    void addIngredients(Ingredients ingredients);


    Ingredients getIngredient(int ingredientsID);

    void deleteIngredients(int ingredientsID);

    void putIngredients(int ingredientsID, Ingredients newIngredients);



}
