package com.example.first_fx_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToPausePage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("pausePage.fxml"));
        root = fxmlLoader.load(); // Set the loaded FXML as the root
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToMenuPage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("menuPage.fxml"));
        root = fxmlLoader.load(); // Set the loaded FXML as the root
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToGamePlayPage(ActionEvent event) throws IOException {
        GamePlayController stickHeroGame = new GamePlayController(); // Create an instance of StickHeroGame


        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stickHeroGame.start(stage); // Get the content of StickHeroGame
    }


    public void switchToCharacterPage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("gamePlayPage.fxml"));
        Parent root = fxmlLoader.load();

        GamePlayController controller = fxmlLoader.getController(); // Get the controller instance

        stage.setTitle("Game Play Page");
        stage.setScene(new Scene(root, 800, 600));
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

    public void exitGame(ActionEvent event) throws IOException {
        System.exit(0);
    }
}
