package com.mealplanner.planner;

import com.mealplanner.model.Meal;
import com.mealplanner.model.MealPlan;
import com.mealplanner.model.UserPreferences;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MealPlanner {
    private List<Meal> meals;

    public MealPlanner(List<Meal> meals) {
        this.meals = meals != null ? meals : new ArrayList<>();
    }

    public MealPlan generatePlan(UserPreferences prefs) {
        if (prefs == null) {
            return null;
        }
        MealPlan plan = new MealPlan();
        int mealsPerDay = prefs.getMealsPerDay();
        for (int day = 1; day <= 7; day++) {
            List<Meal> dailyMeals = new ArrayList<>();
            for (int i = 0; i < mealsPerDay; i++) {
                if (!meals.isEmpty()) {
                    dailyMeals.add(meals.get(i % meals.size()));
                }
            }
            plan.setDailyPlan(day, dailyMeals);
        }
        return plan;
    }

    public Map<String, Double> analyzeNutrition(MealPlan plan) {
        if (plan == null) {
            return null;
        }
        Map<String, Double> analysis = new HashMap<>();
        double totalCalories = 0.0;
        double totalCarbs = 0.0;
        double totalProtein = 0.0;
        double totalFat = 0.0;

        for (int day = 1; day <= 7; day++) {
            List<Meal> dailyMeals = plan.getDailyPlan(day);
            if (dailyMeals != null) {
                for (Meal meal : dailyMeals) {
                    totalCalories += meal.getCalories();
                    totalCarbs += meal.getCarbs();
                    totalProtein += meal.getProtein();
                    totalFat += meal.getFat();
                }
            }
        }

        double totalMacros = totalCarbs + totalProtein + totalFat;
        if (totalMacros > 0) {
            analysis.put("calories", totalCalories);
            analysis.put("carbs_percent", (totalCarbs / totalMacros) * 100);
            analysis.put("protein_percent", (totalProtein / totalMacros) * 100);
            analysis.put("fat_percent", (totalFat / totalMacros) * 100);
        } else {
            analysis.put("calories", 0.0);
            analysis.put("carbs_percent", 0.0);
            analysis.put("protein_percent", 0.0);
            analysis.put("fat_percent", 0.0);
        }
        return analysis;
    }
}