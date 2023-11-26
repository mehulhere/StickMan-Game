package com.example.first_fx_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToPausePage(MouseEvent event) throws IOException {
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

    public void switchToGamePlayPage(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("gamePlayPage.fxml"));
        Parent root = fxmlLoader.load();
        Platform platform1 = new Platform(1);
        Platform platform2 = new Platform(2);
        Platform platform3 = new Platform(3);
        Stick stick1 = new Stick();
        Stick stick2 = new Stick();
        Player player = new Player();
        GamePlayController gamePlayController = fxmlLoader.getController();

        DefaultCharacter defaultCharacter = new DefaultCharacter(gamePlayController.getImgDefaultCharacter());

        gamePlayController.getPlatformRectangle1().setX(platform1.getPosition().getX());
        gamePlayController.getPlatformRectangle1().setWidth(platform1.getWidth());
        gamePlayController.getPlatformRectangle2().setX(platform2.getPosition().getX());
        gamePlayController.getPlatformRectangle2().setWidth(platform2.getWidth());
        gamePlayController.getPlatformRectangle3().setX(platform3.getPosition().getX());
        gamePlayController.getPlatformRectangle3().setWidth(platform3.getWidth());


        int stickStartX = 0;
        if (platform1.isCurrentPlatform()) {
            stickStartX = platform1.getPosition().getX() + platform1.getWidth() - 5;
        } else if (platform2.isCurrentPlatform()) {
            stickStartX = platform2.getPosition().getX() + platform2.getWidth() - 5 ;
        }
        if (platform3.isCurrentPlatform()) {
            stickStartX = platform3.getPosition().getX() + platform3.getWidth() - 5;
        }
        gamePlayController.getStick().setStartX(stickStartX);
        gamePlayController.getStick().setEndX(stickStartX);
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

    public void exitGame(ActionEvent event) throws IOException {
        System.exit(0);
    }


    public void directionButtonClick(ActionEvent actionEvent) {
    }
}
