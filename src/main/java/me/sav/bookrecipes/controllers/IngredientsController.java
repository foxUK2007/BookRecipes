package me.sav.bookrecipes.controllers;

import me.sav.bookrecipes.model.Ingredients;
import me.sav.bookrecipes.model.Recipes;
import me.sav.bookrecipes.services.IngredientsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    public ResponseEntity<Ingredients> getIngredient(@PathVariable int id) {
        Ingredients ingredients = ingredientsService.getIngredient(id);
        if (ingredients == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredients);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Ingredients> putIngredients(@PathVariable int id, @RequestBody Ingredients newIngredients) {
        ingredientsService.putIngredients(id, newIngredients);
        return ResponseEntity.ok().body(newIngredients);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Ingredients> deleteIngredients(@PathVariable int id) {
        ingredientsService.deleteIngredients(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public ResponseEntity<Map<Integer, Ingredients>> getAllIngredients() {
        Map<Integer, Ingredients> ingredientsMap = ingredientsService.getAllIngredients();
        if (ingredientsMap!= null) {
            return ResponseEntity.ok(ingredientsMap);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
