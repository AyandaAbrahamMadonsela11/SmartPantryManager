package com.example.smartpantrymanager.ui;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecipeRepository {

    public static List<Recipe> getRecipes() {

        List<Recipe> recipes = new ArrayList<>();

        // Maize Meal and Beans
        Map<String, Integer> recipe1 = new HashMap<>();
        recipe1.put("Maize Meal", 3);
        recipe1.put("Beans", 1);

        recipes.add(new Recipe(
                "Maize Meal and Beans",
                recipe1,
                "Prepare the maize meal according to the package instructions. "
                        + "Cook the beans until ready and serve together."
        ));

        // Chicken and Rice
        Map<String, Integer> recipe2 = new HashMap<>();
        recipe2.put("Chicken", 2);
        recipe2.put("Rice", 1);

        recipes.add(new Recipe(
                "Chicken and Rice",
                recipe2,
                "Cook the chicken thoroughly. "
                        + "Cook the rice until soft and serve together."
        ));

        // Beef and Rice
        Map<String, Integer> recipe3 = new HashMap<>();
        recipe3.put("Beef", 2);
        recipe3.put("Rice", 1);

        recipes.add(new Recipe(
                "Beef and Rice",
                recipe3,
                "Cook the beef thoroughly. "
                        + "Prepare the rice and serve together."
        ));

        // Mince and Rice
        Map<String, Integer> recipe4 = new HashMap<>();
        recipe4.put("Mince", 2);
        recipe4.put("Rice", 1);

        recipes.add(new Recipe(
                "Mince and Rice",
                recipe4,
                "Cook the mince thoroughly. "
                        + "Prepare the rice and serve together."
        ));

        // Chicken and Potato
        Map<String, Integer> recipe5 = new HashMap<>();
        recipe5.put("Chicken", 2);
        recipe5.put("Potato", 3);

        recipes.add(new Recipe(
                "Chicken and Potato",
                recipe5,
                "Cook the chicken thoroughly. "
                        + "Cook the potatoes until soft and serve together."
        ));

        // Beef and Potato
        Map<String, Integer> recipe6 = new HashMap<>();
        recipe6.put("Beef", 2);
        recipe6.put("Potato", 3);

        recipes.add(new Recipe(
                "Beef and Potato",
                recipe6,
                "Cook the beef thoroughly. "
                        + "Cook the potatoes until soft and serve together."
        ));

        // Mince and Potato
        Map<String, Integer> recipe7 = new HashMap<>();
        recipe7.put("Mince", 2);
        recipe7.put("Potato", 3);

        recipes.add(new Recipe(
                "Mince and Potato",
                recipe7,
                "Cook the mince thoroughly. "
                        + "Cook the potatoes until soft and serve together."
        ));

        // Mince and Beans
        Map<String, Integer> recipe8 = new HashMap<>();
        recipe8.put("Mince", 2);
        recipe8.put("Beans", 1);

        recipes.add(new Recipe(
                "Mince and Beans",
                recipe8,
                "Cook the mince thoroughly. "
                        + "Cook the beans until ready and serve together."
        ));

        // Rice and Beans
        Map<String, Integer> recipe9 = new HashMap<>();
        recipe9.put("Rice", 1);
        recipe9.put("Beans", 1);

        recipes.add(new Recipe(
                "Rice and Beans",
                recipe9,
                "Cook the rice until soft. "
                        + "Cook the beans until ready and serve together."
        ));

        // Beef and Maize Meal
        Map<String, Integer> recipe10 = new HashMap<>();
        recipe10.put("Beef", 2);
        recipe10.put("Maize Meal", 3);

        recipes.add(new Recipe(
                "Beef and Maize Meal",
                recipe10,
                "Cook the beef thoroughly. "
                        + "Prepare the maize meal according to the package instructions."
        ));

        // Chicken and Maize Meal
        Map<String, Integer> recipe11 = new HashMap<>();
        recipe11.put("Chicken", 2);
        recipe11.put("Maize Meal", 3);

        recipes.add(new Recipe(
                "Chicken and Maize Meal",
                recipe11,
                "Cook the chicken thoroughly. "
                        + "Prepare the maize meal and serve together."
        ));

        // Beef, Rice and Beans
        Map<String, Integer> recipe12 = new HashMap<>();
        recipe12.put("Beef", 2);
        recipe12.put("Rice", 1);
        recipe12.put("Beans", 1);

        recipes.add(new Recipe(
                "Beef, Rice and Beans",
                recipe12,
                "Cook the beef thoroughly. "
                        + "Prepare the rice and beans and serve together."
        ));

        // Chicken, Rice and Beans
        Map<String, Integer> recipe13 = new HashMap<>();
        recipe13.put("Chicken", 2);
        recipe13.put("Rice", 1);
        recipe13.put("Beans", 1);

        recipes.add(new Recipe(
                "Chicken, Rice and Beans",
                recipe13,
                "Cook the chicken thoroughly. "
                        + "Prepare the rice and beans and serve together."
        ));

        // Chicken, Potato and Rice
        Map<String, Integer> recipe14 = new HashMap<>();
        recipe14.put("Chicken", 2);
        recipe14.put("Potato", 3);
        recipe14.put("Rice", 1);

        recipes.add(new Recipe(
                "Chicken, Potato and Rice",
                recipe14,
                "Cook the chicken thoroughly. "
                        + "Cook the potatoes until soft. "
                        + "Prepare the rice and serve together."
        ));

        // Mince, Potato and Rice
        Map<String, Integer> recipe15 = new HashMap<>();
        recipe15.put("Mince", 2);
        recipe15.put("Potato", 3);
        recipe15.put("Rice", 1);

        recipes.add(new Recipe(
                "Mince, Potato and Rice",
                recipe15,
                "Cook the mince thoroughly. "
                        + "Cook the potatoes until soft. "
                        + "Prepare the rice and serve together."
        ));

        return recipes;
    }

    public static Map<String, Integer> getPantry(
            com.example.smartpantrymanager.ui.DatabaseHelper databaseHelper) {

        Map<String, Integer> pantry = new HashMap<>();

        Cursor cursor = databaseHelper.getAllIngredients();

        if (cursor != null) {

            while (cursor.moveToNext()) {

                String name = cursor.getString(
                        cursor.getColumnIndexOrThrow("name")
                );

                int quantity = cursor.getInt(
                        cursor.getColumnIndexOrThrow("quantity")
                );

                pantry.put(name, quantity);
            }

            cursor.close();
        }

        return pantry;
    }

    public static List<Recipe> getMatchingRecipes(
            com.example.smartpantrymanager.ui.DatabaseHelper databaseHelper) {

        List<Recipe> matchingRecipes = new ArrayList<>();

        Map<String, Integer> pantry =
                getPantry(databaseHelper);

        List<Recipe> recipes =
                getRecipes();

        for (Recipe recipe : recipes) {

            if (RecipeMatcher.matches(recipe, pantry)) {
                matchingRecipes.add(recipe);
            }
        }

        return matchingRecipes;
    }
}