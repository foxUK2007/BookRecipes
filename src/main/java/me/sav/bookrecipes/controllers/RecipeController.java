package me.sav.bookrecipes.controllers;

import me.sav.bookrecipes.model.Recipes;
import me.sav.bookrecipes.services.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping("/add")
    public ResponseEntity<Recipes> addRecipe(@RequestBody Recipes recipes) {
       recipeService.addRecipe(recipes);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipes> getRecipe(@PathVariable int id) {
        Recipes recipes = recipeService.getRecipe(id);
        if (recipes == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipes);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Recipes> putRecipe(@PathVariable int id, @RequestBody Recipes newRecipes) {
        recipeService.putRecipe(id, newRecipes);
        return ResponseEntity.ok().body(newRecipes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Recipes> deleteRecipe(@PathVariable int id) {
        recipeService.deleteRecipe(id);
        return ResponseEntity.ok().build();
    }

}

