package com.example.first_fx_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    private Scene overlayScene;


    public void closePopup(ActionEvent event) {
            // Access the root of the current scene
            Scene currentScene = ((Node) event.getSource()).getScene();
            if (currentScene != null && currentScene.getRoot() instanceof StackPane) {
                StackPane stackPane = (StackPane) currentScene.getRoot();
                stackPane.getChildren().remove(stackPane.getChildren().size() - 1); // Remove the topmost child (assuming it's the overlay)
//                extendButton.setDisable(false);
//                extendButton.requestFocus();
            }

    }
    public void switchToMenuPage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("menuPage.fxml"));
        root = fxmlLoader.load(); // Set the loaded FXML as the root
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToGamePlayPage(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("gamePlayPage.fxml"));
        Parent root = fxmlLoader.load();

    // Set the loaded FXML as the root
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToCharacterPage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("characterPage.fxml"));
        root = fxmlLoader.load(); // Set the loaded FXML as the root
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToThemesPage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("themesPage.fxml"));
        root = fxmlLoader.load(); // Set the loaded FXML as the root
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToGameOverPage(Node currentNode, String score, Button extendButton) throws IOException {
        System.out.println("Game Over Page");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("gameOverPage.fxml"));
            Parent overlayRoot = fxmlLoader.load();

            StackPane stackPane = new StackPane();

            Scene currentScene = currentNode.getScene(); // Fetch scene from provided Node

            if (currentScene != null) {
                stackPane.getChildren().addAll(
                        currentScene.getRoot(),
                        overlayRoot
                );

                overlayScene = new Scene(stackPane);

                Stage currentStage = (Stage) currentScene.getWindow();
                if (currentStage != null) {
                    currentStage.setScene(overlayScene);
                    System.out.println(1);
                    System.out.println(2);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToPausePage(MouseEvent event) throws IOException {
        System.out.println("Paused");
        try {
//            extendButton.setDisable(true);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("pausePage.fxml"));
            Parent overlayRoot = fxmlLoader.load();

            StackPane stackPane = new StackPane();

            Scene currentScene = ((Node) event.getSource()).getScene();

            stackPane.getChildren().addAll(
                    currentScene.getRoot(),
                    overlayRoot
            );

            overlayScene = new Scene(stackPane);

            Stage stage = (Stage) currentScene.getWindow();

            stage.setScene(overlayScene);
            System.out.println(1);
            System.out.println(2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    public void exitGame(ActionEvent event) throws IOException {
        System.exit(0);
    }


    public void directionButtonClick(ActionEvent actionEvent) {
    }
}
