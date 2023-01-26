package me.sav.bookrecipes.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.sav.bookrecipes.model.Recipes;
import me.sav.bookrecipes.services.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            summary = "Добавление рецептов в книгу"
    )
    public ResponseEntity<Recipes> addRecipe(@RequestBody Recipes recipes) {
        recipeService.addRecipe(recipes);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Получение любого рецепта из книги"
    )
    public ResponseEntity<Recipes> getRecipe(@PathVariable int id) {
        Recipes recipes = recipeService.getRecipe(id);
        if (recipes == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipes);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Изменение содержания рецептов"
    )
    public ResponseEntity<Recipes> putRecipe(@PathVariable int id, @RequestBody Recipes newRecipes) {
        recipeService.putRecipe(id, newRecipes);
        return ResponseEntity.ok().body(newRecipes);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление рецептов"
    )
    public ResponseEntity<Recipes> deleteRecipe(@PathVariable int id) {
        recipeService.deleteRecipe(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public ResponseEntity<Map<Integer, Recipes>> getAllRecipe() {
        Map<Integer, Recipes> recipesMap = recipeService.getAllRecipe();
        if (recipesMap!= null) {
            return ResponseEntity.ok(recipesMap);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

