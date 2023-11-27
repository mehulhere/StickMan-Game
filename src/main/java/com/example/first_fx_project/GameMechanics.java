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

    public void changeScene(Platform platform2, AnchorPane movableComponents, int increment, Button extendStickButton){
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // Create a TranslateTransition for the AnchorPane
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), movableComponents);
        translateTransition.setToX(movableComponents.getTranslateX() - increment);
        translateTransition.play();
        translateTransition.setOnFinished(event -> {
            gamePlayController.redefineVariables(increment);
            extendStickButton.setDisable(false);
            extendStickButton.requestFocus();
        });
    }



    public boolean checkCollision(Line stickLine, Rectangle platformRectangle, Platform platform2){

        double stickLength = stickLine.getStartY() - stickLine.getEndY();
        double stickX = stickLength + stickLine.getStartX();
//        System.out.println(stickX);
//        System.out.println(platformRectangle.getX());
//        System.out.println(platformRectangle.getX() + platformRectangle.getWidth());
        if (stickX > platformRectangle.getX() && stickX < (platformRectangle.getX() + platformRectangle.getWidth())) {
            //score ++
            //check collision with hitPoint
            //Player Moves
            //Calls Scenes Change
            System.out.println("Collision!! RUN");
            gamePlayController.playerMove(platform2.getMidX() - 125, true);
            return true;
        }
        System.out.println("NO Collision!! DONT RUN");
        gamePlayController.playerMove(stickX - 125, false);
        return false;
    }


    public boolean checkTokenCollision(Token token) {
        // Method to check collision between the player and a token
        // Returns true if collision occurs, false otherwise
        return false;
    }

    public void destroyPlayerClass() {
        // Method to handle the destruction of the player class
    }

    public void playerDeath() {
        // Method to handle player death event
    }

    public void resetMeter() {
        // Method to reset powerMeter
    }
}
