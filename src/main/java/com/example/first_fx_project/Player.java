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


public abstract class Player {

    Stick stick;
    static private int length;
    static private int speed;
    public boolean isInverted = false;
    private boolean meterIsFull;
    private Position position;
    GamePlayController gamePlayController;

    private AtomicBoolean tokenCollected = new AtomicBoolean(false);
    private AtomicBoolean transitionRunning;
    ImageView image;

    GameStatistics gameStatistics = GameStatistics.getInstance();

    public Player(GamePlayController gamePlayController, ImageView image) {
        this.gamePlayController = gamePlayController;
        this.image = image;
    }

    public void move(double playerFinalX, Platform platformCurrent, Platform platformTarget, Token token, boolean alive, double stickEndX) {
        double rate = 0.0016;
        moveToPlatform(platformCurrent,platformTarget, playerFinalX, rate, token, alive, stickEndX);
    }


    public void animationWaitingThread(ImageView image, AtomicBoolean transitionRunning, double playerFallX, Timeline timeline, double transitionRate, double targetPlatformEndX, double stickEndX) {
        Thread waitingThread = new Thread(() -> {
            double playerX = image.getTranslateX() + image.getX();
            boolean playerCrossedStick = playerFallX - playerX >-5;

            while (transitionRunning.get() && playerCrossedStick) {
                playerX = image.getTranslateX() + image.getX();
                playerCrossedStick = playerFallX - playerX > 0;
//
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            gamePlayController.rotateStick(1);
            gamePlayController.playerFall();
            revival();
            double remainingDistance = Math.abs(targetPlatformEndX - stickEndX);
            System.out.println(remainingDistance);
            System.out.println(remainingDistance);
            Timeline timeline1 = playerMovementAnimationTimeline(transitionRate, remainingDistance, targetPlatformEndX, true);
        });
//        timeline.stop();
        waitingThread.start();
    }

    public void AnimationThread(ImageView image, AtomicBoolean transitionRunning, double playerFallX, GamePlayController gamePlayController, Timeline timeline, boolean alive) {
        Thread collisionThread = new Thread(() -> {
            double playerX = image.getTranslateX() + image.getX();
            boolean playerCrossedStick = playerFallX - playerX > 0;

            while (transitionRunning.get() && playerCrossedStick) {
                playerX = image.getTranslateX() + image.getX();
                playerCrossedStick = playerFallX - playerX > 0;
//
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            gamePlayController.disableInvertButton();

            if (isInverted) {
                System.out.println("Collision with platform");
                timeline.stop();
                gamePlayController.playerFall();
                revival(); // Blocking Command
                System.out.println("Player Moves forward");
                timeline.play();
            }
//

            javafx.application.Platform.runLater(() -> {
                gamePlayController.updateTokenCount();
            });
//            timeline.play();
        });
        collisionThread.start();
    }


    public void checkTokenCollisionThread(ImageView image, Token token, AtomicBoolean transitionRunning, AtomicBoolean tokenCollected, GamePlayController gamePlayController) {
        Thread tokenThread = new Thread(() -> {
            double playerX = image.getTranslateX() + image.getX();
            double tokenEndX = token.getImgToken().getX() + token.getImgToken().getFitWidth();
            boolean playerCrossedToken = tokenEndX < playerX;

            while (transitionRunning.get() && !playerCrossedToken) {
                playerX = image.getTranslateX() + image.getX();
                playerCrossedToken = tokenEndX < playerX;

                if (Math.abs(token.getImgToken().getX() - playerX) < image.getFitWidth() && isInverted) {
                    tokenCollected.set(true);
                    token.getImgToken().setOpacity(0);

                    javafx.application.Platform.runLater(() -> {
                        gamePlayController.updateTokenCount();
                    });
                    break;
                }

                try {
                    Thread.sleep(5); //
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (tokenCollected.get()) {
                gameStatistics.setTokens(gameStatistics.getTokens() + 1);
                tokenCollected.set(false);
            }
        });

        tokenThread.start();
    }

    public Timeline playerMovementAnimationTimeline(double transitionRate, double transitionDistance, double playerFinalX, boolean changeSceneNeeded) {
        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        tokenCollected = new AtomicBoolean(false);
        transitionRunning = new AtomicBoolean(true);
        System.out.println(transitionRate*transitionDistance);
        Duration duration = Duration.seconds(transitionRate*transitionDistance); // Duration of animation (1 second)
        KeyFrame keyFrame = new KeyFrame(duration, event -> {
            gamePlayController.updateScore();
            gamePlayController.checkHighScore();
            transitionRunning.set(false);
            if(changeSceneNeeded){
                System.out.println("Changing SCENE");
                gamePlayController.changeScene();
            }
        }, new KeyValue(image.xProperty(), playerFinalX));


        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
        System.out.println("I am running");
        // Perform any further operations needed within this function

        return timeline;
    }


    AtomicBoolean collectedToken = new AtomicBoolean(false);

    public void moveToPlatform(Platform platformCurrent,Platform platformTarget, double playerFinalX, double transitionRate, Token token, boolean alive, double stickEndX){
//        transitionRate = 0.05;

        double platformWidth = platformTarget.getPlatformRectangle().getWidth();
        double playerFallX;
        int aestheticMargin=5;
        double targetPlatformEndX = platformTarget.getPlatformRectangle().getX() + platformWidth - image.getFitHeight() - aestheticMargin;
        GamePlayController.getHitPointFront().isVisible(false); //Front hitpoint is disabled
        double playerStartX = image.getX();
        System.out.println(targetPlatformEndX);
        System.out.println(stickEndX);
        System.out.println("+");
        if(alive){
            //Player moves to next Platform
            gamePlayController.enableInvertButton();
            double transitionDistance = playerFinalX - playerStartX;
            Timeline timeline = playerMovementAnimationTimeline(transitionRate, transitionDistance, playerFinalX, true);
            checkTokenCollisionThread(image, token, transitionRunning, tokenCollected, gamePlayController);
            playerFallX = (playerFinalX - platformWidth);
            AnimationThread(image, transitionRunning, playerFallX, gamePlayController, timeline, alive);

        }

        else{
            //Player Falls, after the target platform
            playerFallX = stickEndX;
            double transitionDistance = stickEndX - playerStartX;
            Timeline timeline = playerMovementAnimationTimeline(transitionRate, transitionDistance, stickEndX,false);
//            AnimationThread(image, transitionRunning, playerFallX, gamePlayController, timeline, alive);
            animationWaitingThread(image, transitionRunning, playerFallX, timeline, transitionRate, targetPlatformEndX, stickEndX);
            System.out.println("TargetPlatformENDZ: "+targetPlatformEndX);

        }


    }

    public void revival(){
        System.out.println("Revival Called");
        System.out.println(gameStatistics.getTokens());
        ExecutorService executor2 = Executors.newSingleThreadExecutor();
        executor2.execute(() -> {
            int tokensAtStart = gameStatistics.getTokens();
            boolean tokensUsed = false;
            while (!tokensUsed) {
                try {
                    int tokensNow = gameStatistics.getTokens();
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

    public abstract void characterDescription();

    // Getters and setters can be added for the private fields

}

