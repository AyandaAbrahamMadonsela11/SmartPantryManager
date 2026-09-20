package com.example.smartpantrymanager.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartpantrymanager.R;

import java.util.List;

public class SuggestedRecipesActivity
        extends AppCompatActivity {

    private DatabaseHelper databaseHelper;

    private LinearLayout recipeContainer;
    private TextView tvRecipeMessage;

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

        tvRecipeMessage =
                findViewById(
                        R.id.tvRecipeMessage
                );

        Button btnBack =
                findViewById(R.id.btnBack);

        btnBack.setOnClickListener(
                v -> finish()
        );

        displayRecipes();
    }

    @SuppressLint("SetTextI18n")
    private void displayRecipes() {

        recipeContainer.removeAllViews();

        List<Recipe> matchingRecipes =
                RecipeRepository.getMatchingRecipes(
                        databaseHelper
                );

        if (matchingRecipes.isEmpty()) {

            tvRecipeMessage.setText(
                    "No matching recipes are available with your current pantry ingredients."
            );

            TextView message =
                    new TextView(this);

            message.setText(
                    "Add more ingredients or increase their quantities to see recipe suggestions."
            );

            message.setTextSize(16);

            message.setGravity(
                    android.view.Gravity.CENTER
            );

            message.setPadding(
                    16,
                    30,
                    16,
                    30
            );

            recipeContainer.addView(message);

            return;
        }

        tvRecipeMessage.setText(
                "Recipes using your pantry ingredients:"
        );

        for (Recipe recipe :
                matchingRecipes) {

            Button recipeButton = getButton(recipe);

            recipeContainer.addView(
                    recipeButton
            );
        }
    }

    @NonNull
    private Button getButton(Recipe recipe) {
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
        return recipeButton;
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