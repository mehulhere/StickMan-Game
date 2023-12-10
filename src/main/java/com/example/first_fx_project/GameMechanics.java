package com.example.first_fx_project;

import javafx.animation.TranslateTransition;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class GameMechanics implements Mechanics {
    private GamePlayController gamePlayController;
    private static GameMechanics gameMechanics = null;
    public static GameMechanics getInstance()
    {
        if (gameMechanics == null) {
            gameMechanics = new GameMechanics();
        }
        return gameMechanics;
    }

    private GameMechanics() {
    }

    @Override
    public void changeScene(Platform platform2, AnchorPane movableComponents, double shiftDistance, Button extendStickButton){
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // Create a TranslateTransition for the AnchorPane
        System.out.println("Shifting Anchor Pane");
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.3), movableComponents);
        translateTransition.setToX(movableComponents.getTranslateX() - shiftDistance);
        translateTransition.play();
        translateTransition.setOnFinished(event -> {
            gamePlayController.redefineVariables();
            gamePlayController.rotateBackground(shiftDistance);
            extendStickButton.setDisable(false);
            extendStickButton.requestFocus();


        });
    }

    public boolean collisionCalculator(double stickEndX, double platformStartX, double platformEndX){
        return stickEndX >= platformStartX && stickEndX <= platformEndX;
    }

    @Override
    public void checkCollision(Line stickLine, Rectangle platformRectangle, Platform platform2, ImageView playerImage){
        double stickLength = stickLine.getStartY() - stickLine.getEndY();
        double stickEndX = stickLine.getStartX() + stickLength;
        System.out.println("Running Collision Check");
        System.out.println("Stick StartX: "+stickLine.getStartX());
        System.out.println("Stick Length: "+stickLength);
        System.out.println("Stick EndX: "+stickEndX);
        double platformStartX = platformRectangle.getX();
        double platformEndX = platformRectangle.getX() + platformRectangle.getWidth();
        System.out.println("Platform StartX: "+platformStartX);
        System.out.println("Platform EndX: "+platformEndX);
        int aestheticMargin = 4;
        double playerFinalX = platformEndX - playerImage.getFitWidth() - aestheticMargin;
        if (collisionCalculator(stickEndX, platformStartX, platformEndX)) {
            System.out.println("Collision!! RUN");
            gamePlayController.setHitPointPosition(GamePlayController.getHitPointBack(), GamePlayController.getHitPointFront().getHitPointPosition());
            GamePlayController.getHitPointBack().changeColor(checkHitPointCollision(stickEndX));
            gamePlayController.setHitsPoint(checkHitPointCollision(stickEndX));

            System.out.println("Player FinaLX: "+ playerFinalX);
            gamePlayController.playerMove(playerFinalX, stickEndX, true);
            System.out.println("Application Thread Finished check collision");
            return;
        }
        System.out.println("NO Collision!! DONT RUN");
        gamePlayController.playerMove(playerFinalX, stickEndX, false);
        System.out.println("Application Thread Finished check collision");
        System.out.println("StickEndX: "+ stickEndX);
    }

    public boolean hitPointCollisionCalculator(double lowerLimit, double stickX){
        return stickX >= lowerLimit - 1 && stickX <= lowerLimit + HitPoint.getWidth() + 1;
    }

    public boolean checkHitPointCollision(double stickX){
        double lowerLimit = GamePlayController.getHitPointBack().getHitPointPosition();
        return hitPointCollisionCalculator(lowerLimit, stickX);
    }

    public GamePlayController getGamePlayController() {
        return gamePlayController;
    }

    public void setGamePlayController(GamePlayController gamePlayController) {
        this.gamePlayController = gamePlayController;
    }

    public static GameMechanics getGameMechanics() {
        return gameMechanics;
    }

    public static void setGameMechanics(GameMechanics gameMechanics) {
        GameMechanics.gameMechanics = gameMechanics;
    }
}