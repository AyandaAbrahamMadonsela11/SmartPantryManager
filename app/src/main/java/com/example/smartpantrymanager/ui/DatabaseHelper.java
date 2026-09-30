package com.example.smartpantrymanager.ui;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME =
            "pantry.db";

    private static final int DATABASE_VERSION = 2;

    public DatabaseHelper(Context context) {

        super(
                context,
                DATABASE_NAME,
                null,
                DATABASE_VERSION
        );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(
                "CREATE TABLE pantry (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "name TEXT NOT NULL, " +
                        "quantity INTEGER NOT NULL, " +
                        "expiry_date TEXT NOT NULL)"
        );
    }

    @Override
    public void onUpgrade(
            SQLiteDatabase db,
            int oldVersion,
            int newVersion) {

        if (oldVersion < 2) {

            db.execSQL(
                    "ALTER TABLE pantry " +
                            "ADD COLUMN expiry_date " +
                            "TEXT NOT NULL " +
                            "DEFAULT '2099-12-31'"
            );
        }
    }

    public long addIngredient(
            String name,
            int quantity,
            String expiryDate) {

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

        return db.insert(
                "pantry",
                null,
                values
        );
    }

    public Cursor getAllIngredients() {

        SQLiteDatabase db =
                getReadableDatabase();

        return db.rawQuery(
                "SELECT id, name, quantity, expiry_date " +
                        "FROM pantry " +
                        "ORDER BY name",
                null
        );
    }

    public Cursor getIngredient(int id) {

        SQLiteDatabase db =
                getReadableDatabase();

        return db.rawQuery(
                "SELECT id, name, quantity, expiry_date " +
                        "FROM pantry " +
                        "WHERE id = ?",
                new String[]{
                        String.valueOf(id)
                }
        );
    }

    public int updateIngredient(
            int id,
            String name,
            int quantity,
            String expiryDate) {

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

        return db.update(
                "pantry",
                values,
                "id = ?",
                new String[]{
                        String.valueOf(id)
                }
        );
    }

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