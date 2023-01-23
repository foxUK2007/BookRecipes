package me.sav.bookrecipes.services.impl;

import me.sav.bookrecipes.model.Recipes;
import me.sav.bookrecipes.services.RecipeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class RecipeServiceImpl implements RecipeService {

    private Map<Integer, Recipes> recipesMap = new TreeMap<>();

    static int recipeID = 0;

    @Override
    public void addRecipe(Recipes recipes) {
        recipesMap.put(recipeID++, recipes);

    }
    @Override
    public Recipes getRecipe(int number) {
        return recipesMap.get(number);
    }

    @Override
    public void deleteRecipe(int recipeID) {
        recipesMap.remove(recipeID);

    }

    @Override
    public List<Recipes> getAllRecipe() {
        ArrayList<Recipes> allRecipes = new ArrayList<>();
        for (Map.Entry<Integer, Recipes> pair : recipesMap.entrySet()) {
            allRecipes.add(pair.getValue());
        }
        return allRecipes;
    }

    @Override
    public void putRecipe(int recipeID, Recipes newRecipes) {
        recipesMap.put(recipeID, newRecipes);

    }

}

