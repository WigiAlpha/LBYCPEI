package com.example.engineeringformula;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Variable implements Initializable {

    public enum Mode {
        VOLUME, COULOMB
    }

    private static Mode currentMode;

    public static Mode getCurrentMode() {
        return currentMode;
    }

    public static void setMode(Mode mode) {
        System.out.println("✅ Mode SET to: " + mode);
        currentMode = mode;
    }

    @FXML private VBox volumeBox;
    @FXML private VBox coulombBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (currentMode == Mode.VOLUME) {
            volumeBox.setVisible(true);
            volumeBox.setManaged(true);
            coulombBox.setVisible(false);
            coulombBox.setManaged(false);
        } else if (currentMode == Mode.COULOMB) {
            volumeBox.setVisible(false);
            volumeBox.setManaged(false);
            coulombBox.setVisible(true);
            coulombBox.setManaged(true);
        }
    }

    // === FORMULA HANDLERS — VOLUME ===
    @FXML private void handleVolume(ActionEvent event) throws IOException {
        setMode(Mode.VOLUME);
        loadCalculationScene(event, "Volume");
    }

    @FXML private void handleLength(ActionEvent event) throws IOException {
        setMode(Mode.VOLUME);
        loadCalculationScene(event, "Length");
    }

    @FXML private void handleWidth(ActionEvent event) throws IOException {
        setMode(Mode.VOLUME);
        loadCalculationScene(event, "Width");
    }

    @FXML private void handleHeight(ActionEvent event) throws IOException {
        setMode(Mode.VOLUME);
        loadCalculationScene(event, "Height");
    }

    // === FORMULA HANDLERS — COULOMB ===
    @FXML private void handleCoulombQ1(ActionEvent event) throws IOException {
        setMode(Mode.COULOMB);
        loadCalculationScene(event, "Charge1");
    }

    @FXML private void handleCoulombQ2(ActionEvent event) throws IOException {
        setMode(Mode.COULOMB);
        loadCalculationScene(event, "Charge2");
    }

    @FXML private void handleCoulombR(ActionEvent event) throws IOException {
        setMode(Mode.COULOMB);
        loadCalculationScene(event, "Distance");
    }

    // === LOAD CALCULATION SCENE ===
    private void loadCalculationScene(ActionEvent event, String solveForValue) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/engineeringformula/Calculation.fxml"));
        Parent root = loader.load();

        Calculation controller = loader.getController();
        controller.setSolveFor(solveForValue);
        controller.setMode(currentMode); // ✅ Pass the mode

        Platform.runLater(controller::setupInputs);

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/com/example/engineeringformula/style.css").toExternalForm());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    // === BACK TO MAIN MENU ===
    @FXML
    private void handleBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/engineeringformula/MainMenu.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/com/example/engineeringformula/style.css").toExternalForm());
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
