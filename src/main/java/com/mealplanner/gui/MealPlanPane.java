package com.mealplanner.gui;

import com.mealplanner.data.DataManager;
import com.mealplanner.model.Meal;
import com.mealplanner.model.MealPlan;
import com.mealplanner.model.UserPreferences;
import com.mealplanner.planner.MealDatabase;
import com.mealplanner.planner.MealPlanner;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import java.util.List;
import java.util.StringJoiner;

public class MealPlanPane extends VBox {
    public MealPlanPane() {
        setSpacing(10);
        Button generateButton = new Button("Generate Meal Plan");
        TextArea planArea = new TextArea();
        planArea.setEditable(false);

        generateButton.setOnAction(e -> {
            try {
                UserPreferences prefs = DataManager.loadPreferences("preferences.json");
                if (prefs == null) {
                    throw new Exception("Preferences file not found. Please save preferences first.");
                }

                List<Meal> meals = MealDatabase.getDefaultMeals();
                if (meals == null || meals.isEmpty()) {
                    throw new Exception("No meals available in the database.");
                }

                MealPlanner planner = new MealPlanner(meals);
                MealPlan plan = planner.generatePlan(prefs);
                if (plan == null) {
                    throw new Exception("Failed to generate meal plan.");
                }

                DataManager.savePlan(plan, "mealplan.json");

                StringBuilder planText = new StringBuilder();
                for (int day = 1; day <= 7; day++) {
                    planText.append("Day ").append(day).append(":\n");
                    List<Meal> dailyMeals = plan.getDailyPlan(day);
                    if (dailyMeals != null) {
                        for (Meal meal : dailyMeals) {
                            planText.append("- ").append(meal.getType()).append(": ").append(meal.getName()).append("\n");
                        }
                    }
                    planText.append("\n");
                }
                planArea.setText(planText.toString());
            } catch (Exception ex) {
                System.err.println("Error in MealPlanPane: " + ex.getMessage());
                ex.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error generating plan: " + ex.getMessage());
                alert.showAndWait();
            }
        });

        getChildren().addAll(generateButton, planArea);
    }
}