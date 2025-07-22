package com.mealplanner.planner;

import com.mealplanner.model.Meal;
import java.util.ArrayList;
import java.util.List;

public class MealDatabase {
    public static List<Meal> getDefaultMeals() {
        List<Meal> meals = new ArrayList<>();
        meals.add(new Meal("Breakfast", "Oatmeal", 150.0, 27.0, 5.0, 3.0));
        meals.add(new Meal("Lunch", "Grilled Chicken Salad", 300.0, 10.0, 30.0, 15.0));
        meals.add(new Meal("Dinner", "Vegetable Stir-Fry", 200.0, 30.0, 8.0, 5.0));
        return meals;
    }
}