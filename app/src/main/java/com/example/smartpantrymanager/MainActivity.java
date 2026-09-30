package com.example.smartpantrymanager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartpantrymanager.ui.AddEditIngredientActivity;
import com.example.smartpantrymanager.ui.DatabaseHelper;
import com.example.smartpantrymanager.ui.Ingredient;
import com.example.smartpantrymanager.ui.IngredientAdapter;
import com.example.smartpantrymanager.ui.SettingsActivity;
import com.example.smartpantrymanager.ui.SuggestedRecipesActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity
        implements IngredientAdapter.OnIngredientActionListener {

    private IngredientAdapter adapter;
    private DatabaseHelper databaseHelper;
    private TextView txtEmptyPantry;
    // Running database work away from the UI thread
    private final ExecutorService databaseExecutor =
            Executors.newSingleThreadExecutor();
    private EditText etSearchIngredient;
    private Button btnHome;
    private List<Ingredient> ingredientList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(
                R.layout.activity_main
        );

        databaseHelper =
                new DatabaseHelper(this);

        RecyclerView recyclerView =
                findViewById(
                        R.id.recyclerViewIngredients
                );

        txtEmptyPantry =
                findViewById(
                        R.id.txtEmptyPantry
                );

        etSearchIngredient =
                findViewById(
                        R.id.etSearchIngredient
                );

        btnHome =
                findViewById(
                        R.id.btnHome
                );

        Button btnAddIngredient =
                findViewById(
                        R.id.btnAddIngredient
                );

        Button btnRecipes =
                findViewById(
                        R.id.btnRecipes
                );

        Button btnSettings =
                findViewById(
                        R.id.btnSettings
                );

        Button btnCloseApp =
                findViewById(
                        R.id.btnCloseApp
                );

        ingredientList =
                new ArrayList<>();

        adapter =
                new IngredientAdapter(
                        ingredientList,
                        this
                );

        recyclerView.setLayoutManager(
                new LinearLayoutManager(this)
        );

        recyclerView.setAdapter(
                adapter
        );

        // Closing the search
        btnHome.setOnClickListener(v -> {

            // Clearing the search text
            etSearchIngredient.setText("");

            // Removing focus from search
            etSearchIngredient.clearFocus();

            // Hiding the keyboard
            InputMethodManager keyboard =
                    (InputMethodManager)
                            getSystemService(
                                    Context.INPUT_METHOD_SERVICE
                            );

            if (keyboard != null) {

                keyboard.hideSoftInputFromWindow(
                        etSearchIngredient.getWindowToken(),
                        0
                );
            }

            // Hiding Home button
            btnHome.setVisibility(
                    View.GONE
            );

            // Showing all ingredients
            adapter.updateList(
                    ingredientList
            );

            // Hiding empty message
            txtEmptyPantry.setVisibility(
                    View.GONE
            );

            // Returning to the top of the pantry
            recyclerView.scrollToPosition(
                    0
            );
        });

        // Opening Add Ingredient screen
        btnAddIngredient.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            MainActivity.this,
                            AddEditIngredientActivity.class
                    );

            startActivity(intent);
        });

        // Opening Recipe Suggestions screen
        btnRecipes.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            MainActivity.this,
                            SuggestedRecipesActivity.class
                    );

            startActivity(intent);
        });

        // Opening Settings screen
        btnSettings.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            MainActivity.this,
                            SettingsActivity.class
                    );

            startActivity(intent);
        });

        // Closing the application
        btnCloseApp.setOnClickListener(v -> {
            finishAffinity();
        });

        // Searching ingredients
        etSearchIngredient.addTextChangedListener(
                new TextWatcher() {

                    @Override
                    public void beforeTextChanged(
                            CharSequence s,
                            int start,
                            int count,
                            int after) {
                    }

                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onTextChanged(
                            CharSequence s,
                            int start,
                            int before,
                            int count) {

                        String searchText =
                                s.toString()
                                        .trim();

                        if (searchText.isEmpty()) {

                            // Hiding Home button
                            btnHome.setVisibility(
                                    View.GONE
                            );

                            // Showing all ingredients
                            adapter.updateList(
                                    ingredientList
                            );

                            if (ingredientList.isEmpty()) {

                                txtEmptyPantry.setText(
                                        "Your pantry is empty"
                                );

                                txtEmptyPantry.setVisibility(
                                        View.VISIBLE
                                );

                            } else {

                                txtEmptyPantry.setVisibility(
                                        View.GONE
                                );
                            }

                        } else {

                            // Showing Home button
                            btnHome.setVisibility(
                                    View.VISIBLE
                            );

                            // Filtering ingredients
                            filterIngredients(
                                    searchText
                            );
                        }
                    }

                    @Override
                    public void afterTextChanged(
                            Editable s) {
                    }
                }
        );

        // Loading ingredients
        loadIngredients();
    }

    // Loading ingredients
    @SuppressLint("SetTextI18n")
    private void loadIngredients() {

        databaseExecutor.execute(() -> {

            List<Ingredient> loadedList =
                    new ArrayList<>();

            Cursor cursor =
                    databaseHelper.getAllIngredients();

            if (cursor != null) {

                try {

                    while (cursor.moveToNext()) {

                        int id =
                                cursor.getInt(
                                        cursor.getColumnIndexOrThrow(
                                                "id"
                                        )
                                );

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

                        // Creating the ingredient object
                        loadedList.add(
                                new Ingredient(
                                        id,
                                        name,
                                        quantity,
                                        expiryDate,
                                        unit
                                )
                        );
                    }

                } finally {

                    cursor.close();
                }
            }

            runOnUiThread(() -> {

                ingredientList.clear();

                ingredientList.addAll(
                        loadedList
                );

                String searchText =
                        etSearchIngredient
                                .getText()
                                .toString()
                                .trim();

                if (ingredientList.isEmpty()) {

                    adapter.updateList(
                            new ArrayList<>()
                    );

                    txtEmptyPantry.setText(
                            "Your pantry is empty"
                    );

                    txtEmptyPantry.setVisibility(
                            View.VISIBLE
                    );

                } else if (searchText.isEmpty()) {

                    adapter.updateList(
                            ingredientList
                    );

                    txtEmptyPantry.setVisibility(
                            View.GONE
                    );

                } else {

                    // Searching the loaded ingredients
                    filterIngredients(
                            searchText
                    );
                }
            });
        });
    }

    // Filtering ingredients
    @SuppressLint("SetTextI18n")
    private void filterIngredients(
            String searchText) {

        List<Ingredient> filteredList =
                new ArrayList<>();

        String search =
                searchText
                        .trim()
                        .toLowerCase();

        for (Ingredient ingredient :
                ingredientList) {

            if (ingredient.getName()
                    .toLowerCase()
                    .contains(search)) {

                filteredList.add(
                        ingredient
                );
            }
        }

        adapter.updateList(
                filteredList
        );

        if (filteredList.isEmpty()) {

            if (ingredientList.isEmpty()) {

                txtEmptyPantry.setText(
                        "Your pantry is empty"
                );

            } else {

                txtEmptyPantry.setText(
                        "No ingredients found"
                );
            }

            txtEmptyPantry.setVisibility(
                    View.VISIBLE
            );

        } else {

            txtEmptyPantry.setVisibility(
                    View.GONE
            );
        }
    }

    @Override
    protected void onResume() {

        super.onResume();

        if (databaseHelper != null &&
                adapter != null) {

            // Loading the latest ingredients
            loadIngredients();
        }
    }

    @Override
    public void onEdit(
            Ingredient ingredient) {

        // Opening the edit screen
        Intent intent =
                new Intent(
                        MainActivity.this,
                        AddEditIngredientActivity.class
                );

        intent.putExtra(
                "ingredient_id",
                ingredient.getId()
        );

        startActivity(intent);
    }

    @Override
    public void onDelete(
            Ingredient ingredient) {

        databaseExecutor.execute(() -> {

            // Deleting the ingredient
            int result =
                    databaseHelper.deleteIngredient(
                            ingredient.getId()
                    );

            runOnUiThread(() -> {

                if (result > 0) {

                    Toast.makeText(
                            MainActivity.this,
                            "Ingredient deleted",
                            Toast.LENGTH_SHORT
                    ).show();

                    // Loading the updated pantry
                    loadIngredients();

                } else {

                    Toast.makeText(
                            MainActivity.this,
                            "Could not delete ingredient",
                            Toast.LENGTH_SHORT
                    ).show();
                }
            });
        });
    }

    @Override
    public boolean onCreateOptionsMenu(
            Menu menu) {

        getMenuInflater().inflate(
                R.menu.main_menu,
                menu
        );

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(
            MenuItem item) {

        int itemId =
                item.getItemId();

        if (itemId == R.id.menu_pantry) {

            // Closing the search
            etSearchIngredient.setText("");

            // Removing focus from search
            etSearchIngredient.clearFocus();

            // Hiding the keyboard
            InputMethodManager keyboard =
                    (InputMethodManager)
                            getSystemService(
                                    Context.INPUT_METHOD_SERVICE
                            );

            if (keyboard != null) {

                keyboard.hideSoftInputFromWindow(
                        etSearchIngredient.getWindowToken(),
                        0
                );
            }

            // Hiding Home button
            btnHome.setVisibility(
                    View.GONE
            );

            return true;

        } else if (itemId == R.id.menu_recipes) {

            // Opening Recipe Suggestions
            Intent intent =
                    new Intent(
                            MainActivity.this,
                            SuggestedRecipesActivity.class
                    );

            startActivity(intent);

            return true;

        } else if (itemId == R.id.menu_settings) {

            // Opening Settings
            Intent intent =
                    new Intent(
                            MainActivity.this,
                            SettingsActivity.class
                    );

            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(
                item
        );
    }

    @Override
    protected void onDestroy() {

        // Closing the database worker
        databaseExecutor.shutdown();

        super.onDestroy();
    }
}