package me.sav.bookrecipes.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Data
@AllArgsConstructor
public class Recipes {

    private String title;

    private int cookingTime;

    private Ingredients ingredients[];

    private String cookingMethod;

}
