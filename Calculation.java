package com.example.engineeringformula;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Calculation implements Initializable {

    @FXML private Label lblTitle;
    @FXML private Label input1Label, input2Label, input3Label;
    @FXML private TextField input1, input2, input3;
    @FXML private Label lblResult;

    private String solveFor;
    private Variable.Mode mode; // ✅ Mode stored locally

    public void setSolveFor(String variable) {
        this.solveFor = variable;
    }

    public void setMode(Variable.Mode mode) {
        this.mode = mode;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // setupInputs() is called manually
    }

    public void setupInputs() {
        System.out.println("setupInputs CALLED with solveFor = " + solveFor);
        System.out.println("🧪 Current Mode = " + mode);

        if (solveFor == null) {
            lblTitle.setText("Solve");
            return;
        }

        lblTitle.setText("Solve for " + solveFor);

        if (mode == Variable.Mode.VOLUME) {
            switch (solveFor) {
                case "Volume" -> {
                    input1Label.setText("Enter Length:");
                    input2Label.setText("Enter Width:");
                    input3Label.setText("Enter Height:");
                    input1.setPromptText("Length");
                    input2.setPromptText("Width");
                    input3.setPromptText("Height");
                }
                case "Length" -> {
                    input1Label.setText("Enter Volume:");
                    input2Label.setText("Enter Width:");
                    input3Label.setText("Enter Height:");
                    input1.setPromptText("Volume");
                    input2.setPromptText("Width");
                    input3.setPromptText("Height");
                }
                case "Width" -> {
                    input1Label.setText("Enter Volume:");
                    input2Label.setText("Enter Length:");
                    input3Label.setText("Enter Height:");
                    input1.setPromptText("Volume");
                    input2.setPromptText("Length");
                    input3.setPromptText("Height");
                }
                case "Height" -> {
                    input1Label.setText("Enter Volume:");
                    input2Label.setText("Enter Length:");
                    input3Label.setText("Enter Width:");
                    input1.setPromptText("Volume");
                    input2.setPromptText("Length");
                    input3.setPromptText("Width");
                }
            }
        } else if (mode == Variable.Mode.COULOMB) {
            switch (solveFor) {
                case "Charge1" -> {
                    input1Label.setText("Enter Charge 2:");
                    input2Label.setText("Enter Distance:");
                    input3Label.setText("Enter Force:");
                    input1.setPromptText("Charge 2");
                    input2.setPromptText("Distance");
                    input3.setPromptText("Force");
                }
                case "Charge2" -> {
                    input1Label.setText("Enter Charge 1:");
                    input2Label.setText("Enter Distance:");
                    input3Label.setText("Enter Force:");
                    input1.setPromptText("Charge 1");
                    input2.setPromptText("Distance");
                    input3.setPromptText("Force");
                }
                case "Distance" -> {
                    input1Label.setText("Enter Charge 1:");
                    input2Label.setText("Enter Charge 2:");
                    input3Label.setText("Enter Force:");
                    input1.setPromptText("Charge 1");
                    input2.setPromptText("Charge 2");
                    input3.setPromptText("Force");
                }
            }
        }
    }

    @FXML
    private void handleCalculate(ActionEvent event) {
        try {
            double val1 = Double.parseDouble(input1.getText());
            double val2 = Double.parseDouble(input2.getText());
            double val3 = Double.parseDouble(input3.getText());
            double result = 0;

            if (mode == Variable.Mode.VOLUME) {
                switch (solveFor) {
                    case "Volume" -> result = val1 * val2 * val3;
                    case "Length", "Width", "Height" -> result = val1 / (val2 * val3);
                }
            } else if (mode == Variable.Mode.COULOMB) {
                switch (solveFor) {
                    case "Charge1", "Charge2" -> result = (val3 * Math.pow(val2, 2)) / (8.99e9 * val1);
                    case "Distance" -> result = Math.sqrt((8.99e9 * val1 * val2) / val3);
                }
            }

            lblResult.setText("Result: " + result);
        } catch (NumberFormatException e) {
            lblResult.setText("Invalid input.");
        } catch (Exception e) {
            lblResult.setText("Error: " + e.getMessage());
        }
    }

    @FXML
    private void handleBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/engineeringformula/MainMenu.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/com/example/engineeringformula/style.css").toExternalForm());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


}
