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
    public ResponseEntity<Recipes> getRecipe(@PathVariable int number) {
        Recipes recipes = recipeService.getRecipe(number);
        if (recipes == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipes);
    }


    @PutMapping("/put/{id}")
    public ResponseEntity<Recipes> putRecipe(@PathVariable int recipeID, @RequestBody Recipes newRecipes) {
        recipeService.putRecipe(recipeID, newRecipes);
        return ResponseEntity.ok().body(newRecipes);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Recipes> deleteRecipe(@PathVariable int id) {
        recipeService.deleteRecipe(id);
        return ResponseEntity.ok().build();
    }

}

