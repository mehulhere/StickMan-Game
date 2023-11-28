package com.example.first_fx_project;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
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

    public void move(double playerFinalX, Platform platform, Token token, boolean alive) {
        double rate = 0.002;
        if (alive) {
            moveToPlatformStart(platform, playerFinalX, rate);
        }
        else{
            moveToStickEnd(playerFinalX);
        }
    }

    public void moveToPlatformStart(Platform platform, double playerFinalX, double transitionRate){
        double platformMidLength = (double) platform.getWidth() / 2;
        double playerNewX =  (playerFinalX - platformMidLength - image.getFitWidth()) ;
        Timeline timeline = new Timeline();
        double transitionDistance = playerNewX - image.getX();
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(transitionDistance * transitionRate), event -> { //Reaches Platform Start
            gamePlayController.stopInversion();  //Disable Inversion Button
            if (image.getScaleY() == -1) { // Check If Inverted at this point
                gamePlayController.playerFall();
            } else { // Not Inverted
                moveToPlatformMid(platform, transitionRate);
            }
        }, new KeyValue(image.xProperty(), playerNewX)); //Moves Till Pillar Start
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }

    public void moveToPlatformMid(Platform platform, double transitionRate){
        double platformMidLength = (double) platform.getWidth() / 2;
        Timeline secondaryTimeline = new Timeline();
        double TransitionDistance =  platformMidLength +image.getFitWidth();
        KeyFrame keyFrame2 = new KeyFrame(Duration.seconds(transitionRate * TransitionDistance), event2 ->{
            gamePlayController.changeScene();
        }, new KeyValue(image.xProperty(), image.getX() + TransitionDistance));
        secondaryTimeline.getKeyFrames().add(keyFrame2);
        secondaryTimeline.play();
    }

    public void moveToStickEnd(double playerFinalX){
        System.out.println("Player Falls");
        System.out.println("PlayerFinalX: "+ playerFinalX);
        Timeline timeline = new Timeline();
        double durationInSeconds = 0.5;
        Duration duration = Duration.seconds(durationInSeconds);
        KeyFrame keyFrame = new KeyFrame(
                duration,
                event -> {
                    gamePlayController.rotateStick(1);
                    gamePlayController.playerFall();
                },
                new KeyValue(image.xProperty(), playerFinalX)
        );
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
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

