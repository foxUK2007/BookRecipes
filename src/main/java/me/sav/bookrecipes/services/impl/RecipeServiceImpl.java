package me.sav.bookrecipes.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.sav.bookrecipes.model.Recipes;
import me.sav.bookrecipes.services.RecipeService;
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
public class RecipeServiceImpl implements RecipeService {

    final private FilesServiceImpl filesService;

    private Map<Integer, Recipes> recipesMap = new TreeMap<>();

    static int recipeID = 0;

    public RecipeServiceImpl(FilesServiceImpl filesService) {
        this.filesService = filesService;
    }

    @PostConstruct
    private void initRc() {
        readFromFileRc();
    }

    @Override
    public void addRecipe(Recipes recipes) {
        recipesMap.put(recipeID++, recipes);
        saveToFileRc();

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
    public Map<Integer, Recipes> getAllRecipe() {
        return recipesMap;
    }

    @Override
    public void putRecipe(int recipeID, Recipes newRecipes) {
        recipesMap.put(recipeID, newRecipes);
        saveToFileRc();

    }

    @Override
    public Path createRecipeReport(Recipes recipes) throws IOException {
        Map<Integer, Recipes> recipesMapOrDefault = (Map<Integer, Recipes>) recipesMap.getOrDefault(recipes, new Recipes());
        Path path = filesService.createTempFileRc("recipeReport");
        for (Recipes recipe : recipesMapOrDefault.values()) {
            try (Writer writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
                writer.append(recipe.getTitle() + recipe.getCookingMethod() + recipe.getCookingTime());
                writer.append("\n");
            }
        }
        return path;
    }


    private void saveToFileRc() {
        try {
            String json = new ObjectMapper().writeValueAsString(recipesMap);
            filesService.saveToFileRc(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFromFileRc() {
        String json = filesService.readFromFileRc();
        try {
            new ObjectMapper().readValue(json, new TypeReference<Map<Integer, Recipes>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}

