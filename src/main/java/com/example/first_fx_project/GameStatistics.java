package com.example.first_fx_project;
public class GameStatistics {
    private int currentScore;
    private int tokens;
    private int powerUpMeter;
    static private int bestScore = 0;

    // Constructor
    public GameStatistics() {
        this.currentScore = 0;
        this.tokens = 0;
        this.powerUpMeter = 0;
    }

    // Method to reset all stats to default values
    public void resetStats() {
        this.currentScore = 0;
        this.tokens = 0;
        this.powerUpMeter = 0;
    }

    // Method to update the current score
    public void updateScore() {
        //Implementation goes here
    }

    // Getters and Setters (if needed)
    public int getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }

    public int getTokens() {
        return tokens;
    }

    public void setTokens(int tokens) {
        this.tokens = tokens;
    }

    public int getPowerUpMeter() {
        return powerUpMeter;
    }

    public void setPowerUpMeter(int powerUpMeter) {
        this.powerUpMeter = powerUpMeter;
    }

    static public int getBestScore() {
        return bestScore;
    }

    static public void setBestScore(int bestScore) {
        GameStatistics.bestScore = bestScore;
    }
}

