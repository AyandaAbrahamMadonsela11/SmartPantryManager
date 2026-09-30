package com.example.smartpantrymanager.ui;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartpantrymanager.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddEditIngredientActivity extends AppCompatActivity {

    private EditText etIngredientName;
    private EditText etQuantity;
    private final Calendar selectedDate = Calendar.getInstance();
    private final SimpleDateFormat dateFormat =
            new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

    private int ingredientId = -1;
    private EditText etExpiryDate;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_edit_ingredient);

        databaseHelper = new DatabaseHelper(this);

        etIngredientName =
                findViewById(R.id.etIngredientName);

        etQuantity =
                findViewById(R.id.etQuantity);

        etExpiryDate =
                findViewById(R.id.etExpiryDate);

        Button btnSave =
                findViewById(R.id.btnSaveIngredient);

        Button btnCancel =
                findViewById(R.id.btnCancel);

        etExpiryDate.setOnClickListener(
                v -> showDatePicker()
        );

        Intent intent = getIntent();

        if (intent.hasExtra("ingredient_id")) {

            ingredientId = intent.getIntExtra(
                    "ingredient_id",
                    -1
            );

            loadIngredient();

        } else {

            // Default expiry date is today
            etExpiryDate.setText(
                    dateFormat.format(selectedDate.getTime())
            );
        }

        btnSave.setOnClickListener(
                v -> saveIngredient()
        );

        btnCancel.setOnClickListener(
                v -> finish()
        );
    }

    private void showDatePicker() {

        DatePickerDialog datePickerDialog =
                new DatePickerDialog(
                        this,
                        (view, year, month, dayOfMonth) -> {

                            selectedDate.set(
                                    year,
                                    month,
                                    dayOfMonth
                            );

                            etExpiryDate.setText(
                                    dateFormat.format(
                                            selectedDate.getTime()
                                    )
                            );
                        },
                        selectedDate.get(
                                Calendar.YEAR
                        ),
                        selectedDate.get(
                                Calendar.MONTH
                        ),
                        selectedDate.get(
                                Calendar.DAY_OF_MONTH
                        )
                );

        datePickerDialog.show();
    }

    private void loadIngredient() {

        Cursor cursor =
                databaseHelper.getIngredient(ingredientId);

        if (cursor != null && cursor.moveToFirst()) {

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

            etIngredientName.setText(name);

            etQuantity.setText(
                    String.valueOf(quantity)
            );

            etExpiryDate.setText(expiryDate);

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

        String expiryDate =
                etExpiryDate.getText()
                        .toString()
                        .trim();

        if (name.isEmpty()) {

            etIngredientName.setError(
                    "Enter an ingredient name"
            );

            etIngredientName.requestFocus();

            return;
        }

        if (name.length() >= 15) {

            etIngredientName.setError(
                    "Ingredient name must be less than 15 characters"
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

        if (!quantityText.matches("[0-9]+")) {

            etQuantity.setError(
                    "Not allowed"
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
                    "Not allowed"
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

        if (expiryDate.isEmpty()) {

            etExpiryDate.setError(
                    "Select an expiry date"
            );

            etExpiryDate.requestFocus();

            return;
        }

        if (ingredientId == -1) {

            long result =
                    databaseHelper.addIngredient(
                            name,
                            quantity,
                            expiryDate
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
                            quantity,
                            expiryDate
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
