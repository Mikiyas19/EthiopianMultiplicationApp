package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    private TextField multiplicandField;
    private TextField multiplierField;
    private Label resultLabel;

    @Override
    public void start(Stage primaryStage) {
        // Create UI components
        multiplicandField = new TextField();
        multiplierField = new TextField();
        resultLabel = new Label("Result will be displayed here.");

        Button calculateButton = new Button("Calculate");

        // Set button action
        calculateButton.setOnAction(e -> calculateEthiopianMultiplication());

        // Create layout
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(8);
        gridPane.setHgap(10);

        // Add components to layout
        gridPane.add(new Label("Enter Multiplicand:"), 0, 0);
        gridPane.add(multiplicandField, 1, 0);
        gridPane.add(new Label("Enter Multiplier:"), 0, 1);
        gridPane.add(multiplierField, 1, 1);
        gridPane.add(calculateButton, 0, 2);
        gridPane.add(resultLabel, 0, 3, 2, 1);

        // Set up the scene and stage
        Scene scene = new Scene(gridPane, 400, 200);
        primaryStage.setTitle("Ethiopian Multiplication");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void calculateEthiopianMultiplication() {
        try {
            int multiplicand = Integer.parseInt(multiplicandField.getText());
            int multiplier = Integer.parseInt(multiplierField.getText());

            StringBuilder resultBuilder = new StringBuilder();
            int result = 0;

            // Header for the output
            resultBuilder.append(String.format("%-10s %-10s\n", "Multiplier", "Multiplicand"));

            while (multiplier > 0) {
                // If multiplier is odd, add the current multiplicand to the result
                if (multiplier % 2 == 1) {
                    resultBuilder.append(String.format("%-10d %-10d\n", multiplier, multiplicand));
                    result += multiplicand;
                } else {
                    resultBuilder.append(String.format("%-10d [%d]\n", multiplier, multiplicand));
                }

                // Halve the multiplier and double the multiplicand
                multiplier /= 2;
                multiplicand *= 2;
            }

            // Display the final result
            resultBuilder.append("=".repeat(22)).append("\n");
            resultBuilder.append("Result: ").append(result);

            resultLabel.setText(resultBuilder.toString());
        } catch (NumberFormatException e) {
            resultLabel.setText("Please enter valid integers.");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
