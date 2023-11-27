package com.example.first_fx_project;

import java.util.Random;

public class Platform {
    Random random = new Random();
    static private int height = 100;
    private int width;

    private Position position;

    private int midX; //Mid-point of the platform




    private int platformType; // 1 for current. 2 for target. 3 for invisible.


    public Platform(int platformNumber) { // Width should be between some particular values
        this.platformType = platformNumber;
        platformDefine();
    }

    public void platformDefine() {
        switch(platformType){
            case 1-> midX = 125;
            case 2-> midX = random.nextInt(300,600);
            case 3-> midX = random.nextInt(900,1000);
        }
        this.width = random.nextInt(80,150);
        this.position = new Position(midX-width/2, height);
    }

    public void redefinePosition(int increment) {
        int newX = position.getX() - increment;
       // System.out.println(position.getX());
        //System.out.println(newX);
        System.out.println(increment);
        if (platformType == 1){
            System.out.println("I am becoming new Invisible");
            platformType = 3;
            platformDefine();
        }
        else if(platformType == 2){ //Better Condition Required
            platformType = 1;
            position = new Position(newX, position.getY());
            midX = newX + width/2;
        }
        else{
            System.out.println(" I Am becoming new Target");
            platformType = 2;
            position = new Position(newX, position.getY());
            midX = newX + width/2;
        }
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


    public int getPlatformType() {
        return platformType;
    }

    public void setPlatformType(int platformType) {
        this.platformType = platformType;
    }

}

