/**
 * Course: LBYCPEI - OBJECT-ORIENTED PROGRAMMING LABORATORY
 * ASSIGNMENT: Module 7: Graphic User Interface (GUI)
 *
 * FILENAME: MainApp.java
 * STUDENT NAME: JOHN LESTER R. BLAS
 * STUDENT ID: 12475351
 * SECTION: XXE1
 * DATE: 07/14/2025
 *
 * DESCRIPTION: Main Application launcher.
 *
 * HONOR CODE: I have answered this activity with utmost integrity.
 */
package ph.edu.dlsu.lbycpei.portfolio;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/Style.css").toExternalForm());
        primaryStage.setTitle("LBYCPEI SocialNet - Profile Manager");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}