package com.example.first_fx_project;
public class Platform {
    static private int length;
    private int width;
    private int midPosition;
    private int startPosition;
    private int endPosition;
    static private int hitPointWidth;
    private int hitPointStart;
    private int hitPointEnd;

    public Platform(int length, int width, int midPosition, int startPosition, int endPosition,
                    int hitPointWidth, int hitPointStart, int hitPointEnd) {
        this.length = length;
        this.width = width;
        this.midPosition = midPosition;
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        this.hitPointWidth = hitPointWidth;
        this.hitPointStart = hitPointStart;
        this.hitPointEnd = hitPointEnd;
    }

    static public void generatePlatform() {
        // Logic to generate the platform
    }

    static public void removePlatform() {
        // Logic to remove the platform from the game
    }

    // Getters and setters for the attributes
}

