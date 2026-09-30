package com.example.smartpantrymanager;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
    // Saving time for crashing, Runs database work away from the UI thread
    private final ExecutorService databaseExecutor =
            Executors.newSingleThreadExecutor();

    private List<Ingredient> ingredientList;
    private EditText etSearchIngredient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        databaseHelper =
                new DatabaseHelper(this);

        RecyclerView recyclerView =
                findViewById(R.id.recyclerViewIngredients);

        txtEmptyPantry =
                findViewById(R.id.txtEmptyPantry);

        etSearchIngredient =
                findViewById(R.id.etSearchIngredient);

        Button btnAddIngredient =
                findViewById(R.id.btnAddIngredient);

        Button btnRecipes =
                findViewById(R.id.btnRecipes);

        Button btnSettings =
                findViewById(R.id.btnSettings);

        Button btnCloseApp =
                findViewById(R.id.btnCloseApp);

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

        recyclerView.setAdapter(adapter);

        // Open Add Ingredient screen
        btnAddIngredient.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            MainActivity.this,
                            AddEditIngredientActivity.class
                    );

            startActivity(intent);
        });

        // Open Recipe Suggestions screen
        btnRecipes.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            MainActivity.this,
                            SuggestedRecipesActivity.class
                    );

            startActivity(intent);
        });

        // Open Settings screen
        btnSettings.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            MainActivity.this,
                            SettingsActivity.class
                    );

            startActivity(intent);
        });

        // Close application
        btnCloseApp.setOnClickListener(v -> {
            finishAffinity();
        });

        // Search ingredients
        etSearchIngredient.addTextChangedListener(
                new TextWatcher() {

                    @Override
                    public void beforeTextChanged(
                            CharSequence s,
                            int start,
                            int count,
                            int after) {
                    }

                    @Override
                    public void onTextChanged(
                            CharSequence s,
                            int start,
                            int before,
                            int count) {

                        filterIngredients(
                                s.toString()
                        );
                    }

                    @Override
                    public void afterTextChanged(
                            Editable s) {
                    }
                }
        );

        loadIngredients();
    }

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

                        loadedList.add(
                                new Ingredient(
                                        id,
                                        name,
                                        quantity,
                                        expiryDate
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

                    filterIngredients(
                            searchText
                    );
                }
            });
        });
    }

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

            loadIngredients();
        }
    }

    @Override
    public void onEdit(
            Ingredient ingredient) {

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

            return true;

        } else if (itemId == R.id.menu_recipes) {

            Intent intent =
                    new Intent(
                            MainActivity.this,
                            SuggestedRecipesActivity.class
                    );

            startActivity(intent);

            return true;

        } else if (itemId == R.id.menu_settings) {

            Intent intent =
                    new Intent(
                            MainActivity.this,
                            SettingsActivity.class
                    );

            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {

        databaseExecutor.shutdown();

        super.onDestroy();
    }
}