package com.example.first_fx_project;

import java.util.Random;

public class Platform {
    Random random = new Random();
    static private int height = 100;
    private int width;

    private Position position;

    private int midX; //Mid-point of the piller

    private boolean currentPlatform;

    public Platform(boolean currentPlatform) { // Width should be between some particular values
        this.currentPlatform=currentPlatform;
        platformDefine();
    }

    public void platformDefine() {
        if(currentPlatform){
            midX = 10;
        }
        else{
            midX = random.nextInt(200,600);
        }
        this.width = random.nextInt(20,100);
        this.position = new Position(midX-width/2, height);
    }

    static public void removePlatform() {
        // Logic to remove the platform from the game
    }

    // Getters and setters for the attributes

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public static int getHeight() {
        return height;
    }

    public static void setHeight(int height) {
        Platform.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getMidX() {
        return midX;
    }

    public void setMidX(int midX) {
        this.midX = midX;
    }

    public boolean isCurrentPlatform() {
        return currentPlatform;
    }

    public void setCurrentPlatform(boolean currentPlatform) {
        this.currentPlatform = currentPlatform;
    }
}

