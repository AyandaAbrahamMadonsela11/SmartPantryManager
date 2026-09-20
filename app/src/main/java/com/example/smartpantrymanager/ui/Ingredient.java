package com.example.smartpantrymanager.ui;

public class Ingredient {

    private int id;
    private String name;
    private int quantity;

    public Ingredient(int id, String name, int quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
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
}
