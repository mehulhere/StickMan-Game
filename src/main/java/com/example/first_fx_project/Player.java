package com.example.first_fx_project;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.util.Duration;

import java.io.IOException;

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

    public void move(double increment, Platform platform, Line stickLine, boolean alive) {
        if (alive) {
            gamePlayController.setHitPointPosition(gamePlayController.getHitPointFront(),
                    gamePlayController.getInvisiblePlatform().getMidX() - (double) HitPoint.getWidth() /2 + gamePlayController.getTotalIncrement());
            Timeline timeline = new Timeline();
            double seconds =  increment * 0.002;
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(seconds), event -> {
                    if (image.getScaleY() == -1) { //Collision with Pillar Logic
                        System.out.println("GAME OVER");
                        fall();
                    } else {
                        Timeline secondaryTimeline = new Timeline();
                        KeyFrame keyFrame2 = new KeyFrame(Duration.seconds(0.001 * platform.getWidth()), event2 ->{
                            gamePlayController.changeScene();
                        }, new KeyValue(image.translateXProperty(), image.getTranslateX() + platform.getWidth()/2 ));
                        secondaryTimeline.getKeyFrames().add(keyFrame2);
                        secondaryTimeline.play();
                        gamePlayController.updateScore();
                    }
            },new KeyValue(image.translateXProperty(), image.getTranslateX() + increment -platform.getWidth()/2));
            timeline.getKeyFrames().add(keyFrame);
            timeline.play();
        }
        else{
            TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), image);
            System.out.println(image.getTranslateX());
            System.out.println(platform.getMidX());
            translateTransition.setToX(increment);
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

        translateTransition.setOnFinished(event -> {
            try {
                gamePlayController.switchToGameOverPage();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
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

