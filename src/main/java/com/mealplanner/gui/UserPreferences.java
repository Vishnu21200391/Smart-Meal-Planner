package com.mealplanner.gui;

public class UserPreferences {
    private String dietType;
    private int mealsPerDay;

    // Default constructor for Gson
    public UserPreferences() {
    }

    public UserPreferences(String dietType, int mealsPerDay) {
        this.dietType = dietType;
        this.mealsPerDay = mealsPerDay;
    }

    public String getDietType() {
        return dietType;
    }

    public void setDietType(String dietType) {
        this.dietType = dietType;
    }

    public int getMealsPerDay() {
        return mealsPerDay;
    }

    public void setMealsPerDay(int mealsPerDay) {
        this.mealsPerDay = mealsPerDay;
    }
}