package com.example.first_fx_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
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
    GamePlayController gamePlayController;



    public void closePopup(ActionEvent event) throws IOException {
        System.out.println("Closing Up");
        // Access the root of the current scene
        Scene currentScene = ((Node) event.getSource()).getScene();
//        gamePlayController.enableExtendButton();
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
    public void switchToGameOverPage(Node currentNode, String score, Button extendButton) throws IOException {
        System.out.println("Game Over Page");
        try {
            extendButton.setDisable(true);
            //Also disable invert Button
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
                    currentStage.requestFocus();
                    System.out.println(1);
                    System.out.println(2);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void switchToGamePlayPage(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("gamePlayPage.fxml"));
        Parent root = fxmlLoader.load();
        gamePlayController =  fxmlLoader.getController();
        System.out.println(gamePlayController);
        if(gamePlayController==null){
            System.out.println("I AM NULL");
        }
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


    public void switchToGameOverPage(Node currentNode, String score, String tokens) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("gameOverPage.fxml"));
        Parent root = fxmlLoader.load(); // Set the loaded FXML as the root ------ error here
        Scene newScene = new Scene(root);

        GameOverController gameOverController = fxmlLoader.getController();
        gameOverController.setGameOverScore(score);
        gameOverController.setGameOverTokens(tokens);




    public void switchToPausePage(MouseEvent event) throws IOException {
        System.out.println("Paused");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("pausePage.fxml"));
            Parent overlayRoot = fxmlLoader.load();
//            gamePlayController.disableExtendButton();
//            gamePlayController.disableInvertButton();

            StackPane stackPane = new StackPane();

            Scene currentScene = ((Node) event.getSource()).getScene();

            stackPane.getChildren().addAll(
                    currentScene.getRoot(),
                    overlayRoot
            );

            overlayScene = new Scene(stackPane);

            Stage stage = (Stage) currentScene.getWindow();
            stage.setScene(overlayScene);
            overlayRoot.requestFocus();
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