package com.example.first_fx_project;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;


import java.io.IOException;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;


public class Player {

    Stick stick;
    GameMechanics gameMechanics;
    static private int length;
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

    public void move(double playerFinalX, Platform platformCurrent, Platform platformTarget, Token token, boolean alive, double stickEndX) {
        double rate = 0.002;
        moveToPlatform(platformCurrent,platformTarget, playerFinalX, rate, token, alive, stickEndX);
    }

    AtomicBoolean collectedToken = new AtomicBoolean(false);

    public void moveToPlatform(Platform platformCurrent,Platform platformTarget, double playerFinalX, double transitionRate, Token token, boolean alive, double stickEndX){
//        transitionRate = 0.05;
        GamePlayController.getHitPointFront().isVisible(false);
        double platformWidth = platformTarget.getPlatformRectangle().getWidth();
        double playerCrashX;
        if(alive) {
            playerCrashX = (playerFinalX - platformWidth);
        }
        else{
            playerCrashX = stickEndX;
        }
        double playerStartX = image.getX();
        double transitionDistance = playerFinalX - playerStartX;
        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        AtomicBoolean tokenCollected = new AtomicBoolean(false);
        AtomicBoolean transitionRunning= new AtomicBoolean(true);
        gamePlayController.disableInvertButton();
        ExecutorService collisionThread = Executors.newSingleThreadExecutor();
        ExecutorService tokenThread = Executors.newSingleThreadExecutor();
        ImageView imgToken = token.getImgToken();

        tokenThread.execute(()->{
            double playerX = image.getTranslateX() + image.getX();
            double tokenEndX = token.getImgToken().getX()+token.getImgToken().getFitWidth();
            boolean playerCrossedToken = tokenEndX < playerX;
            while (transitionRunning.get() && !playerCrossedToken ) {
                playerX = image.getTranslateX() + image.getX();
//                System.out.println(playerCrossedToken);
//                System.out.println("2: "+playerX);
                playerCrossedToken = tokenEndX < playerX;
                if (Math.abs(imgToken.getX() - playerX) < image.getFitWidth() && isInverted) {
                    tokenCollected.set(true);
                    imgToken.setOpacity(0);

                    javafx.application.Platform.runLater(()-> {
                        gamePlayController.updateTokenCount();
                    });
                    break;
                }
                try {
                    Thread.sleep(5); // Adjust sleep time as needed
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } // Player Crossed Token
                }
        if(tokenCollected.get()) {
            GameStatistics.setTokens(GameStatistics.getTokens() + 1);
            tokenCollected.set(false);
        }
        });

        collisionThread.execute(() -> {
            double playerX = image.getTranslateX() + image.getX();
            boolean playerCrossedStick = playerCrashX - playerX > 0;
            while (transitionRunning.get() && playerCrossedStick ) {
//                System.out.println("1:"+playerX);
                playerX = image.getTranslateX() + image.getX();
//                System.out.println(playerX);
//                System.out.println(playerCrossedPlatformX);
                playerCrossedStick = playerCrashX - playerX > 0;
                try {
                    Thread.sleep(5); // Adjust sleep time as needed
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } // Player Crossed Stick
//                System.out.println(playerCrashX);
//                System.out.println(playerX);
//                System.out.println(isInverted);
            }
            gamePlayController.stopInversion();
            if(isInverted){
                System.out.println("Collision with platform");
                timeline.stop();
                gamePlayController.playerFall();
                revival();//Blocking Command
                System.out.println("Player Moves forward");
                timeline.play();
                //Proper Logic
                //On Revive
//                timeline.play();
            }
            if(!alive){
                System.out.println("Stick Small");
                timeline.stop();
                gamePlayController.rotateStick(1);
                gamePlayController.playerFall();
                revival();
                timeline.play();
            }
            javafx.application.Platform.runLater(()-> {
                gamePlayController.updateTokenCount();
            });
        });

        System.out.println("Hee");

        // Adding KeyFrame to the timeline
        Duration duration = Duration.seconds(transitionRate*transitionDistance); // Duration of animation (1 second)
        KeyFrame keyFrame = new KeyFrame(duration, event -> {
            gamePlayController.updateScore();
            gamePlayController.checkHighScore();
            gamePlayController.changeScene();
            transitionRunning.set(false);
        }, new KeyValue(image.xProperty(), playerFinalX));


        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
        Future<?> future = tokenThread.submit(() -> {
            if(tokenCollected.get()){
                GameStatistics.setTokens(GameStatistics.getTokens()+1);
                gamePlayController.updateTokenCount();
                tokenCollected.set(false);
            }
        });

//        try {
//            // Wait for the task to complete
//            future.get(); // This blocks until the task is done
//        } catch (InterruptedException | ExecutionException e) {
//            e.printStackTrace();
//        }
//
//        tokenThread.shutdown();


    }

    public void revival(){
        System.out.println("Revival Called");
        System.out.println(GameStatistics.getTokens());
        ExecutorService executor2 = Executors.newSingleThreadExecutor();
        executor2.execute(() -> {
            int tokensAtStart = GameStatistics.getTokens();
            boolean tokensUsed = false;
            while (!tokensUsed) {
                try {
                    int tokensNow = GameStatistics.getTokens();
//                    System.out.println(tokensNow);
                    tokensUsed = tokensAtStart>tokensNow;
//                    System.out.println(tokensNow);
                    Thread.sleep(50);
                    //If thread runs for more than 10s, call dump. Set revival 0
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } // Player Crossed Stick
            }
            System.out.println("I am revived");
            GameStatistics.setRevivals(GameStatistics.getRevivals()+1);
            fallInverse();
        });
        Future<?> future = executor2.submit(() -> {
            // Your task logic here
        });

        try {
            // Wait for the task to complete

// Schedule a task to interrupt the thread after a delay of 5 seconds
            System.out.println("Waiting for Revival return");
            future.get(); // This blocks until the task is done
            System.out.println("Revival returned");
            if (isInverted) {
                invert();
                gamePlayController.flipInverted();
//                image.setY(image.getY() - image.getFitHeight());
            }
            Thread.sleep(500);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        executor2.shutdown();
        System.out.println("Revive Button Thread Closed");
    }

    public void fall(){

        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5), image);
        translateTransition.setToY(image.getTranslateY() + 350);
        translateTransition.play();
        System.out.println("Falling");

        translateTransition.setOnFinished(event -> {
            try {
                //New Thread
                //this inside thread for 10s
                gamePlayController.switchToGameOverPage();
                System.out.println("Calling Revival");
                System.out.println("OGADJA");

                //Input
                // Or ask GPT
                //Back to This Instance
                //Inverse Fall Animation
                // Fall finishes goes to transition
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println(" I am here");
    }

    public void fallInverse(){
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5), image);
        translateTransition.setToY(image.getTranslateY() - 350);
        translateTransition.play();
    }

    public void invert() {
        image.getTransforms().clear();

        // Apply the transformation to the ImageView
        if(image.getScaleY() == -1){
            image.setY(image.getY()-image.getFitHeight());
            image.setScaleY(1);
        }
        else{
            image.setY(image.getY()+image.getFitHeight());
            image.setScaleY(-1);
        }
    }

    // Getters and setters can be added for the private fields
}

