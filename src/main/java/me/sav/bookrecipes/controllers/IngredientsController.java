package me.sav.bookrecipes.controllers;

import me.sav.bookrecipes.model.Ingredients;
import me.sav.bookrecipes.model.Recipes;
import me.sav.bookrecipes.services.IngredientsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/ingredients")
public class IngredientsController {
    private final IngredientsService ingredientsService;

    public IngredientsController(IngredientsService ingredientsService) {
        this.ingredientsService = ingredientsService;
    }

    @PostMapping("/add")
    public ResponseEntity<Ingredients> addIngredients(@RequestBody Ingredients ingredients) {
        ingredientsService.addIngredients(ingredients);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredients> getIngredient(@PathVariable int ingredientsID) {
        Ingredients ingredients = ingredientsService.getIngredient(ingredientsID);
        if (ingredients == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredients);
    }


    @PutMapping("/put/{id}")
    public ResponseEntity<Ingredients> putIngredients(@PathVariable int ingredientsID, @RequestBody Ingredients newIngredients) {
        ingredientsService.putIngredients(ingredientsID, newIngredients);
        return ResponseEntity.ok().body(newIngredients);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Ingredients> deleteIngredients(@PathVariable int ingredientsID) {
        ingredientsService.deleteIngredients(ingredientsID);
        return ResponseEntity.ok().build();
    }

}
