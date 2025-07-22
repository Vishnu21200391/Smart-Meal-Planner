package com.mealplanner.gui;

import com.mealplanner.data.DataManager;
import com.mealplanner.model.UserPreferences;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class PreferencesPane extends GridPane {
    public PreferencesPane() {
        setPadding(new Insets(10));
        setVgap(10);
        setHgap(10);

        Label dietLabel = new Label("Diet Type:");
        ComboBox<String> dietCombo = new ComboBox<>();
        dietCombo.getItems().addAll("Vegetarian", "Low-Carb", "Balanced");
        dietCombo.setValue("Balanced");

        Label mealsLabel = new Label("Meals per Day:");
        Spinner<Integer> mealsSpinner = new Spinner<>(1, 5, 3);

        Button saveButton = new Button("Save Preferences");
        saveButton.setOnAction(e -> {
            UserPreferences prefs = new UserPreferences(dietCombo.getValue(), mealsSpinner.getValue());
            try {
                DataManager.savePreferences(prefs, "preferences.json");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Preferences saved!");
                alert.showAndWait();
            } catch (Exception ex) {
                System.err.println("Error in PreferencesPane: " + ex.getMessage());
                ex.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error saving preferences: " + ex.getMessage());
                alert.showAndWait();
            }
        });

        add(dietLabel, 0, 0);
        add(dietCombo, 1, 0);
        add(mealsLabel, 0, 1);
        add(mealsSpinner, 1, 1);
        add(saveButton, 1, 2);
    }
}