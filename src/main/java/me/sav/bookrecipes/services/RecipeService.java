package me.sav.bookrecipes.services;

import me.sav.bookrecipes.model.Recipes;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public interface RecipeService {

    void addRecipe(Recipes recipes);

    Recipes getRecipe(int number);

    void deleteRecipe(int counter);

    Map<Integer,Recipes> getAllRecipe();

    void putRecipe(int id, Recipes newRecipes);

    Path createRecipeReport(Recipes recipes) throws IOException;
}
