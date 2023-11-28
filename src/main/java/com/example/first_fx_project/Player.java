package com.example.first_fx_project;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.util.Duration;


import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;


public class Player {

    Stick stick;
    GameMechanics gameMechanics;
    static private int length;
    static private int width;
    static private int speed;
    public boolean isInverted = false;
    private boolean meterIsFull;
    private Position position;
    GamePlayController gamePlayController;
    ImageView image;

    public Player(GamePlayController gamePlayController, ImageView image) {
        this.gamePlayController = gamePlayController;
        this.image = image;
    }

    public void move(double playerFinalX, Platform platformCurrent, Platform platformTarget, Token token, boolean alive, Button invertButton) {
        double rate = 0.002;
        if (alive) {
            moveToPlatform(platformCurrent,platformTarget, playerFinalX, rate, token, invertButton);
        }
        else{
            moveToStickEnd(playerFinalX);
        }
    }


  public void moveToPlatform(Platform platformCurrent,Platform platformTarget, double playerFinalX, double transitionRate, Token token, Button invertButton){
        gamePlayController.getHitPointFront().isVisible(false);
        double platformMidLength = (double) platformTarget.getPlatformRectangle().getWidth() / 2;
        double playerCrashX =  (playerFinalX - platformMidLength - image.getFitWidth());
        double playerStartX = image.getX();
        double transitionDistance = playerFinalX - playerStartX;
        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        AtomicBoolean transitionRunning= new AtomicBoolean(true);
        gamePlayController.disableInvertButton();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            ImageView imgToken = token.getImgToken();
            double playerX = image.getTranslateX() + image.getX();
            boolean playerCrossedStick = playerCrashX - playerX > 0;
            double platformWidth = platformCurrent.getPlatformRectangle().getWidth();
            double playerCrossedPlatformX = platformCurrent.getPlatformRectangle().getX()+ platformWidth + image.getFitWidth();
            while (transitionRunning.get() && playerCrossedStick) {
                playerX = image.getTranslateX() + image.getX();
//                System.out.println(playerX);
//                System.out.println(playerCrossedPlatformX);
                playerCrossedStick = playerCrashX - playerX > 0;
//                System.out.println(playerCrashX);
//                System.out.println(playerX);
//                System.out.println(isInverted);
                if(Math.abs(imgToken.getX() - playerX) < image.getFitWidth() && isInverted){
                    //Tokens ++
                    imgToken.setOpacity(0);
                }
                try {
                    Thread.sleep(10); // Adjust sleep time as needed
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } // Player Crossed Stick
                }
            gamePlayController.stopInversion();
            if(isInverted){
                timeline.stop();
                gamePlayController.playerFall();
            }
        });
        // Adding KeyFrame to the timeline
        Duration duration = Duration.seconds(transitionRate*transitionDistance); // Duration of animation (1 second)
        KeyFrame keyFrame = new KeyFrame(duration, event -> {
            gamePlayController.updateScore();
            gamePlayController.changeScene();
            transitionRunning.set(false);
        }, new KeyValue(image.xProperty(), playerFinalX));

        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
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

