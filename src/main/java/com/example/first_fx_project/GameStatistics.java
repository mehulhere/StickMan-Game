package com.example.first_fx_project;

import javafx.scene.control.Label;

import java.io.Serializable;

public class GameStatistics implements Serializable {

    private static int currentScore;
    private static GameStatistics instance = null;
    private  int tokens;
    private int bestScore;
    private static boolean highScoreChecked;

    private static int revivals;

    public static int getRevivals() {
        return revivals;
    }

    public static void setRevivals(int revivals) {
        GameStatistics.revivals = revivals;
    }

    // Constructor
    private GameStatistics() {
        currentScore = 0;
        tokens = 0;
        bestScore = 0;
        highScoreChecked = false;
        revivals = 0;
    }

    // Method to get the singleton instance
    public static GameStatistics getInstance() {
        if (instance == null) {
            instance = new GameStatistics();
        }
        return instance;
    }

    // Method to update the current score
    public static void updateScore(Label score, boolean hitsPoint) {
        int value = ++currentScore;
        if(hitsPoint){
            currentScore++;
        }
        score.setText(Integer.toString(currentScore));
    }

    public int getTokens() {
        return tokens;
    }

    public static void setCurrentScore(int score){
        currentScore = score;
    }

    public void checkBestScore(){
        if(currentScore > bestScore){
            this.bestScore = currentScore;
        }
    }

    public static void setHighScoreChecked(boolean highScoreChecked) {
        GameStatistics.highScoreChecked = highScoreChecked;
    }

    public static boolean isHighScoreChecked() {
        return highScoreChecked;
    }

    public int getBestScore() {
        return bestScore;
    }

    public  boolean checkHighScore(){
        return (currentScore > bestScore);
    }

    public void setTokens(int tokens) {
        this.tokens = tokens;
    }

    public static int getCurrentScore() {
        return currentScore;
    }

    public void setBestScore(int bestScore) {
        this.bestScore = bestScore;
    }
}

