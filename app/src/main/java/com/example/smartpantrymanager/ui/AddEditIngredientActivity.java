package com.example.smartpantrymanager.ui;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartpantrymanager.R;

public class AddEditIngredientActivity extends AppCompatActivity {

    private EditText etIngredientName;
    private EditText etQuantity;

    private com.example.smartpantrymanager.ui.DatabaseHelper databaseHelper;

    private int ingredientId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_edit_ingredient);

        databaseHelper =
                new com.example.smartpantrymanager.ui.DatabaseHelper(this);

        etIngredientName =
                findViewById(R.id.etIngredientName);

        etQuantity =
                findViewById(R.id.etQuantity);

        Button btnSave =
                findViewById(R.id.btnSaveIngredient);

        Button btnCancel =
                findViewById(R.id.btnCancel);

        Intent intent = getIntent();

        if (intent.hasExtra("ingredient_id")) {

            ingredientId = intent.getIntExtra(
                    "ingredient_id",
                    -1
            );

            loadIngredient();
        }

        btnSave.setOnClickListener(
                v -> saveIngredient()
        );

        btnCancel.setOnClickListener(
                v -> finish()
        );
    }

    private void loadIngredient() {

        Cursor cursor =
                databaseHelper.getIngredient(ingredientId);

        if (cursor != null && cursor.moveToFirst()) {

            String name =
                    cursor.getString(
                            cursor.getColumnIndexOrThrow("name")
                    );

            int quantity =
                    cursor.getInt(
                            cursor.getColumnIndexOrThrow("quantity")
                    );

            etIngredientName.setText(name);

            etQuantity.setText(
                    String.valueOf(quantity)
            );

            cursor.close();
        }
    }

    private void saveIngredient() {

        String name =
                etIngredientName.getText()
                        .toString()
                        .trim();

        String quantityText =
                etQuantity.getText()
                        .toString()
                        .trim();

        if (name.isEmpty()) {

            etIngredientName.setError(
                    "Enter an ingredient name"
            );

            etIngredientName.requestFocus();

            return;
        }

        if (quantityText.isEmpty()) {

            etQuantity.setError(
                    "Enter a quantity"
            );

            etQuantity.requestFocus();

            return;
        }

        int quantity;

        try {

            quantity =
                    Integer.parseInt(quantityText);

        } catch (NumberFormatException e) {

            etQuantity.setError(
                    "Enter a valid number"
            );

            etQuantity.requestFocus();

            return;
        }

        if (quantity <= 0) {

            etQuantity.setError(
                    "Quantity must be greater than 0"
            );

            etQuantity.requestFocus();

            return;
        }

        if (ingredientId == -1) {

            long result =
                    databaseHelper.addIngredient(
                            name,
                            quantity
                    );

            if (result != -1) {

                Toast.makeText(
                        this,
                        "Ingredient added",
                        Toast.LENGTH_SHORT
                ).show();

                finish();

            } else {

                Toast.makeText(
                        this,
                        "Could not add ingredient",
                        Toast.LENGTH_SHORT
                ).show();
            }

        } else {

            int result =
                    databaseHelper.updateIngredient(
                            ingredientId,
                            name,
                            quantity
                    );

            if (result > 0) {

                Toast.makeText(
                        this,
                        "Ingredient updated",
                        Toast.LENGTH_SHORT
                ).show();

                finish();

            } else {

                Toast.makeText(
                        this,
                        "Could not update ingredient",
                        Toast.LENGTH_SHORT
                ).show();
            }
        }
    }
}