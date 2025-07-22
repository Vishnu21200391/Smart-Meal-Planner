package com.mealplanner.gui;

import com.mealplanner.data.DataManager;
import com.mealplanner.model.MealPlan;
import com.mealplanner.planner.MealDatabase;
import com.mealplanner.planner.MealPlanner;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import java.util.Map;
import java.util.StringJoiner;

public class NutritionPane extends VBox {
    public NutritionPane() {
        setSpacing(10);
        Button analyzeButton = new Button("Analyze Nutrition");
        TextArea analysisArea = new TextArea();
        analysisArea.setEditable(false);

        analyzeButton.setOnAction(e -> {
            try {
                MealPlan plan = DataManager.loadPlan("mealplan.json");
                if (plan == null) {
                    throw new Exception("Meal plan not found. Please generate a meal plan first.");
                }

                MealPlanner planner = new MealPlanner(MealDatabase.getDefaultMeals());
                Map<String, Double> analysis = planner.analyzeNutrition(plan);
                if (analysis == null) {
                    throw new Exception("Nutrition analysis failed. Analysis data is unavailable.");
                }

                StringBuilder analysisText = new StringBuilder();
                analysisText.append("Weekly Nutritional Analysis:\n");
                analysisText.append("Total Calories: ").append(String.format("%.2f", analysis.get("calories"))).append(" kcal\n");
                analysisText.append("Carbs: ").append(String.format("%.2f", analysis.get("carbs_percent"))).append("%\n");
                analysisText.append("Protein: ").append(String.format("%.2f", analysis.get("protein_percent"))).append("%\n");
                analysisText.append("Fat: ").append(String.format("%.2f", analysis.get("fat_percent"))).append("%\n");
                analysisArea.setText(analysisText.toString());
            } catch (Exception ex) {
                System.err.println("Error in NutritionPane: " + ex.getMessage());
                ex.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error analyzing nutrition: " + ex.getMessage());
                alert.showAndWait();
            }
        });

        getChildren().addAll(analyzeButton, analysisArea);
    }
}