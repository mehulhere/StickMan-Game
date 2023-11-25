package com.example.first_fx_project;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
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
    private Line stick;

    @FXML
    private Button extendStickButton;

    @FXML
    private Button invertPlayerButton;

    @FXML
    private Rectangle platformRectangle1;

    @FXML
    private Rectangle platformRectangle2;

    @FXML
    private Rectangle platformRectangle3;


    public Line getStick() {
        return stick;
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
    void extendStick(KeyEvent event) {
        if (event.getCode() == KeyCode.SPACE) {
            Timeline timeline = new Timeline();
            timeline.getKeyFrames().clear(); // Clear existing keyframes
            timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(0.03), new KeyValue(stick.endYProperty(), stick.getEndY() - 10)));
            timeline.play();
        }
    }

    @FXML
    void invertPlayer(KeyEvent event) {
        if (event.getCode() == KeyCode.SPACE) {

        }
    }


    @FXML
    void rotateStick(KeyEvent event) {
        if (event.getCode() == KeyCode.SPACE){
            extendStickButton.setDisable(true);
            Rotate rotate = new Rotate();
            rotate.setPivotX(stick.getStartX());
            rotate.setPivotY(stick.getStartY());

            stick.getTransforms().add(rotate);

            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(rotate.angleProperty(), 0)),
                    new KeyFrame(Duration.seconds(1), new KeyValue(rotate.angleProperty(), 90))
            );

            timeline.setCycleCount(1);
            timeline.play();
        }
    }



}

