package me.sav.bookrecipes.services;

import me.sav.bookrecipes.model.Recipes;

import java.util.List;

public interface RecipeService {

    void addRecipe(Recipes recipes);

    Recipes getRecipe(int number);

    void deleteRecipe(int counter);

    List<Recipes> getAllRecipe();

    void putRecipe(int id, Recipes newRecipes);
}
