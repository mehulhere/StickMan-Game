package com.example.first_fx_project;

import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Platform {
    Random random = new Random();
    static private int height = 100;
    private int width;


    private int midX; //Mid-point of the platform
    private int platformType; // 1 for current. 2 for target. 3 for invisible.
    private Rectangle platformRectangle;



    public Platform(int platformNumber, Rectangle platformRectangle) { // Width should be between some particular values
        this.platformType = platformNumber;
        this.platformRectangle = platformRectangle;
        platformDefine( 0);
    }

    public void platformDefine(int totalIncrement) {
        switch(platformType){
            case 1-> midX = 125;
            case 2-> midX = random.nextInt(300,600);
            case 3-> midX = random.nextInt(900,1000);
        }

        width = random.nextInt(80,150);
        platformRectangle.setX(totalIncrement + midX- (double) width /2);
        System.out.println("I am new Rectangle: "+ (totalIncrement + midX- (double) width /2));
        System.out.println("I am midX"+ midX);
        platformRectangle.setWidth(width);
    }

    public void redefinePosition(int increment, int totalIncrement) {
        int newX = (int) (platformRectangle.getX() - increment);
        if (platformType == 1){
            platformType = 3;
            platformDefine(totalIncrement);
        }
        else if(platformType == 2){ //Better Condition Required
            platformType = 1;
            midX = midX - increment;
            System.out.println("Current Pillar"+newX);
        }
        else{
            platformType = 2;
            midX = midX - increment;
            System.out.println("Target Pillar"+newX);
        }
    }

    // Getters and setters for the attributes

    public static Platform checkPlatformType(Platform platform1, Platform platform2, Platform platform3, int type) {
        if(platform1.getPlatformType() == type){
            return platform1;
        }
        else if(platform2.getPlatformType() == type){
            return platform2;
        }
        else if(platform3.getPlatformType() == type){
            return platform3;
        }
        return null;
    }

    public int getWidth() {
        return width;
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
    public Rectangle getPlatformRectangle() {
        return platformRectangle;
    }


}

