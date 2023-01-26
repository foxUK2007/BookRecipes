package me.sav.bookrecipes.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.sav.bookrecipes.model.Ingredients;
import me.sav.bookrecipes.model.Recipes;
import me.sav.bookrecipes.services.IngredientsService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
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

    @PostConstruct
    private void initIg() {
        readFromFileIg();
    }

    @Override
    public void addIngredients(Ingredients ingredients) {
        ingredientsMap.put(ingredientsID++, ingredients);
        saveToFileIg();
    }

    @Override
    public Ingredients getIngredient(int ingredientsID) {
        return ingredientsMap.get(ingredientsID);
    }

    @Override
    public void putIngredients(int ingredientsID, Ingredients newIngredients) {
        ingredientsMap.put(ingredientsID, newIngredients);
        saveToFileIg();

    }

    @Override
    public void deleteIngredients(int ingredientsID) {
        ingredientsMap.remove(ingredientsID);
    }

    @Override
    public Map<Integer, Ingredients> getAllIngredients() {
        return ingredientsMap;
    }

    private void saveToFileIg() {
        try {
            String json = new ObjectMapper().writeValueAsString(ingredientsMap);
            filesService.saveToFileIg(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFromFileIg() {
        String json = filesService.readFromFileIg();
        try {
            new ObjectMapper().readValue(json, new TypeReference<Map<Integer, Ingredients>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


}

