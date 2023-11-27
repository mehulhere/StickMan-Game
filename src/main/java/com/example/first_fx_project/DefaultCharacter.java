package com.example.first_fx_project;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class DefaultCharacter extends Player{
    static private int maxMeter;
    private Position position;

    public DefaultCharacter(GamePlayController gamePlayController, ImageView imgDefaultCharacter) {
        super(gamePlayController, imgDefaultCharacter);
        setPosition(100, 460);
    }

    public void setPosition(double x, double y){
        image.setX(x);
        image.setY(y);

    }
}
