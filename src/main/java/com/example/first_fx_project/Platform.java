package com.example.first_fx_project;

import java.util.Random;

public class Platform {
    Random random = new Random();
    static private int height = 100;
    private int width;

    private Position position;


    public Platform() { // Width should be between some particular values

    }

    public void generatePlatform() {
        // Logic to generate the platform
        this.width = random.nextInt(50) + 20;
        int randomX = random.nextInt(100)+300;
        this.position = new Position(randomX, height);
    }

    static public void removePlatform() {
        // Logic to remove the platform from the game
    }

    // Getters and setters for the attributes
}

