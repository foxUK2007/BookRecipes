package me.sav.bookrecipes.services;

import me.sav.bookrecipes.model.Ingredients;

public interface IngredientsService {

    void addIngredients(Ingredients ingredients);

    void getIngredients(int number);

}
