package com.mealplanner;

import com.mealplanner.gui.PreferencesPane;
import com.mealplanner.gui.MealPlanPane;
import com.mealplanner.gui.NutritionPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) {
        stage.setTitle("Smart Meal Planner");

        // Create TabPane
        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        // Create pane instances
        PreferencesPane prefPane = new PreferencesPane();
        MealPlanPane planPane = new MealPlanPane();
        NutritionPane nutritionPane = new NutritionPane();

        // Add tabs with panes as content
        Tab prefTab = new Tab("Preferences", prefPane);
        Tab planTab = new Tab("Meal Plan", planPane);
        Tab nutritionTab = new Tab("Nutrition", nutritionPane);

        tabPane.getTabs().addAll(prefTab, planTab, nutritionTab);

        // Use BorderPane as the root to manage the TabPane
        BorderPane root = new BorderPane();
        root.setCenter(tabPane);

        // Create and set the scene
        Scene scene = new Scene(root, 600, 400);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}