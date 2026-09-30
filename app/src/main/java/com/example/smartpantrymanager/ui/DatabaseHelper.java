package com.example.smartpantrymanager.ui;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME =
            "pantry.db";

    private static final int DATABASE_VERSION = 3;

    public DatabaseHelper(Context context) {
        super(
                context,
                DATABASE_NAME,
                null,
                DATABASE_VERSION
        );
    }

    // Creating the database table
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(
                "CREATE TABLE pantry (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "name TEXT NOT NULL, " +
                        "quantity INTEGER NOT NULL, " +
                        "expiry_date TEXT NOT NULL, " +
                        "unit TEXT NOT NULL DEFAULT 'unit')"
        );
    }

    // Updating the database when the version changes
    @Override
    public void onUpgrade(
            SQLiteDatabase db,
            int oldVersion,
            int newVersion) {

        // Adding the expiry date column
        if (oldVersion < 2) {

            db.execSQL(
                    "ALTER TABLE pantry " +
                            "ADD COLUMN expiry_date " +
                            "TEXT NOT NULL " +
                            "DEFAULT '2099-12-31'"
            );
        }

        // Adding the unit column
        if (oldVersion < 3) {

            db.execSQL(
                    "ALTER TABLE pantry " +
                            "ADD COLUMN unit " +
                            "TEXT NOT NULL " +
                            "DEFAULT 'unit'"
            );
        }
    }

    // Adding an ingredient
    public long addIngredient(
            String name,
            int quantity,
            String expiryDate,
            String unit) {

        SQLiteDatabase db =
                getWritableDatabase();

        ContentValues values =
                new ContentValues();

        values.put(
                "name",
                name
        );

        values.put(
                "quantity",
                quantity
        );

        values.put(
                "expiry_date",
                expiryDate
        );

        values.put(
                "unit",
                unit
        );

        return db.insert(
                "pantry",
                null,
                values
        );
    }

    // Getting all ingredients
    public Cursor getAllIngredients() {

        SQLiteDatabase db =
                getReadableDatabase();

        return db.rawQuery(
                "SELECT id, name, quantity, expiry_date, unit " +
                        "FROM pantry " +
                        "ORDER BY name",
                null
        );
    }

    // Getting one ingredient
    public Cursor getIngredient(int id) {

        SQLiteDatabase db =
                getReadableDatabase();

        return db.rawQuery(
                "SELECT id, name, quantity, expiry_date, unit " +
                        "FROM pantry " +
                        "WHERE id = ?",
                new String[]{
                        String.valueOf(id)
                }
        );
    }

    // Updating an ingredient
    public int updateIngredient(
            int id,
            String name,
            int quantity,
            String expiryDate,
            String unit) {

        SQLiteDatabase db =
                getWritableDatabase();

        ContentValues values =
                new ContentValues();

        values.put(
                "name",
                name
        );

        values.put(
                "quantity",
                quantity
        );

        values.put(
                "expiry_date",
                expiryDate
        );

        values.put(
                "unit",
                unit
        );

        return db.update(
                "pantry",
                values,
                "id = ?",
                new String[]{
                        String.valueOf(id)
                }
        );
    }

    // Deleting an ingredient
    public int deleteIngredient(int id) {

        SQLiteDatabase db =
                getWritableDatabase();

        return db.delete(
                "pantry",
                "id = ?",
                new String[]{
                        String.valueOf(id)
                }
        );
    }
}