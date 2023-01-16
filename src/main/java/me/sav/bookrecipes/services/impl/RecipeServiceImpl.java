package me.sav.bookrecipes.services.impl;

import me.sav.bookrecipes.model.Recipes;
import me.sav.bookrecipes.services.RecipeService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecipeServiceImpl implements RecipeService {

    private Map<Integer, Recipes> recipesMap = new TreeMap<>();

    private static Integer recipeID = 0;


    @Override
    public void addRecipe(Recipes recipes) {
        recipesMap.put(recipeID++, recipes);

    }

    @Override
    public Recipes getRecipe(int number) {
        return recipesMap.get(number);
    }



}
