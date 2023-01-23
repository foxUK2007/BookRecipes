package me.sav.bookrecipes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Map;
import java.util.TreeMap;

@Data
@AllArgsConstructor
public class Ingredients {

    private String ingredient;

    private int amountIngredient;

    private String measure;

}
