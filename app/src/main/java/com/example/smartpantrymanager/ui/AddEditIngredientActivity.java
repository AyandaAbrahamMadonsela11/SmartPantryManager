package com.example.smartpantrymanager.ui;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartpantrymanager.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddEditIngredientActivity
        extends AppCompatActivity {

    private EditText etIngredientName;
    private EditText etQuantity;
    private EditText etExpiryDate;

    private final Calendar selectedDate =
            Calendar.getInstance();

    private DatabaseHelper databaseHelper;
    private final SimpleDateFormat dateFormat =
            new SimpleDateFormat(
                    "dd/MM/yyyy",
                    Locale.getDefault()
            );
    private Spinner spinnerUnit;
    private int ingredientId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(
                R.layout.activity_add_edit_ingredient
        );

        databaseHelper =
                new DatabaseHelper(this);

        etIngredientName =
                findViewById(
                        R.id.etIngredientName
                );

        etQuantity =
                findViewById(
                        R.id.etQuantity
                );

        etExpiryDate =
                findViewById(
                        R.id.etExpiryDate
                );

        spinnerUnit =
                findViewById(
                        R.id.spinnerUnit
                );

        Button btnSave =
                findViewById(
                        R.id.btnSaveIngredient
                );

        Button btnCancel =
                findViewById(
                        R.id.btnCancel
                );

        // Creating the unit list
        ArrayAdapter<String> unitAdapter = getStringArrayAdapter();

        spinnerUnit.setAdapter(
                unitAdapter
        );

        // Opening the date picker
        etExpiryDate.setOnClickListener(
                v -> showDatePicker()
        );

        Intent intent =
                getIntent();

        if (intent.hasExtra("ingredient_id")) {

            ingredientId =
                    intent.getIntExtra(
                            "ingredient_id",
                            -1
                    );

            loadIngredient();

        } else {

            etExpiryDate.setText(
                    dateFormat.format(
                            selectedDate.getTime()
                    )
            );
        }

        // Saving the ingredient
        btnSave.setOnClickListener(
                v -> saveIngredient()
        );

        // Closing the screen
        btnCancel.setOnClickListener(
                v -> finish()
        );
    }

    @NonNull
    private ArrayAdapter<String> getStringArrayAdapter() {
        String[] units = {
                "g",
                "kg",
                "ml",
                "L",
                "packet",
                "tin",
                "bottle"
        };

        ArrayAdapter<String> unitAdapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_item,
                        units
                );

        unitAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );
        return unitAdapter;
    }

    // Opening the date picker
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

    // Loading an existing ingredient
    private void loadIngredient() {

        Cursor cursor =
                databaseHelper.getIngredient(
                        ingredientId
                );

        if (cursor != null &&
                cursor.moveToFirst()) {

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

            String unit =
                    cursor.getString(
                            cursor.getColumnIndexOrThrow(
                                    "unit"
                            )
                    );

            etIngredientName.setText(
                    name
            );

            etQuantity.setText(
                    String.valueOf(quantity)
            );

            etExpiryDate.setText(
                    expiryDate
            );

            // Selecting the saved unit
            @SuppressWarnings("rawtypes") ArrayAdapter adapter =
                    (ArrayAdapter) spinnerUnit.getAdapter();

            @SuppressWarnings("unchecked") int position =
                    adapter.getPosition(unit);

            if (position >= 0) {

                spinnerUnit.setSelection(
                        position
                );
            }

            cursor.close();
        }
    }

    // Saving the ingredient
    private void saveIngredient() {

        String name =
                etIngredientName
                        .getText()
                        .toString()
                        .trim();

        String quantityText =
                etQuantity
                        .getText()
                        .toString()
                        .trim();

        String expiryDate =
                etExpiryDate
                        .getText()
                        .toString()
                        .trim();

        String unit =
                spinnerUnit
                        .getSelectedItem()
                        .toString();

        // Checking the ingredient name
        if (name.isEmpty()) {

            etIngredientName.setError(
                    "Enter an ingredient name"
            );

            etIngredientName.requestFocus();

            return;
        }

        // Checking the ingredient name length
        if (name.length() >= 15) {

            etIngredientName.setError(
                    "Ingredient name must be less than 15 characters"
            );

            etIngredientName.requestFocus();

            return;
        }

        // Checking the quantity
        if (quantityText.isEmpty()) {

            etQuantity.setError(
                    "Enter a quantity"
            );

            etQuantity.requestFocus();

            return;
        }

        // Checking the quantity format
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
                    Integer.parseInt(
                            quantityText
                    );

        } catch (NumberFormatException e) {

            etQuantity.setError(
                    "Not allowed"
            );

            etQuantity.requestFocus();

            return;
        }

        // Checking that quantity is greater than zero
        if (quantity <= 0) {

            etQuantity.setError(
                    "Quantity must be greater than 0"
            );

            etQuantity.requestFocus();

            return;
        }

        // Checking the expiry date
        if (expiryDate.isEmpty()) {

            etExpiryDate.setError(
                    "Select an expiry date"
            );

            etExpiryDate.requestFocus();

            return;
        }

        if (ingredientId == -1) {

            // Adding a new ingredient
            long result =
                    databaseHelper.addIngredient(
                            name,
                            quantity,
                            expiryDate,
                            unit
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

            // Updating an existing ingredient
            int result =
                    databaseHelper.updateIngredient(
                            ingredientId,
                            name,
                            quantity,
                            expiryDate,
                            unit
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