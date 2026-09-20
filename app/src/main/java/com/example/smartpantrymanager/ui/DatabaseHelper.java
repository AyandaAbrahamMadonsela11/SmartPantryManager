package com.example.smartpantrymanager.ui;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "pantry.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(
                "CREATE TABLE pantry (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "name TEXT NOT NULL, " +
                        "quantity INTEGER NOT NULL)"
        );
    }

    @Override
    public void onUpgrade(
            SQLiteDatabase db,
            int oldVersion,
            int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS pantry");

        onCreate(db);
    }

    // Adding ingredient
    public long addIngredient(String name, int quantity) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("quantity", quantity);

        long result = db.insert("pantry", null, values);

        db.close();

        return result;
    }

    // Getting all ingredients
    public Cursor getAllIngredients() {

        SQLiteDatabase db = this.getReadableDatabase();

        return db.rawQuery(
                "SELECT id, name, quantity FROM pantry ORDER BY name",
                null
        );
    }

    // Getting one ingredient
    public Cursor getIngredient(int id) {

        SQLiteDatabase db = this.getReadableDatabase();

        return db.rawQuery(
                "SELECT id, name, quantity FROM pantry WHERE id = ?",
                new String[]{String.valueOf(id)}
        );
    }

    // Updating ingredient
    public int updateIngredient(
            int id,
            String name,
            int quantity) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("quantity", quantity);

        int result = db.update(
                "pantry",
                values,
                "id = ?",
                new String[]{String.valueOf(id)}
        );

        db.close();

        return result;
    }

    // Deleting ingredient
    public int deleteIngredient(int id) {

        SQLiteDatabase db = this.getWritableDatabase();

        int result = db.delete(
                "pantry",
                "id = ?",
                new String[]{String.valueOf(id)}
        );

        db.close();

        return result;
    }

    // Ingredient model
    public static class Ingredient {

        public int id;
        public String name;
        public int quantity;

        public Ingredient(int id, String name, int quantity) {
            this.id = id;
            this.name = name;
            this.quantity = quantity;
        }
    }
}