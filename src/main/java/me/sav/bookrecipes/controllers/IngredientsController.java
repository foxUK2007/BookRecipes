package me.sav.bookrecipes.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.sav.bookrecipes.model.Ingredients;
import me.sav.bookrecipes.model.Recipes;
import me.sav.bookrecipes.services.IngredientsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@RestController
@RequestMapping("/ingredients")
@Tag(name = "Ингредиенты", description = "Список ингредиентов из всех рецептов")
public class IngredientsController {
    private final IngredientsService ingredientsService;

    public IngredientsController(IngredientsService ingredientsService) {
        this.ingredientsService = ingredientsService;
    }

    @PostMapping("/add")
    @Operation(
            summary = "Добавление ингредиентов в рецепт",
            description = "Для добавления ингредиента необходимо заполнить поля шаблона в теле запроса"
    )
    @ApiResponses(value =
            {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ингредиент добавлен"
                    )
            })
    @Parameters({
            @Parameter(description = "Без параметров")
    })
    public ResponseEntity<Ingredients> addIngredients(@RequestBody Ingredients ingredients) {
        ingredientsService.addIngredients(ingredients);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Получение любого ингредиента из рецепта",
            description = "Получение по порядковому номеру ингредиента"
    )
    @ApiResponses(value =
            {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ингредиент получен"
                    )
            })
    @Parameters(value = {
            @Parameter(name = "Порядковый номер ингредиента",
                    description = "Целое число")
    })
    public ResponseEntity<Ingredients> getIngredient(@PathVariable int id) {
        Ingredients ingredients = ingredientsService.getIngredient(id);
        if (ingredients == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredients);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Изменение содержания ингредиентов",
            description = "Изменение по порядковому номеру ингредиента"
    )
    @ApiResponses(value =
            {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ингредиент изменен"
                    )
            })
    public ResponseEntity<Ingredients> putIngredients(@PathVariable int id, @RequestBody Ingredients newIngredients) {
        ingredientsService.putIngredients(id, newIngredients);
        return ResponseEntity.ok().body(newIngredients);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление ингредиентов",
            description = "Удаление по порядковому номеру ингредиента"
    )
    @ApiResponses(value =
            {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ингредиент удален"
                    )
            })
    @Parameters(value = {
            @Parameter(description = "Порядковый номер ингредиента")
    })
    public ResponseEntity<Ingredients> deleteIngredients(@PathVariable int id) {
        ingredientsService.deleteIngredients(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    @Operation(
            summary = "Получение списка всех рецептов ингредиентов",
            description = "Для получения списка ингредиентов не потребуется вводить никаких параметров"
    )
    @Parameters({
            @Parameter(description = "Без параметров")
    })
    public ResponseEntity<Map<Integer, Ingredients>> getAllIngredients() {
        Map<Integer, Ingredients> ingredientsMap = ingredientsService.getAllIngredients();
        if (ingredientsMap != null) {
            return ResponseEntity.ok(ingredientsMap);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
