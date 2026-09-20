package com.example.smartpantrymanager;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartpantrymanager.ui.AddEditIngredientActivity;
import com.example.smartpantrymanager.ui.DatabaseHelper;
import com.example.smartpantrymanager.ui.Ingredient;
import com.example.smartpantrymanager.ui.IngredientAdapter;
import com.example.smartpantrymanager.ui.SuggestedRecipesActivity;
import com.example.smartpantrymanager.ui.SettingsActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements IngredientAdapter.OnIngredientActionListener {

    private RecyclerView recyclerView;
    private IngredientAdapter adapter;
    private DatabaseHelper databaseHelper;
    private TextView txtEmptyPantry;

    private List<Ingredient> ingredientList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);

        recyclerView = findViewById(
                R.id.recyclerViewIngredients
        );

        txtEmptyPantry = findViewById(
                R.id.txtEmptyPantry
        );

        Button btnAddIngredient =
                findViewById(R.id.btnAddIngredient);

        Button btnRecipes =
                findViewById(R.id.btnRecipes);

        Button btnSettings =
                findViewById(R.id.btnSettings);

        ingredientList = new ArrayList<>();

        adapter = new IngredientAdapter(
                ingredientList,
                this
        );

        recyclerView.setLayoutManager(
                new LinearLayoutManager(this)
        );

        recyclerView.setAdapter(adapter);

        btnAddIngredient.setOnClickListener(v -> {

            Intent intent = new Intent(
                    MainActivity.this,
                    AddEditIngredientActivity.class
            );

            startActivity(intent);
        });

        btnRecipes.setOnClickListener(v -> {

            Intent intent = new Intent(
                    MainActivity.this,
                    SuggestedRecipesActivity.class
            );

            startActivity(intent);
        });

        btnSettings.setOnClickListener(v -> {

            Intent intent = new Intent(
                    MainActivity.this,
                    SettingsActivity.class
            );

            startActivity(intent);
        });

        loadIngredients();
    }

    private void loadIngredients() {

        ingredientList.clear();

        Cursor cursor =
                databaseHelper.getAllIngredients();

        if (cursor != null) {

            while (cursor.moveToNext()) {

                int id = cursor.getInt(
                        cursor.getColumnIndexOrThrow("id")
                );

                String name = cursor.getString(
                        cursor.getColumnIndexOrThrow("name")
                );

                int quantity = cursor.getInt(
                        cursor.getColumnIndexOrThrow("quantity")
                );

                ingredientList.add(
                        new Ingredient(
                                id,
                                name,
                                quantity
                        )
                );
            }

            cursor.close();
        }

        adapter.updateList(ingredientList);

        if (ingredientList.isEmpty()) {
            txtEmptyPantry.setVisibility(View.VISIBLE);
        } else {
            txtEmptyPantry.setVisibility(View.GONE);
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
    public void onEdit(Ingredient ingredient) {

        Intent intent = new Intent(
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
    public void onDelete(Ingredient ingredient) {

        int result =
                databaseHelper.deleteIngredient(
                        ingredient.getId()
                );

        if (result > 0) {

            Toast.makeText(
                    this,
                    "Ingredient deleted",
                    Toast.LENGTH_SHORT
            ).show();

            loadIngredients();

        } else {

            Toast.makeText(
                    this,
                    "Could not delete ingredient",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(
                R.menu.main_menu,
                menu
        );

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(
            MenuItem item) {

        int itemId = item.getItemId();

        if (itemId == R.id.menu_pantry) {

            return true;

        } else if (itemId == R.id.menu_recipes) {

            Intent intent = new Intent(
                    MainActivity.this,
                    SuggestedRecipesActivity.class
            );

            startActivity(intent);

            return true;

        } else if (itemId == R.id.menu_settings) {

            Intent intent = new Intent(
                    MainActivity.this,
                    SettingsActivity.class
            );

            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}