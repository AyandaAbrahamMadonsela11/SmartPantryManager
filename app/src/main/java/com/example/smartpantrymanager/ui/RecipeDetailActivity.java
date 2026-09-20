package com.example.smartpantrymanager.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartpantrymanager.R;

public class RecipeDetailActivity extends AppCompatActivity {

    private TextView tvRecipeName;
    private TextView tvRecipeInstructions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recipe_detail);

        tvRecipeName = findViewById(
                R.id.tvRecipeName
        );

        tvRecipeInstructions = findViewById(
                R.id.tvRecipeInstructions
        );

        Button btnBack = findViewById(
                R.id.btnBack
        );

        String recipeName =
                getIntent().getStringExtra(
                        "recipe_name"
                );

        String recipeInstructions =
                getIntent().getStringExtra(
                        "recipe_instructions"
                );

        if (recipeName != null) {

            tvRecipeName.setText(recipeName);
        }

        if (recipeInstructions != null) {

            tvRecipeInstructions.setText(
                    recipeInstructions
            );
        }

        btnBack.setOnClickListener(
                v -> finish()
        );
    }
}