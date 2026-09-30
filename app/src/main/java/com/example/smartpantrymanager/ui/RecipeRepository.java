package com.example.smartpantrymanager.ui;

import android.database.Cursor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class RecipeRepository {

    // Getting all recipes
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

        // Cabbage and Beef
        Map<String, Integer> recipe16 = new HashMap<>();
        recipe16.put("Cabbage", 2);
        recipe16.put("Beef", 2);
        recipe16.put("Onion", 1);

        recipes.add(new Recipe(
                "Cabbage and Beef",
                recipe16,
                "Cook the beef thoroughly. "
                        + "Cook the cabbage and onion until soft. "
                        + "Combine and serve."
        ));

        // Chicken and Cabbage
        Map<String, Integer> recipe17 = new HashMap<>();
        recipe17.put("Chicken", 2);
        recipe17.put("Cabbage", 2);
        recipe17.put("Onion", 1);

        recipes.add(new Recipe(
                "Chicken and Cabbage",
                recipe17,
                "Cook the chicken thoroughly. "
                        + "Cook the cabbage and onion until soft. "
                        + "Combine and serve."
        ));

        // Tomato and Rama
        Map<String, Integer> recipe18 = new HashMap<>();
        recipe18.put("Tomato", 2);
        recipe18.put("Rama", 1);

        recipes.add(new Recipe(
                "Tomato and Rama",
                recipe18,
                "Cook the tomatoes with Rama. "
                        + "Cook until soft and serve."
        ));

        // Butternut and Chicken
        Map<String, Integer> recipe19 = new HashMap<>();
        recipe19.put("Butternut", 2);
        recipe19.put("Chicken", 2);
        recipe19.put("Onion", 1);

        recipes.add(new Recipe(
                "Butternut and Chicken",
                recipe19,
                "Cook the chicken thoroughly. "
                        + "Cook the butternut until soft. "
                        + "Cook the onion and combine all ingredients."
        ));

        // Sweet Potato and Chicken
        Map<String, Integer> recipe20 = new HashMap<>();
        recipe20.put("Sweet Potato", 2);
        recipe20.put("Chicken", 2);
        recipe20.put("Rama", 1);

        recipes.add(new Recipe(
                "Sweet Potato and Chicken",
                recipe20,
                "Cook the sweet potatoes until soft. "
                        + "Cook the chicken thoroughly. "
                        + "Add Rama and serve together."
        ));

        // Lettice and Tomato Salad
        Map<String, Integer> recipe21 = new HashMap<>();
        recipe21.put("Lettice", 2);
        recipe21.put("Tomato", 2);
        recipe21.put("Onion", 1);

        recipes.add(new Recipe(
                "Lettice and Tomato Salad",
                recipe21,
                "Wash the lettuce and tomatoes. "
                        + "Slice the vegetables and onion. "
                        + "Combine and serve."
        ));

        // Beef and Butternut
        Map<String, Integer> recipe22 = new HashMap<>();
        recipe22.put("Beef", 2);
        recipe22.put("Butternut", 2);
        recipe22.put("Onion", 1);

        recipes.add(new Recipe(
                "Beef and Butternut",
                recipe22,
                "Cook the beef thoroughly. "
                        + "Cook the butternut until soft. "
                        + "Cook the onion and serve together."
        ));

        // Mince and Tomato
        Map<String, Integer> recipe23 = new HashMap<>();
        recipe23.put("Mince", 2);
        recipe23.put("Tomato", 2);
        recipe23.put("Onion", 1);

        recipes.add(new Recipe(
                "Mince and Tomato",
                recipe23,
                "Cook the mince thoroughly. "
                        + "Add the tomatoes and onion. "
                        + "Cook until ready and serve."
        ));

        // Cabbage and Potato
        Map<String, Integer> recipe24 = new HashMap<>();
        recipe24.put("Cabbage", 2);
        recipe24.put("Potato", 3);
        recipe24.put("Onion", 1);

        recipes.add(new Recipe(
                "Cabbage and Potato",
                recipe24,
                "Cook the potatoes until soft. "
                        + "Cook the cabbage and onion. "
                        + "Combine and serve."
        ));

        // Mutton and Potato
        Map<String, Integer> recipe25 = new HashMap<>();
        recipe25.put("Mutton", 2);
        recipe25.put("Potato", 3);
        recipe25.put("Onion", 1);

        recipes.add(new Recipe(
                "Mutton and Potato",
                recipe25,
                "Cook the mutton thoroughly. "
                        + "Cook the potatoes until soft. "
                        + "Cook the onion and serve together."
        ));

        // Mutton and Rice
        Map<String, Integer> recipe26 = new HashMap<>();
        recipe26.put("Mutton", 2);
        recipe26.put("Rice", 1);
        recipe26.put("Onion", 1);

        recipes.add(new Recipe(
                "Mutton and Rice",
                recipe26,
                "Cook the mutton thoroughly. "
                        + "Prepare the rice and onion. "
                        + "Combine and serve."
        ));

        // Mutton and Cabbage
        Map<String, Integer> recipe27 = new HashMap<>();
        recipe27.put("Mutton", 2);
        recipe27.put("Cabbage", 2);
        recipe27.put("Onion", 1);

        recipes.add(new Recipe(
                "Mutton and Cabbage",
                recipe27,
                "Cook the mutton thoroughly. "
                        + "Cook the cabbage and onion until soft. "
                        + "Combine and serve."
        ));

        // Mutton, Potato and Rice
        Map<String, Integer> recipe28 = new HashMap<>();
        recipe28.put("Mutton", 2);
        recipe28.put("Potato", 3);
        recipe28.put("Rice", 1);

        recipes.add(new Recipe(
                "Mutton, Potato and Rice",
                recipe28,
                "Cook the mutton thoroughly. "
                        + "Cook the potatoes until soft. "
                        + "Prepare the rice and serve together."
        ));

        // Beef and Sweet Potato
        Map<String, Integer> recipe29 = new HashMap<>();
        recipe29.put("Beef", 2);
        recipe29.put("Sweet Potato", 2);
        recipe29.put("Onion", 1);

        recipes.add(new Recipe(
                "Beef and Sweet Potato",
                recipe29,
                "Cook the beef thoroughly. "
                        + "Cook the sweet potatoes until soft. "
                        + "Cook the onion and serve together."
        ));

        // Mince and Cabbage
        Map<String, Integer> recipe30 = new HashMap<>();
        recipe30.put("Mince", 2);
        recipe30.put("Cabbage", 2);
        recipe30.put("Onion", 1);

        recipes.add(new Recipe(
                "Mince and Cabbage",
                recipe30,
                "Cook the mince thoroughly. "
                        + "Cook the cabbage and onion until soft. "
                        + "Combine and serve."
        ));

        // Chicken and Sweet Potato
        Map<String, Integer> recipe31 = new HashMap<>();
        recipe31.put("Chicken", 2);
        recipe31.put("Sweet Potato", 2);
        recipe31.put("Onion", 1);

        recipes.add(new Recipe(
                "Chicken and Sweet Potato",
                recipe31,
                "Cook the chicken thoroughly. "
                        + "Cook the sweet potatoes until soft. "
                        + "Cook the onion and serve together."
        ));

        // Tomato and Onion
        Map<String, Integer> recipe32 = new HashMap<>();
        recipe32.put("Tomato", 2);
        recipe32.put("Onion", 1);
        recipe32.put("Rama", 1);

        recipes.add(new Recipe(
                "Tomato and Onion",
                recipe32,
                "Cook the tomatoes and onion with Rama. "
                        + "Cook until soft and serve."
        ));

        // Butternut and Potato
        Map<String, Integer> recipe33 = new HashMap<>();
        recipe33.put("Butternut", 2);
        recipe33.put("Potato", 3);
        recipe33.put("Rama", 1);

        recipes.add(new Recipe(
                "Butternut and Potato",
                recipe33,
                "Cook the butternut and potatoes until soft. "
                        + "Add Rama and serve."
        ));

        // Cabbage, Potato and Beef
        Map<String, Integer> recipe34 = new HashMap<>();
        recipe34.put("Cabbage", 2);
        recipe34.put("Potato", 3);
        recipe34.put("Beef", 2);

        recipes.add(new Recipe(
                "Cabbage, Potato and Beef",
                recipe34,
                "Cook the beef thoroughly. "
                        + "Cook the potatoes until soft. "
                        + "Cook the cabbage and combine all ingredients."
        ));

        // Chicken, Cabbage and Potato
        Map<String, Integer> recipe35 = new HashMap<>();
        recipe35.put("Chicken", 2);
        recipe35.put("Cabbage", 2);
        recipe35.put("Potato", 3);

        recipes.add(new Recipe(
                "Chicken, Cabbage and Potato",
                recipe35,
                "Cook the chicken thoroughly. "
                        + "Cook the potatoes until soft. "
                        + "Cook the cabbage and serve together."
        ));

        // Mutton and Sweet Potato
        Map<String, Integer> recipe36 = new HashMap<>();
        recipe36.put("Mutton", 2);
        recipe36.put("Sweet Potato", 2);
        recipe36.put("Onion", 1);

        recipes.add(new Recipe(
                "Mutton and Sweet Potato",
                recipe36,
                "Cook the mutton thoroughly. "
                        + "Cook the sweet potatoes until soft. "
                        + "Cook the onion and serve together."
        ));

        // Rice, Beans and Tomato
        Map<String, Integer> recipe37 = new HashMap<>();
        recipe37.put("Rice", 1);
        recipe37.put("Beans", 1);
        recipe37.put("Tomato", 2);

        recipes.add(new Recipe(
                "Rice, Beans and Tomato",
                recipe37,
                "Cook the rice and beans until ready. "
                        + "Cook the tomatoes and combine all ingredients."
        ));

        // Maize Meal, Beef and Onion
        Map<String, Integer> recipe38 = new HashMap<>();
        recipe38.put("Maize Meal", 3);
        recipe38.put("Beef", 2);
        recipe38.put("Onion", 1);

        recipes.add(new Recipe(
                "Maize Meal, Beef and Onion",
                recipe38,
                "Prepare the maize meal. "
                        + "Cook the beef and onion thoroughly. "
                        + "Serve together."
        ));

        // Mince, Tomato and Rice
        Map<String, Integer> recipe39 = new HashMap<>();
        recipe39.put("Mince", 2);
        recipe39.put("Tomato", 2);
        recipe39.put("Rice", 1);

        recipes.add(new Recipe(
                "Mince, Tomato and Rice",
                recipe39,
                "Cook the mince and tomatoes thoroughly. "
                        + "Prepare the rice and serve together."
        ));

        // Mutton, Cabbage and Potato
        Map<String, Integer> recipe40 = new HashMap<>();
        recipe40.put("Mutton", 2);
        recipe40.put("Cabbage", 2);
        recipe40.put("Potato", 3);

        recipes.add(new Recipe(
                "Mutton, Cabbage and Potato",
                recipe40,
                "Cook the mutton thoroughly. "
                        + "Cook the potatoes and cabbage until soft. "
                        + "Combine and serve."
        ));

        return recipes;
    }

    // Getting pantry ingredients
    public static Map<String, Integer> getPantry(
            DatabaseHelper databaseHelper) {

        Map<String, Integer> pantry = new HashMap<>();

        Cursor cursor =
                databaseHelper.getAllIngredients();

        if (cursor != null) {

            while (cursor.moveToNext()) {

                String name =
                        cursor.getString(
                                cursor.getColumnIndexOrThrow(
                                        "name"
                                )
                        );

                int quantity =
                        cursor.getInt(
                                cursor.getColumnIndexOrThrow(
                                        "quantity"
                                )
                        );

                String expiryDate =
                        cursor.getString(
                                cursor.getColumnIndexOrThrow(
                                        "expiry_date"
                                )
                        );

                // Checking expiry date
                if (isNotExpired(expiryDate)) {

                    pantry.put(
                            name,
                            quantity
                    );
                }
            }

            cursor.close();
        }

        return pantry;
    }

    // Checking if ingredient has not expired
    private static boolean isNotExpired(
            String expiryDate) {

        if (expiryDate == null ||
                expiryDate.trim().isEmpty()) {

            return false;
        }

        SimpleDateFormat dateFormat =
                new SimpleDateFormat(
                        "dd/MM/yyyy",
                        Locale.getDefault()
                );

        dateFormat.setLenient(false);

        try {

            Date expiry =
                    dateFormat.parse(expiryDate);

            Date today =
                    dateFormat.parse(
                            dateFormat.format(
                                    new Date()
                            )
                    );

            return expiry != null &&
                    today != null &&
                    !expiry.before(today);

        } catch (ParseException e) {

            return false;
        }
    }

    // Getting matching recipes
    public static List<Recipe> getMatchingRecipes(
            DatabaseHelper databaseHelper) {

        List<Recipe> matchingRecipes =
                new ArrayList<>();

        Map<String, Integer> pantry =
                getPantry(databaseHelper);

        List<Recipe> recipes =
                getRecipes();

        // Checking every recipe
        for (Recipe recipe : recipes) {

            if (RecipeMatcher.matches(
                    recipe,
                    pantry)) {

                matchingRecipes.add(recipe);
            }
        }

        return matchingRecipes;
    }
}