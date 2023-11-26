package com.example.first_fx_project;
public class Stick {
    static private int width = 20;
    private int length;
    private Position position;
    private int maxLength= 1000;

    private boolean currentStick;



    public Stick(boolean currentStick) {
        this.currentStick = currentStick;
    }

    public void generateStick(int length) {
        // Implement stick extension logic
    }

    public void removeStick() {
        // Implement stick removal logic
    }

    // Getters and setters for the class properties (width, length, startPosition, maxLength)
    public boolean isCurrentStick() {
        return currentStick;
    }

    public void setCurrentStick(boolean currentStick) {
        this.currentStick = currentStick;
    }
}


