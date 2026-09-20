package com.example.smartpantrymanager.ui;

import java.util.Map;

public class RecipeMatcher {

    // Checks if all recipe ingredients are available
    public static boolean matches(
            Recipe recipe,
            Map<String, Integer> pantry) {

        for (Map.Entry<String, Integer> required :
                recipe.getIngredients().entrySet()) {

            String requiredName =
                    normalise(required.getKey());

            int requiredQuantity =
                    required.getValue();

            boolean found = false;

            for (Map.Entry<String, Integer> available :
                    pantry.entrySet()) {

                String pantryName =
                        normalise(available.getKey());

                if (pantryName.equals(requiredName)) {

                    int pantryQuantity =
                            available.getValue();

                    if (pantryQuantity >= requiredQuantity) {
                        found = true;
                    }

                    break;
                }
            }

            if (!found) {
                return false;
            }
        }

        return true;
    }

    // Handles simple singular and plural names
    private static String normalise(String name) {

        String value = name
                .trim()
                .toLowerCase();

        if (value.endsWith("ies")) {

            value = value.substring(
                    0,
                    value.length() - 3
            ) + "y";

        } else if (value.endsWith("es")) {

            value = value.substring(
                    0,
                    value.length() - 2
            );

        } else if (value.endsWith("s")) {

            value = value.substring(
                    0,
                    value.length() - 1
            );
        }

        return value;
    }
}