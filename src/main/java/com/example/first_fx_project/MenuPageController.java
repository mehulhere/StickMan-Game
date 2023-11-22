package com.example.first_fx_project;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MenuPageController {

    @FXML
    private Button startGameButton;

    @FXML
    private Button instructionsButton;

    @FXML
    private Button exitButton;

    @FXML
    private void startGame() {
        // Action when Start Game button is clicked
        System.out.println("Start Game button clicked");
        // Add logic to start the game
    }

    @FXML
    private void showInstructions() {
        // Action when Instructions button is clicked
        System.out.println("Instructions button clicked");
        // Add logic to display game instructions
    }

    @FXML
    private void exitGame() {
        // Action when Exit button is clicked
        System.out.println("Exit button clicked");
        // Add logic to exit the game
    }
}

