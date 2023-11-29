package com.example.first_fx_project;

import javafx.scene.control.Label;

public class GameStatistics {
    private static int currentScore;
    private static int tokens;
    private static int bestScore;
    private static boolean highScoreChecked;

    // Constructor
    public GameStatistics() {
        currentScore = 0;
        tokens = 0;
        bestScore = 0;
        highScoreChecked = false;
    }

    // Method to update the current score
    public static void updateScore(Label score, boolean hitsPoint) {
        int value = ++currentScore;
        if(hitsPoint){
            currentScore++;
        }
        score.setText(Integer.toString(currentScore));
    }

    public static void updateTokenCount(Label tokenLabel){
        tokenLabel.setText(Integer.toString(++tokens));
    }

    public static int getTokens() {
        return tokens;
    }

    public static void setCurrentScore(int score){
        currentScore = score;
    }

    public static void setBestScore(){
        if(currentScore > bestScore){
            bestScore = currentScore;
        }
    }

    public static void setHighScoreChecked(boolean highScoreChecked) {
        GameStatistics.highScoreChecked = highScoreChecked;
    }

    public static boolean isHighScoreChecked() {
        return highScoreChecked;
    }

    public static int getBestScore() {
        return bestScore;
    }

    public static boolean checkHighScore(){
        return (currentScore > bestScore);
    }
}

