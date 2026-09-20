package com.example.smartpantrymanager.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartpantrymanager.R;
import com.example.smartpantrymanager.ui.DatabaseHelper;

import java.util.List;

public class SuggestedRecipesActivity
        extends AppCompatActivity {

    private DatabaseHelper databaseHelper;

    private LinearLayout recipeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(
                R.layout.activity_suggested_recipes
        );

        databaseHelper =
                new DatabaseHelper(this);

        recipeContainer =
                findViewById(
                        R.id.recipeContainer
                );

        Button btnBack =
                findViewById(R.id.btnBack);

        btnBack.setOnClickListener(
                v -> finish()
        );

        displayRecipes();
    }

    private void displayRecipes() {

        recipeContainer.removeAllViews();

        List<Recipe> matchingRecipes =
                RecipeRepository.getMatchingRecipes(
                        databaseHelper
                );

        if (matchingRecipes.isEmpty()) {

            TextView message =
                    new TextView(this);

            message.setText(
                    "No recipes available with your current pantry ingredients."
            );

            message.setTextSize(18);

            recipeContainer.addView(message);

            return;
        }

        for (Recipe recipe :
                matchingRecipes) {

            Button recipeButton =
                    new Button(this);

            recipeButton.setText(
                    recipe.getName()
            );

            recipeButton.setTextSize(18);

            recipeButton.setOnClickListener(v -> {

                Intent intent =
                        new Intent(
                                SuggestedRecipesActivity.this,
                                RecipeDetailActivity.class
                        );

                intent.putExtra(
                        "recipe_name",
                        recipe.getName()
                );

                intent.putExtra(
                        "recipe_instructions",
                        recipe.getInstructions()
                );

                startActivity(intent);
            });

            recipeContainer.addView(
                    recipeButton
            );
        }
    }

    @Override
    protected void onResume() {

        super.onResume();

        if (databaseHelper != null &&
                recipeContainer != null) {

            displayRecipes();
        }
    }
}
