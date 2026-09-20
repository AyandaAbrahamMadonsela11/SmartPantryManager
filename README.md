# Smart Pantry Manager

## Description

Smart Pantry Manager is an Android application developed in Java using Android Studio. The application allows users to manage pantry ingredients and quantities, view their stored ingredients, and receive recipe suggestions based on the ingredients currently available in their pantry.

The application uses strict recipe matching. A recipe is suggested only when all of its required ingredients are available in the pantry in the required quantities.

## Main Features

* Add pantry ingredients
* View pantry ingredients
* Edit existing ingredients
* Delete pantry ingredients
* Validate ingredient names and quantities
* Suggest recipes based on available pantry ingredients
* View recipe details and instructions
* SQLite database persistence
* Toolbar navigation menu
* Simple mobile-friendly interface

## Database

The application uses **SQLite** with `SQLiteOpenHelper` for local on-device data persistence.

SQLite was selected because it is suitable for storing the pantry data locally on the Android device and is consistent with the persistent data approach covered in the module.

The application implements full CRUD functionality:

* **Create** – Add a new pantry ingredient
* **Read** – View stored ingredients
* **Update** – Edit an existing ingredient
* **Delete** – Remove an ingredient

## Requirements

* Android Studio
* Android SDK
* Java
* Android emulator or Android device

## Setup and Run Instructions

1. Clone or download the Smart Pantry Manager repository.
2. Open the project in Android Studio.
3. Allow Android Studio to synchronise the Gradle files.
4. Create or select an Android emulator, or connect an Android device with USB debugging enabled.
5. Build the project using Android Studio.
6. Run the application.
7. Use the **Add Ingredient** option to add pantry ingredients.
8. Use **Recipe Suggestions** to view recipes that match the available pantry ingredients.

## Project Structure

The project contains Java Activities, XML layouts, a SQLite database helper, recipe matching logic, and a custom RecyclerView adapter.

## Scope

The application does not include:

* Google Maps
* GPS or location-based services
* Payment processing
* Real financial transactions
* Google Play Store publishing

## Author

Smart Pantry Manager – Mobile App Development 700
