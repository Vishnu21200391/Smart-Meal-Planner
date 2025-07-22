module com.mealplanner {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    // Open the model package to Gson for reflection
    opens com.mealplanner.model to com.google.gson;

    // Export the com.mealplanner package to javafx.graphics
    exports com.mealplanner to javafx.graphics;
}