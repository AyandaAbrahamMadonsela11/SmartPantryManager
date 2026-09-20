package com.example.smartpantrymanager.ui;

import java.util.Map;

public class Recipe {

    private final String name;

    private final Map<String, Integer> ingredients;

    private final String instructions;

    public Recipe(
            String name,
            Map<String, Integer> ingredients,
            String instructions) {

        this.name = name;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }

    public String getName() {
        return name;
    }

    public Map<String, Integer> getIngredients() {
        return ingredients;
    }

    public String getInstructions() {
        return instructions;
    }
}