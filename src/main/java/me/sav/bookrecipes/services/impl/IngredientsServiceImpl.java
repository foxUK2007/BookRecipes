package me.sav.bookrecipes.services.impl;

import me.sav.bookrecipes.model.Ingredients;
import me.sav.bookrecipes.model.Recipes;
import me.sav.bookrecipes.services.IngredientsService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.TreeMap;

@Service
public class IngredientsServiceImpl implements IngredientsService {

    private Map<Integer, Ingredients> ingredientsMap = new TreeMap<>();

    private static Integer ingredientsID = 1;

    @Override
    public void addIngredients(Ingredients ingredients) {
        ingredientsMap.put(ingredientsID++, ingredients);

    }

    @Override
    public void getIngredients(int number) {
        ingredientsMap.get(number);
    }
}

