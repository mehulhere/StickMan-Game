package com.example.first_fx_project;

import javafx.animation.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.util.Duration;

import java.io.IOException;

public class GamePlayController extends SceneController{

    @FXML
    private ImageView imgDefaultCharacter;

    @FXML
    private Line stickLine1;

    @FXML
    private Line stickLine2;

    @FXML
    private Button extendStickButton;

    @FXML
    private AnchorPane movableComponents;

    @FXML
    private Button invertPlayerButton;

    @FXML
    public Rectangle platformRectangle1;

    @FXML
    private Rectangle platformRectangle2;

    @FXML
    private Rectangle platformRectangle3;

    private Platform platform1;
    private Platform platform2;
    private Platform platform3;
    @FXML
    private Stick stick1;
    @FXML
    private Stick stick2;
    private Player player;


    public Line getStickLine1() {
        return stickLine1;
    }

    public Rectangle getPlatformRectangle1() {
        return platformRectangle1;
    }

    public Rectangle getPlatformRectangle2() {
        return platformRectangle2;
    }

    public Rectangle getPlatformRectangle3() {
        return platformRectangle3;
    }


    @FXML
    public void initialize() {
        platform1 = new Platform(1); // Initialize with appropriate parameters
        platform2 = new Platform(2); // Initialize with appropriate parameters
        platform3 = new Platform(3); // Initialize with appropriate parameters
        stick1 = new Stick(); // Initialize with appropriate parameters
        stick2 = new Stick(); // Initialize with appropriate parameters
        player = new Player(); // Initialize with appropriate parameters

        // Now you can use these objects in your controller as needed
        // For example:
        platformRectangle1.setX(platform1.getPosition().getX());
        platformRectangle1.setWidth(platform1.getWidth());
        platformRectangle2.setX(platform2.getPosition().getX());
        platformRectangle2.setWidth(platform2.getWidth());
        platformRectangle3.setX(platform3.getPosition().getX());
        platformRectangle3.setWidth(platform3.getWidth());


        int stickStartX = 0;
        if (platform1.isCurrentPlatform()) {
            stickStartX = platform1.getPosition().getX() + platform1.getWidth() - 5;
        } else if (platform2.isCurrentPlatform()) {
            stickStartX = platform2.getPosition().getX() + platform2.getWidth() - 5 ;
        }
        if (platform3.isCurrentPlatform()) {
            stickStartX = platform3.getPosition().getX() + platform3.getWidth() - 5;
        }
        getStickLine1().setStartX(stickStartX);
        getStickLine1().setEndX(stickStartX);
    }

    @FXML
    void extendStick(KeyEvent event) {
        if (event.getCode() == KeyCode.SPACE) {
            Timeline timeline = new Timeline();
            timeline.getKeyFrames().clear(); // Clear existing keyframes
            timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(0.03), new KeyValue(stickLine1.endYProperty(), stickLine1.getEndY() - 10)));
            timeline.play();
        }
    }

    @FXML
    void invertPlayer(KeyEvent event) {
        if (event.getCode() == KeyCode.SPACE) {

        }
    }

    boolean checkStickCollision() {
        double stickLength = stickLine1.getStartY() - stickLine1.getEndY();
        double stickX = stickLength + stickLine1.getStartX();
        System.out.println(stickX);
        System.out.println(platformRectangle2.getX());
        System.out.println(platformRectangle2.getX() + platformRectangle2.getWidth());
        if (stickX > platformRectangle2.getX() && stickX < (platformRectangle2.getX() + platformRectangle2.getWidth())) {
            //score ++
            //check collision with hitPoint
            //Player Moves
            //Calls Scenes Change
            System.out.println("Collision!! RUN");
            changeScene();
            return true;
        }
        System.out.println("NO Collision!! DONT RUN");
        return false;
    }


    void changeScene(){
        int increment = platform2.getMidX() - 125;
        // Create a TranslateTransition for the AnchorPane
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), movableComponents);
        translateTransition.setToX(movableComponents.getTranslateX() - increment);
        translateTransition.play();
        }


    @FXML
    void rotateStick(KeyEvent event) {
        if (event.getCode() == KeyCode.SPACE){
            extendStickButton.setDisable(true);
            Rotate rotate = new Rotate();
            rotate.setPivotX(stickLine1.getStartX());
            rotate.setPivotY(stickLine1.getStartY());

            stickLine1.getTransforms().add(rotate);

            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(rotate.angleProperty(), 0)),
                    new KeyFrame(Duration.seconds(1), new KeyValue(rotate.angleProperty(), 90))
            );

            timeline.setCycleCount(1);
            timeline.setCycleCount(1);

            timeline.setOnFinished(e -> {
                checkStickCollision(); // Call the collision check after animation finishes
            });

            timeline.play();
            timeline.play();
        }
    }




}

