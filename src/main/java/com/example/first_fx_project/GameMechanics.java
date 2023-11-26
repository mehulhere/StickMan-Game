package com.example.first_fx_project;

import javafx.animation.TranslateTransition;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class GameMechanics {
    private GamePlayController gamePlayController;

    public GameMechanics(GamePlayController gamePlayController) {
        this.gamePlayController = gamePlayController;
    }

    public void changeScene(Platform platform2, AnchorPane movableComponents){
        int increment = platform2.getMidX() - 125;
        // Create a TranslateTransition for the AnchorPane
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), movableComponents);
        translateTransition.setToX(movableComponents.getTranslateX() - increment);
        translateTransition.play();
    }

    public boolean checkCollision(Line stickLine1, Rectangle platformRectangle2, Platform platform2){

        double stickLength = stickLine1.getStartY() - stickLine1.getEndY();
        double stickX = stickLength + stickLine1.getStartX();
        System.out.println(stickX);
        System.out.println(platformRectangle2.getX());
        System.out.println(platformRectangle2.getX() + platformRectangle2.getWidth());
        if (stickX > platformRectangle2.getX() && stickX < (platformRectangle2.getX() + platformRectangle2.getWidth())) {
            //score ++
            //check collision with hitPoint
            //Player Moves
            //Calls Scenes Change
            System.out.println("Collision!! RUN");
            gamePlayController.playerMove(platform2.getMidX() - 125);
            return true;
        }
        System.out.println("NO Collision!! DONT RUN");
        gamePlayController.playerMove(stickLength);
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
