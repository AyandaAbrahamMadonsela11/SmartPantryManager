package com.example.smartpantrymanager.ui;

import java.util.HashMap;
import java.util.Map;

public class RecipeMatcher {

    // Checking if all recipe ingredients are available
    public static boolean matches(
            Recipe recipe,
            Map<String, Integer> pantry) {

        // Creating a quick pantry lookup
        Map<String, Integer> normalisedPantry =
                new HashMap<>();

        for (Map.Entry<String, Integer> available :
                pantry.entrySet()) {

            String name =
                    normalise(
                            available.getKey()
                    );

            int quantity =
                    available.getValue();

            normalisedPantry.put(
                    name,
                    quantity
            );
        }

        // Checking every recipe ingredient
        for (Map.Entry<String, Integer> required :
                recipe.getIngredients().entrySet()) {

            String requiredName =
                    normalise(
                            required.getKey()
                    );

            int requiredQuantity =
                    required.getValue();

            Integer availableQuantity =
                    normalisedPantry.get(
                            requiredName
                    );

            // Ingredient is missing
            if (availableQuantity == null) {
                return false;
            }

            // Not enough quantity
            if (availableQuantity <
                    requiredQuantity) {

                return false;
            }
        }

        return true;
    }

    // Handles simple singular and plural names
    private static String normalise(
            String name) {

        String value =
                name
                        .trim()
                        .toLowerCase();

        if (value.endsWith("ies")) {

            value =
                    value.substring(
                            0,
                            value.length() - 3
                    ) + "y";

        } else if (value.endsWith("es")) {

            value =
                    value.substring(
                            0,
                            value.length() - 2
                    );

        } else if (value.endsWith("s")) {

            value =
                    value.substring(
                            0,
                            value.length() - 1
                    );
        }

        return value;
    }
}