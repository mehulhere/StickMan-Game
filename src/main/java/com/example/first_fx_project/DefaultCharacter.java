package com.example.first_fx_project;

import javafx.scene.image.ImageView;

public class DefaultCharacter extends Player {
    static private int maxMeter;

    public DefaultCharacter(GamePlayController gamePlayController, ImageView imgDefaultCharacter) {
        super(gamePlayController, imgDefaultCharacter);
    }

    @Override
    public void characterDescription(){
        System.out.println("I am Default Character, I don't have any special powers");
    }

    public static int getMaxMeter() {
        return maxMeter;
    }

    public static void setMaxMeter(int maxMeter) {
        DefaultCharacter.maxMeter = maxMeter;
    }

}
