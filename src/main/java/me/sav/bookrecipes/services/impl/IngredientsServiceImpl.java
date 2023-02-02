package me.sav.bookrecipes.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.sav.bookrecipes.model.Ingredients;
import me.sav.bookrecipes.services.IngredientsService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.nio.file.Path;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
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
    public Path createIngredientsReport(Ingredients ingredients) throws IOException {
        Map<Integer, Ingredients> ingredientsMapOrDefault = (Map<Integer, Ingredients>) ingredientsMap.getOrDefault(ingredients, new Ingredients());
        Path path =filesService.createTempFileRc("ingredientsReport");
        for (Ingredients ingredient : ingredientsMapOrDefault.values()) {
            try (Writer writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
                writer.append(ingredient.getIngredient() + ingredient.getAmountIngredient() + ingredient.getMeasure());
                writer.append("\n");
            }
        }
        return path;
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

