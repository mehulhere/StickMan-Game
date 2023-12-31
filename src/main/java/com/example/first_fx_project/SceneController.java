package com.example.first_fx_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;
import javax.sound.sampled.*;

public class SceneController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    private Scene overlayScene;

    GameStatistics gameStatistics = GameStatistics.getInstance();


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
//            stage.setMaximized(true);
//            stage.setFullScreen(true);
        }
    }

    public void closePopupRevive(ActionEvent event) throws IOException {
        System.out.println("Revive Button Clicked");
        System.out.println(gameStatistics.getTokens());
        int finalTokens = gameStatistics.getTokens()-GameStatistics.getRevivals()-1;
        if (finalTokens >= 0) {
            gameStatistics.setTokens(finalTokens);
        }
        System.out.println(GameStatistics.getRevivals());
        System.out.println(gameStatistics.getTokens());
        // Access the root of the current scene
        Scene currentScene = ((Node) event.getSource()).getScene();
//        gamePlayController.enableExtendButton();
        if (currentScene != null && currentScene.getRoot() instanceof StackPane) {
            StackPane stackPane = (StackPane) currentScene.getRoot();
            stackPane.getChildren().remove(stackPane.getChildren().size() - 1); // Remove the topmost child (assuming it's the overlay)
//                extendButton.setDisable(false);
//                extendButton.requestFocus();
//            stage.setFullScreen(true);
        }
    }

    public void switchToMenuPage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("menuPage.fxml"));
            // Other parts of your application continue here while the audio is playing in the background

        root = fxmlLoader.load(); // Set the loaded FXML as the root
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
//        stage.setMaximized(true);
        stage.setFullScreen(true);
        stage.show();
    }
    public void switchToGameOverPage(Node currentNode, String score, String tokens) throws IOException {
        System.out.println("Game Over Page");
        try {
            //Also disable invert Button
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("gameOverPage.fxml"));
            Parent overlayRoot = fxmlLoader.load();
            GameOverController gameOverController = fxmlLoader.getController();
            System.out.println(score);
            System.out.println(tokens);
//            gameOverController.setGameOverScore(score);
//            gameOverController.setGameOverTokens(tokens);
            StackPane stackPane = new StackPane();

            Scene currentScene = currentNode.getScene(); // Fetch scene from provided Node

            if (currentScene != null) {
                stackPane.getChildren().addAll(
                        currentScene.getRoot(),
                        overlayRoot
                );

                overlayScene = new Scene(stackPane);
                overlayScene.setFill(Paint.valueOf("Black"));

                Stage currentStage = (Stage) currentScene.getWindow();
                if (currentStage != null) {
                    currentStage.setScene(overlayScene);
                    currentStage.requestFocus();
                    currentStage.setFullScreen(true);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void switchToGamePlayPage(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("gamePlayPage.fxml"));
        Parent root = fxmlLoader.load();
        root.setStyle("-fx-background-color: black;");
        double screenWidth = Screen.getPrimary().getBounds().getWidth();
        double screenHeight = Screen.getPrimary().getBounds().getHeight();


        scene = new Scene(root,1440, 810);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        double centerX = (screenWidth - scene.getWidth()) / 2;
        double centerY = (screenHeight - scene.getHeight()) / 2;
        root.setLayoutX(centerX);
        root.setLayoutY(centerY);
        scene.setFill(Paint.valueOf("Black"));

        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setFullScreen(true);
        stage.show();
    }

    public void switchToCharacterPage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("characterPage.fxml"));
        root = fxmlLoader.load(); // Set the loaded FXML as the root
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.centerOnScreen();
        scene = new Scene(root);
        scene.setFill(Paint.valueOf("Black"));
        stage.setScene(scene);
        double screenWidth = Screen.getPrimary().getBounds().getWidth();
        double screenHeight = Screen.getPrimary().getBounds().getHeight();
        double centerX = (screenWidth - scene.getWidth()) / 2;
        double centerY = (screenHeight - scene.getHeight()) / 2;
        root.setLayoutX(centerX);
        root.setLayoutY(centerY);
        root.setStyle("-fx-background-color: black;");
//        stage.setMaximized(true);
        stage.setFullScreen(true);
        stage.show();
    }

    public void switchToThemesPage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("themesPage.fxml"));
        root = fxmlLoader.load(); // Set the loaded FXML as the root
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.setFill(Paint.valueOf("Black"));
        stage.setScene(scene);
        double screenWidth = Screen.getPrimary().getBounds().getWidth();
        double screenHeight = Screen.getPrimary().getBounds().getHeight();
        double centerX = (screenWidth - scene.getWidth()) / 2;
        double centerY = (screenHeight - scene.getHeight()) / 2;
        root.setLayoutX(centerX);
        root.setLayoutY(centerY);
        root.setStyle("-fx-background-color: black;");
        stage.setMaximized(true);
        stage.setFullScreen(true);
        stage.show();
    }

    public void switchToPausePage(MouseEvent event) throws IOException {
        System.out.println("Paused");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("pausePage.fxml"));
            Parent overlayRoot = fxmlLoader.load();
//            gamePlayController.disableExtendButton();
//            gamePlayController.disableInvertButton();
//            overlayRoot.setStyle("-fx-background-color: black;");
            scene = new Scene(overlayRoot,1440, 810);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setMaximized(true);
            stage.setFullScreen(true);
            double screenWidth = Screen.getPrimary().getBounds().getWidth();
            double screenHeight = Screen.getPrimary().getBounds().getHeight();
            double centerX = (screenWidth - scene.getWidth()) / 2;
            double centerY = (screenHeight - scene.getHeight()) / 2;
            overlayRoot.setLayoutX(centerX);
            overlayRoot.setLayoutY(centerY);
//            scene.setFill(Paint.valueOf("Black"));
            StackPane stackPane = new StackPane();
            stackPane.setStyle("-fx-background-color: black;");
            Scene currentScene = ((Node) event.getSource()).getScene();

//            currentScene.getRoot().setStyle("-fx-background-color: black;");
//            currentScene.setFill(Paint.valueOf("Black"));
            stackPane.getChildren().addAll(
                    currentScene.getRoot(),
                    overlayRoot
            );

            overlayScene = new Scene(stackPane);
//            overlayScene.setFill(Paint.valueOf("Black"));
            Stage stage = (Stage) currentScene.getWindow();
            stage.setScene(overlayScene);
            overlayRoot.requestFocus();
            stage.setMaximized(true);
            stage.setFullScreen(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void serialize() throws IOException {
        GameStatistics gameStatistics = GameStatistics.getInstance();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(
                    new FileOutputStream("GameProgress.txt"));
            out.writeObject(gameStatistics);
        }
            finally{
            if (out != null) {
                out.close();
            }
            }
        }

    public void exitGame(ActionEvent event) throws IOException {
        serialize();
        System.exit(0);
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Parent getRoot() {
        return root;
    }

    public void setRoot(Parent root) {
        this.root = root;
    }

    public Scene getOverlayScene() {
        return overlayScene;
    }

    public void setOverlayScene(Scene overlayScene) {
        this.overlayScene = overlayScene;
    }

    public GameStatistics getGameStatistics() {
        return gameStatistics;
    }

    public void setGameStatistics(GameStatistics gameStatistics) {
        this.gameStatistics = gameStatistics;
    }
}