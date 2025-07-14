/**
 * Course: LBYCPEI - OBJECT-ORIENTED PROGRAMMING LABORATORY
 * ASSIGNMENT: Module 7: Graphic User Interface (GUI)
 *
 * FILENAME: FeaturedProjectsController .java
 * STUDENT NAME: JOHN LESTER R. BLAS
 * STUDENT ID: 12475351
 * SECTION: XXE1
 * DATE: 07/14/2025
 *
 * DESCRIPTION: Controller for the featured Projects tab of the application.
 *
 * HONOR CODE: I have answered this activity with utmost integrity.
 */
package ph.edu.dlsu.lbycpei.portfolio.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;

import java.awt.Desktop;
import java.net.URI;

public class FeaturedProjectsController {

    @FXML private Hyperlink calcButton;
    @FXML private Hyperlink hngButton;

    @FXML
    public void openFormula() {
        openLink("https://github.com/JLRB05/CalculatorApp/tree/main/EngMathApp");
    }

    @FXML
    public void openHangman() {
        openLink("https://github.com/JLRB05/HangmanApp");
    }

    private void openLink(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
