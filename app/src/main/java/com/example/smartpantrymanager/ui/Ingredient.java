package com.example.smartpantrymanager.ui;

public class Ingredient {

    private final int id;
    private final String name;
    private final int quantity;
    private final String expiryDate;
    private final String unit;

    // Creating an ingredient
    public Ingredient(
            int id,
            String name,
            int quantity,
            String expiryDate,
            String unit) {

        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
        this.unit = unit;
    }

    // Getting the ingredient ID
    public int getId() {
        return id;
    }

    // Getting the ingredient name
    public String getName() {
        return name;
    }

    // Getting the ingredient quantity
    public int getQuantity() {
        return quantity;
    }

    // Getting the expiry date
    public String getExpiryDate() {
        return expiryDate;
    }

    // Getting the ingredient unit
    public String getUnit() {
        return unit;
    }
}