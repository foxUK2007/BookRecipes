package me.sav.bookrecipes.services.impl;

import me.sav.bookrecipes.services.FilesService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FilesServiceImpl implements FilesService {

    @Value("${path.to.data.file}")
    private String dataFilePath;

    @Value("${name.of.recipe.file}")
    private String recipeFileName;

    @Value("${name.of.ingredient.file}")
    private String ingredientFileName;

    @Override
    public boolean saveToFileRc(String json) {
        try {
            Files.writeString(Path.of(dataFilePath, recipeFileName), json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String readFromFileRc() {
        try {
            return Files.readString(Path.of(dataFilePath, recipeFileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean saveToFileIg(String json) {
        try {
            Files.writeString(Path.of(dataFilePath, ingredientFileName), json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String readFromFileIg() {
        try {
            return Files.readString(Path.of(dataFilePath, ingredientFileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean cleanDataFileRc() {
        try {
            Path path = Path.of(dataFilePath, recipeFileName);
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            return false;
        }

    }

    private boolean cleanDataFileIg() {
        try {
            Path path = Path.of(dataFilePath, ingredientFileName);
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            return false;
        }

    }

}
