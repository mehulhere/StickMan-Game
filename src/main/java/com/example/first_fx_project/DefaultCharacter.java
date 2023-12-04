package com.example.first_fx_project;

import javafx.scene.image.ImageView;

public class DefaultCharacter extends Player{
    static private int maxMeter;
    private Position position;

    public DefaultCharacter(GamePlayController gamePlayController, ImageView imgDefaultCharacter) {
        super(gamePlayController, imgDefaultCharacter);
    }

    public void setPosition(double x, double y){
        image.setX(x);
        image.setY(y);

    }
}
