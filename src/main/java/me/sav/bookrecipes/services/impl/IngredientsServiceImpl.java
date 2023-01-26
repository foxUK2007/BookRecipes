package me.sav.bookrecipes.services.impl;

import me.sav.bookrecipes.model.Ingredients;
import me.sav.bookrecipes.services.IngredientsService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.TreeMap;

@Service
public class IngredientsServiceImpl implements IngredientsService {

    final private FilesServiceImpl filesService;
    private final Map<Integer, Ingredients> ingredientsMap = new TreeMap<>();

    static int ingredientsID = 0;

    public IngredientsServiceImpl(FilesServiceImpl filesService) {
        this.filesService = filesService;
    }

    @Override
    public void addIngredients(Ingredients ingredients) {
        ingredientsMap.put(ingredientsID++, ingredients);
    }

    @Override
    public Ingredients getIngredient(int ingredientsID) {
        return ingredientsMap.get(ingredientsID);
    }
    @Override
    public void putIngredients(int ingredientsID, Ingredients newIngredients) {
        ingredientsMap.put(ingredientsID, newIngredients);

    }
    @Override
    public void deleteIngredients(int ingredientsID) {
        ingredientsMap.remove(ingredientsID);
    }

    @Override
    public Map<Integer, Ingredients> getAllIngredients() {
        return ingredientsMap;
    }

}

