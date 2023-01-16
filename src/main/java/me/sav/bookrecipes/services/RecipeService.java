package me.sav.bookrecipes.services;

import me.sav.bookrecipes.model.Recipes;

public interface RecipeService {

    void addRecipe(Recipes recipes);

    Recipes getRecipe(int number);

}
