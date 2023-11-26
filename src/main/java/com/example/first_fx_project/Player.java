package com.example.first_fx_project;

import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class Player {

    Stick stick;
    GameMechanics gameMechanics;
    static private int length;
    static private int width;
    static private int speed;
    private boolean isInverted;
    private boolean meterIsFull;
    private Position position;
    GamePlayController gamePlayController;
    ImageView image;
    boolean isFlipped = false;

    public Player(GamePlayController gamePlayController, ImageView image) {
        this.gamePlayController = gamePlayController;
        this.image = image;
    }

    public void move(double increment, Platform platform2, Line stickLine1) {
        if(increment == platform2.getMidX() - 125){
            // Create a TranslateTransition for the AnchorPane
            TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), image);
            translateTransition.setToX(image.getTranslateX() + increment);
            translateTransition.play();

            translateTransition.setOnFinished(event -> {
                gamePlayController.changeScene();
            });
        }

        else{

            double increment2  = increment + (stickLine1.getStartX() - image.getX() - image.getFitWidth());
            TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), image);
            translateTransition.setToX(image.getTranslateX() + increment2);
            translateTransition.play();

            translateTransition.setOnFinished(event -> {
                gamePlayController.rotateStick(1);
                gamePlayController.playerFall();
            });
        }
    }

    public void fall(){
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5), image);
        translateTransition.setToY(image.getTranslateY() + 150);
        translateTransition.play();
    }

    public void invert() {
        image.getTransforms().clear();

        // Apply the transformation to the ImageView
        if(image.getScaleY() == -1){
            image.setTranslateY(0);
            image.setScaleY(1);
        }
        else{
            image.setTranslateY(image.getFitHeight());
            image.setScaleY(-1);
        }
    }

    // Getters and setters can be added for the private fields
}

