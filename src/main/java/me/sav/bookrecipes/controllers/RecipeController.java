package me.sav.bookrecipes.controllers;

import me.sav.bookrecipes.model.Ingredients;
import me.sav.bookrecipes.model.Recipes;
import me.sav.bookrecipes.services.RecipeService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/recipe")
public class RecipeController {

    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    Recipes recipes = new Recipes("рецепт", 10, new Ingredients("ингридиент", 2, "шт"), "приготовить");

    @GetMapping("/add")
    public Recipes addRecipe() {
        recipeService.addRecipe(recipes);
        return recipeService.getRecipe(0);
    }

}
