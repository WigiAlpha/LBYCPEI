/**
 * Course: LBYCPEI - OBJECT-ORIENTED PROGRAMMING LABORATORY
 * ASSIGNMENT: Module 7: Graphic User Interface (GUI)
 *
 * FILENAME: PortfolioController.java
 * STUDENT NAME: JOHN LESTER R. BLAS
 * STUDENT ID: 12475351
 * SECTION: XXE1
 * DATE: 07/14/2025
 *
 * DESCRIPTION: Main Application controller to toggle between scenes.
 *
 * HONOR CODE: I have answered this activity with utmost integrity.
 */
package ph.edu.dlsu.lbycpei.portfolio.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class PortfolioController {

    @FXML
    private StackPane contentArea;


    @FXML
    public void initialize() {
        loadView("/fxml/About.fxml");
    }

    public void loadAbout() {
        loadView("/fxml/About.fxml");
    }

    public void loadSkills() {
        loadView("/fxml/Skills.fxml");
    }

    public void loadProjects() {
        loadView("/fxml/FeaturedProjs.fxml");
    }

    public void loadContact() {
        loadView("/fxml/Contact.fxml");
    }

    private void loadView(String fxml) {
        try {
            System.out.println("Loading FXML: " + getClass().getResource(fxml)); // debug line
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Node node = loader.load();
            contentArea.getChildren().setAll(node);
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }
}
