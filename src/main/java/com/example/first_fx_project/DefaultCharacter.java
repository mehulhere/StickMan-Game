package com.example.first_fx_project;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class DefaultCharacter extends Player{
    static private int maxMeter;
    private Position position;

    private ImageView imgDefaultCharacter;
    public DefaultCharacter(ImageView imgDefaultCharacter) {
        System.out.println("yo");
        this.imgDefaultCharacter = imgDefaultCharacter;
        setPosition(100, 460);
    }

    public void setPosition(double x, double y){
        imgDefaultCharacter.setX(x);
        imgDefaultCharacter.setY(y);

    }

    public void invert(){
        imgDefaultCharacter.setScaleX(-1);
    }
}
