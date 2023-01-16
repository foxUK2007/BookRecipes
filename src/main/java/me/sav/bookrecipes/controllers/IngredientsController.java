package me.sav.bookrecipes.controllers;

import me.sav.bookrecipes.model.Ingredients;
import me.sav.bookrecipes.services.IngredientsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredients")
public class IngredientsController {
    private IngredientsService ingredientsService;

    public IngredientsController(IngredientsService ingredientsService) {
        this.ingredientsService = ingredientsService;
    }

    Ingredients ingredients = new Ingredients("Картофель", 4, "шт.");

    @GetMapping("/add")
    public String addIngredients() {
        ingredientsService.addIngredients(ingredients);
        return "Ингридиенты: " + ingredients;
    }

    }
