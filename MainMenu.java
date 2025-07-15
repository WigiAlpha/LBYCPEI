package com.example.engineeringformula;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenu {

    @FXML
    private void goToVolumeMenu(ActionEvent event) throws IOException {
        System.out.println("Volume button clicked"); // Debug
        Variable.setMode(Variable.Mode.VOLUME);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/engineeringformula/VolumeMenu.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void goToCoulombMenu(ActionEvent event) throws IOException {
        System.out.println("Coulomb button clicked"); // Debug
        Variable.setMode(Variable.Mode.COULOMB);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/engineeringformula/CoulombMenu.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void handleExit(ActionEvent event) {
        System.out.println("Exit button clicked");
        System.exit(0);
    }
}
