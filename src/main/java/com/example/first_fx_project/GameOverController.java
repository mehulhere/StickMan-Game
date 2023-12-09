package com.example.first_fx_project;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class GameOverController extends SceneController{

    @FXML
    private Label gameOverScore;

    @FXML
    private Label gameOverTokens;

    @FXML
    private Label gameOverBestScore;

    @FXML
    public void initialize(){
        gameStatistics.checkBestScore();
        gameOverBestScore.setText(Integer.toString(gameStatistics.getBestScore()));
        System.out.println("Best Score: "+gameStatistics.getBestScore());
        gameOverTokens.setText(Integer.toString(gameStatistics.getTokens()));
        gameOverScore.setText(Integer.toString(GameStatistics.getCurrentScore()));
        System.out.println("Inside GameOver Page: "+gameStatistics.getTokens());
        System.out.println(gameOverTokens.getText());
        int finalTokens = gameStatistics.getTokens()-GameStatistics.getRevivals()-1;
        if (finalTokens < 0) {
            reviveButton.setDisable(true);
        }
    }

    @FXML
    private Button reviveButton;

    public void setGameOverScore(String score){
        gameOverScore.setText(score);
    }

    public void setGameOverTokens(String tokens){
        gameOverTokens.setText(tokens);
    }
}