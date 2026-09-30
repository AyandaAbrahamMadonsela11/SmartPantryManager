package com.example.smartpantrymanager.ui;

public class Ingredient {

    private final int id;
    private final String name;
    private final int quantity;
    private final String expiryDate;

    public Ingredient(
            int id,
            String name,
            int quantity,
            String expiryDate) {

        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getExpiryDate() {
        return expiryDate;
    }
}

