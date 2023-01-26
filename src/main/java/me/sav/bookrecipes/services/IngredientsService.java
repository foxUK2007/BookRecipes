package me.sav.bookrecipes.services;

import me.sav.bookrecipes.model.Ingredients;
import me.sav.bookrecipes.model.Recipes;

import java.util.List;
import java.util.Map;

public interface IngredientsService {

    void addIngredients(Ingredients ingredients);


    Ingredients getIngredient(int ingredientsID);

    void deleteIngredients(int ingredientsID);

    Map<Integer,Ingredients> getAllIngredients();

    void putIngredients(int ingredientsID, Ingredients newIngredients);

}
