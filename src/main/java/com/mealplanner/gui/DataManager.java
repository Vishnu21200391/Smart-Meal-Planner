package com.mealplanner.gui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mealplanner.model.UserPreferences;
import com.mealplanner.model.MealPlan;
import java.io.*;

public class DataManager {
    private static final String BASE_PATH = System.getProperty("user.dir") + File.separator;

    public static void savePreferences(UserPreferences prefs, String filename) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String fullPath = BASE_PATH + filename;
        System.out.println("Saving preferences to: " + fullPath);
        try (FileWriter writer = new FileWriter(fullPath)) {
            gson.toJson(prefs, writer);
        }
    }

    public static UserPreferences loadPreferences(String filename) throws IOException {
        Gson gson = new GsonBuilder().create();
        String fullPath = BASE_PATH + filename;
        File file = new File(fullPath);
        System.out.println("Loading preferences from: " + fullPath);
        if (file.exists()) {
            try (Reader reader = new FileReader(file)) {
                return gson.fromJson(reader, UserPreferences.class);
            }
        }
        return null;
    }

    public static void savePlan(MealPlan plan, String filename) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String fullPath = BASE_PATH + filename;
        System.out.println("Saving meal plan to: " + fullPath);
        try (FileWriter writer = new FileWriter(fullPath)) {
            gson.toJson(plan, writer);
        }
    }

    public static MealPlan loadPlan(String filename) throws IOException {
        Gson gson = new GsonBuilder().create();
        String fullPath = BASE_PATH + filename;
        File file = new File(fullPath);
        System.out.println("Loading meal plan from: " + fullPath);
        if (file.exists()) {
            try (Reader reader = new FileReader(file)) {
                return gson.fromJson(reader, MealPlan.class);
            }
        }
        return null;
    }
}