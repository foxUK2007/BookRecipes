package me.sav.bookrecipes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ingredients {

    private String ingredient;

    private int amountIngredient;

    private String measure;

}
