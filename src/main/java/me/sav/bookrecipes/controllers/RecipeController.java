package me.sav.bookrecipes.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.sav.bookrecipes.model.Recipes;
import me.sav.bookrecipes.services.RecipeService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/recipe")
@Tag(name = "Книга рецептов", description = "Вкусные и простые блюда на каждый день")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping("/add")
    @Operation(
            summary = "Добавление рецептов в книгу",
            description = "Для добавления рецепта необходимо заполнить поля шаблона в теле запроса"
    )
    @ApiResponses(value =
            {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Рецепт добавлен"
                    )
            })
    @Parameters({
            @Parameter(description = "Без параметров")
    })
    public ResponseEntity<Recipes> addRecipe(@RequestBody Recipes recipes) {
        recipeService.addRecipe(recipes);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Получение любого рецепта из книги",
            description = "Получение по порядковому номеру рецепта"
    )
    @ApiResponses(value =
            {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Рецепт получен"
                    )
            })
    @Parameters(value = {
            @Parameter(name = "Порядковый номер рецепта",
                    description = "Целое число")
    })
    public ResponseEntity<Recipes> getRecipe(@PathVariable int id) {
        Recipes recipes = recipeService.getRecipe(id);
        if (recipes == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipes);
    }

    @GetMapping("/{recipe}")
    @Operation(
            summary = "Получение списка рецептов")
    @ApiResponses(value =
            {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Рецепт получен"
                    )
            })
    @Parameters(value = {
            @Parameter(name = "Порядковый номер рецепта",
                    description = "Целое число")
    })
    public ResponseEntity<Object> getRecipeReport() {
        try {
            Path path = recipeService.createRecipeReport();
            if (Files.size(path) == 0){
                return ResponseEntity.noContent().build();
            }
            InputStreamResource resource = new InputStreamResource(new FileInputStream(path.toFile()));
            return ResponseEntity.ok()
                    .contentType(MediaType.TEXT_PLAIN)
                    .contentLength(Files.size(path))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename =\"" + "-report.txt\"")
                    .body(resource);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.toString());
        }
    }


    @PutMapping("/{id}")
    @Operation(
            summary = "Изменение содержания рецептов",
            description = "Изменение по порядковому номеру рецепта и содержанию"
    )
    @ApiResponses(value =
            {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Рецепт изменен"
                    )
            })
    public ResponseEntity<Recipes> putRecipe(@PathVariable int id, @RequestBody Recipes newRecipes) {
        recipeService.putRecipe(id, newRecipes);
        return ResponseEntity.ok().body(newRecipes);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление рецептов",
            description = "Удаление по порядковому номеру рецепта"
    )
    @ApiResponses(value =
            {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Рецепт удален"
                    )
            })
    @Parameters(value = {
            @Parameter(description = "Порядковый номер рецепта")
    })
    public ResponseEntity<Recipes> deleteRecipe(@PathVariable int id) {
        recipeService.deleteRecipe(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    @Operation(
            summary = "Получение списка всех рецептов из книги",
            description = "Для получения списка рецептов не потребуется вводить никаких параметров"
    )
    @Parameters({
            @Parameter(description = "Без параметров")
    })
    public ResponseEntity<Map<Integer, Recipes>> getAllRecipe() {
        Map<Integer, Recipes> recipesMap = recipeService.getAllRecipe();
        if (recipesMap != null) {
            return ResponseEntity.ok(recipesMap);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

