package com.example.first_fx_project;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class Stick {
    private int length;
    private int maxLength= 1000;

    private boolean currentStick;

    private Line stickLine;



    public Stick(boolean currentStick, Line stickLine) {
        this.currentStick = currentStick;
        this.stickLine = stickLine;
    }

    static void initializeStick(Platform platform, Line stick) {
        int stickStartX = (int) (platform.getPlatformRectangle().getX()+platform.getPlatformRectangle().getWidth());
        stick.getTransforms().clear();
        stick.setStartX(stick.getEndX());
        stick.setEndY(stick.getStartY());
        stick.setOpacity(0);
        int aestheticMargin = 5;
        stick.setStartX(stickStartX );
        stick.setEndX(stickStartX);
    }

    public void rotateStick(Button extendStickButton, int num, GamePlayController gamePlayController) {
        Rotate rotate = new Rotate();
        rotate.setPivotX(stickLine.getStartX());
        rotate.setPivotY(stickLine.getStartY());
        stickLine.getTransforms().add(rotate);
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(rotate.angleProperty(), 0)),
                new KeyFrame(Duration.seconds(0.3), new KeyValue(rotate.angleProperty(), 90))
        );
        timeline.setCycleCount(1);
        if(num == 0){
            timeline.setOnFinished(e -> {
                //add sound
                System.out.println(".............................................................");
                gamePlayController.checkStickCollision(); // Call the collision check after animation finishes

            });
        }
        timeline.play();
    }

    public static void invertStickConfiguration(Stick stick1, Stick stick2) {
        if (stick1.isCurrentStick()){
            stick1.setCurrentStick(false);
            stick2.setCurrentStick(true);
        }
        else{
            stick1.setCurrentStick(true);
            stick2.setCurrentStick(false);
        }
    }

    public void setOpacity(int opacity){
        stickLine.setOpacity(opacity);
    }

    // Getters and setters for the class properties (width, length, startPosition, maxLength)
    public boolean isCurrentStick() {
        return currentStick;
    }


    public void setCurrentStick(boolean currentStick) {
        this.currentStick = currentStick;
    }
    public Line getStickLine() {
        return stickLine;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public void setStickLine(Line stickLine) {
        this.stickLine = stickLine;
    }
}


