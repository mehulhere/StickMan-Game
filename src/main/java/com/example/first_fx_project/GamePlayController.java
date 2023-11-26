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
    private GameMechanics gameMechanics;


    public Line getStickLine1() {
        return stickLine1;
    }

    public ImageView getImgDefaultCharacter() {
        return imgDefaultCharacter;
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

    public DefaultCharacter defaultCharacter;

    @FXML
    public void initialize() {
        platform1 = new Platform(1); // Initialize with appropriate parameters
        platform2 = new Platform(2); // Initialize with appropriate parameters
        platform3 = new Platform(3); // Initialize with appropriate parameters
        stick1 = new Stick(); // Initialize with appropriate parameters
        stick2 = new Stick(); // Initialize with appropriate parameters
        defaultCharacter = new DefaultCharacter(this, imgDefaultCharacter);
        gameMechanics = new GameMechanics(this);

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
    void stopExtendStick(KeyEvent event) {
        if (event.getCode() == KeyCode.SPACE) {
            extendStickButton.setDisable(true);
            invertPlayerButton.setDisable(false);
            rotateStick(0);
        }
    }

    @FXML
    void invertPlayer(KeyEvent event) {
        if (event.getCode() == KeyCode.SPACE) {
            defaultCharacter.invert();
        }
    }

    boolean checkStickCollision() {
        return gameMechanics.checkCollision(stickLine1, platformRectangle2, platform2);
    }

    void playerMove(double increment){
        defaultCharacter.move(increment, platform2, stickLine1);
    }

    void playerFall(){
        defaultCharacter.fall();
    }

    void changeScene(){
        gameMechanics.changeScene(platform2, movableComponents);
    }

    void rotateStick(int num) {
        Rotate rotate = new Rotate();
        rotate.setPivotX(stickLine1.getStartX());
        rotate.setPivotY(stickLine1.getStartY());

        stickLine1.getTransforms().add(rotate);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(rotate.angleProperty(), 0)),
                new KeyFrame(Duration.seconds(1), new KeyValue(rotate.angleProperty(), 90))
        );

        timeline.setCycleCount(1);

        if(num == 0){
            timeline.setOnFinished(e -> {
                checkStickCollision(); // Call the collision check after animation finishes
            });
        }

        timeline.play();
    }

}

