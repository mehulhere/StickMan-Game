package com.example.first_fx_project;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Platform {
    static Random random = ThreadLocalRandom.current(); //Generate Better Quality Random
    static private int height = 100;

    private static int platform3Distance;

    private static Map<Integer, Platform> PlatformInstances =
            new HashMap<Integer, Platform>(); //Flyweight Design Pattern Implemented

    public static int getPlatform3X() {
        return platform3Distance;
    }

    public static double setPlatform3Distance(double currentPlatformEndX) {
        System.out.println(currentPlatformEndX);
        platform3Distance = (int) (random.nextInt(200, 500) + (currentPlatformEndX + safetyDistance));
        System.out.println("New PlatformX: "+platform3Distance);
        return platform3Distance;
    }

    public static Platform getInstance(int platformType) {
        if (!PlatformInstances.containsKey(platformType)) {
            PlatformInstances.put(platformType, new Platform(platformType));
        }
        return PlatformInstances.get(platformType);
    }

    private int platformType; // 1 for current. 2 for target. 3 for invisible.
    private Rectangle platformRectangle;

    private static int maxWidth = 250;
    private static int minWidth= 90;

    private static int safetyDistance = 10;

    private GamePlayController gamePlayController;

    private Platform(int platformNumber) { // Width should be between some particular values
        this.platformType = platformNumber;
//        this.platformRectangle = platformRectangle;
    }

    public void platformDefine(int totalIncrement, int lastIncrement) {
        int width = random.nextInt(minWidth,maxWidth);
        platformRectangle.setWidth(width);
        switch(platformType){
            case 1-> {
                int midX = 400;
                double relativePosition = midX - (double) width /2;
                double absolutePosition = totalIncrement + relativePosition;
                platformRectangle.setX(absolutePosition);
                System.out.println("Platform1 X:"+ absolutePosition);
            }
            case 2-> {
                int midX = random.nextInt(600,1000);
                double relativePosition = midX - (double) width /2;
                double absolutePosition = totalIncrement + relativePosition;
                platformRectangle.setX(absolutePosition);
                System.out.println("Platform2 X:"+ absolutePosition);
            }
            case 3-> {
                int midX = random.nextInt(1500,1600);
                double relativePosition =  midX - (double) width /2;
                double absolutePosition = totalIncrement + relativePosition;
                platformRectangle.setX(absolutePosition);
                System.out.println("Platform3 X:"+ absolutePosition);
            }
        }
    }

    public void redefinePlatform() {
        if (platformType == 1){
            platformType = 3;
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
        int width = random.nextInt(minWidth,maxWidth);
        System.out.println("PlatformWidth: "+width);
        targetPlatform.setWidth(width);
        double targetPlatformStartX = getPlatform3X();
        System.out.println("TargetPlatformStartX: " + targetPlatformStartX);
        Timeline timeline= new Timeline();
//        try {
//            Thread.sleep(200);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        System.out.println( "Total Shift Distance: "+totalShiftDistance);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.3), new KeyValue(targetPlatform.xProperty(), targetPlatformStartX));
        timeline.getKeyFrames().add(keyFrame);

        timeline.setOnFinished(event -> {
            System.out.println("Animation finished");
            // Execute the provided onFinish Runnable
            GamePlayController.getHitPointFront().isVisible(true);
        });

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


    public int getPlatformType() {
        return platformType;
    }

    public void setPlatformType(int platformType) {
        this.platformType = platformType;
    }

    public Rectangle getPlatformRectangle() {
        return platformRectangle;
    }

    public void setPlatformRectangle(Rectangle platformRectangle) {
        this.platformRectangle = platformRectangle;
        platformDefine( 0, 0);
    }
}

