package com.mealplanner.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MealPlan {
    private Map<Integer, List<Meal>> weeklyPlan;

    public MealPlan() {
        this.weeklyPlan = new HashMap<>();
    }

    public void setDailyPlan(int day, List<Meal> meals) {
        weeklyPlan.put(day, meals != null ? new ArrayList<>(meals) : new ArrayList<>());
    }

    public List<Meal> getDailyPlan(int day) {
        return weeklyPlan.getOrDefault(day, new ArrayList<>());
    }
}