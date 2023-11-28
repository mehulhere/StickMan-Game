package com.example.first_fx_project;

import javafx.animation.TranslateTransition;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class GameMechanics {
    private GamePlayController gamePlayController;

    public GameMechanics(GamePlayController gamePlayController) {
        this.gamePlayController = gamePlayController;
    }

    public void changeScene(Platform platform2, AnchorPane movableComponents, double shiftDistance, Button extendStickButton){
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // Create a TranslateTransition for the AnchorPane
        System.out.println("Shifting Anchor Pane");
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5), movableComponents);
        translateTransition.setToX(movableComponents.getTranslateX() - shiftDistance);
        translateTransition.play();
        translateTransition.setOnFinished(event -> {

            gamePlayController.redefineVariables(shiftDistance);
            extendStickButton.setDisable(false);
            extendStickButton.requestFocus();
        });
    }

    public boolean checkCollision(Line stickLine, Rectangle platformRectangle, Platform platform2){

        double stickEndX = stickLine.getStartX() + stickLength;
        System.out.println("Running Collision Check");
        System.out.println("Stick EndX: "+stickEndX);
        double platformStartX = platformRectangle.getX();
        double platformEndX = platformRectangle.getX() + platformRectangle.getWidth();
        System.out.println("Platform StartX: "+platformStartX);
        System.out.println("Platform EndX: "+platformEndX);

        if (stickEndX > platformStartX && stickEndX < platformEndX) {
            System.out.println("Collision!! RUN");
            gamePlayController.setHitPointPosition(gamePlayController.getHitPointBack(), gamePlayController.getHitPointFront().getHitPointPosition());
            gamePlayController.getHitPointBack().changeColor(checkHitPointCollision(stickX));
            gamePlayController.setHitsPoint(checkHitPointCollision(stickX));
            double playerFinalX = platformRectangle.getX() + platformRectangle.getWidth()/2;
            System.out.println("Player FinaLX: "+ playerFinalX);
            gamePlayController.playerMove(playerFinalX, true);

            return true;
        }
        System.out.println("NO Collision!! DONT RUN");
        gamePlayController.playerMove(stickEndX, false);
        System.out.println("StickEndX: "+ stickEndX);
        return false;
    }

    public boolean checkHitPointCollision(double stickX){
        double lowerLimit = gamePlayController.getHitPointBack().getHitPointPosition();
        return stickX >= lowerLimit - 1 && stickX <= lowerLimit + HitPoint.getWidth() + 1;
    }

    public boolean checkTokenCollision(Token token) {
        // Method to check collision between the player and a token
        // Returns true if collision occurs, false otherwise
        return false;
    }

    public void destroyPlayerClass() {
        // Method to handle the destruction of the player class
    }

    public void resetMeter() {
        // Method to reset powerMeter
    }
}