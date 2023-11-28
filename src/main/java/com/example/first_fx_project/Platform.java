package com.example.first_fx_project;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.Random;

public class Platform {
    static Random random = new Random();
    static private int height = 100;
    private int width;

    private static int platform3Distance;

    public static int getPlatform3X() {
        return platform3Distance;
    }

    public static double setPlatform3Distance(double currentPlatformEndX) {
        platform3Distance = (int) (random.nextInt(0, 500) + (currentPlatformEndX + safetyDistance));
        System.out.println("New PlatformX: "+platform3Distance);
        return platform3Distance;
    }

    private int platformType; // 1 for current. 2 for target. 3 for invisible.
    private Rectangle platformRectangle;

    private static int maxWidth = 160;
    private static int minWidth= 80;

    private static int safetyDistance = 10;



    public Platform(int platformNumber, Rectangle platformRectangle) { // Width should be between some particular values
        this.platformType = platformNumber;
        this.platformRectangle = platformRectangle;
        platformDefine( 0, 0);
    }

    public void platformDefine(int totalIncrement, int lastIncrement) {
        width = random.nextInt(minWidth,maxWidth);
        switch(platformType){
            case 1-> {
                int midX = 125;
                double relativePosition = midX - (double) width /2;
                double absolutePosition = totalIncrement + relativePosition;
                platformRectangle.setX(absolutePosition);
                System.out.println("Platform1 X:"+ absolutePosition);
            }
            case 2-> {
                int midX = random.nextInt(400,600);
                double relativePosition = midX - (double) width /2;
                double absolutePosition = totalIncrement + relativePosition;
                platformRectangle.setX(absolutePosition);
                System.out.println("Platform2 X:"+ absolutePosition);
            }
            case 3-> {
                int midX = random.nextInt(900,1000);
                double relativePosition =  midX - (double) width /2;
                double absolutePosition = totalIncrement + relativePosition;
                platformRectangle.setX(absolutePosition);
                System.out.println("Platform3 X:"+ absolutePosition);
            }
        }
        platformRectangle.setWidth(width);
    }

    public void redefinePosition(double increment, double totalIncrement) {
        int newX = (int) (platformRectangle.getX() - increment);
        if (platformType == 1){
            platformType = 3;
//            platformDefine(totalIncrement, increment);
        }
        else if(platformType == 2){
            platformType = 1;
        }
        else if(platformType == 3){
            platformType = 2;
        }
    }


    // Getters and setters for the attributes
    public static void animateTranslateInvisiblePlatform(Rectangle currentPlatform, Rectangle targetPlatform, double totalShiftDistance) {
        System.out.println("Translating Invisible Platform");
        double targetPlatformStartX = getPlatform3X();
        System.out.println("TargetPlatformStartX: " + targetPlatformStartX);
        Timeline timeline= new Timeline();
//        try {
//            Thread.sleep(200);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        System.out.println( "Total Shift Distance: "+totalShiftDistance);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.4), new KeyValue(targetPlatform.xProperty(), targetPlatformStartX));
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }


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
    // Getters and setters for the attributes

    public int getWidth() {
        return width;
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

