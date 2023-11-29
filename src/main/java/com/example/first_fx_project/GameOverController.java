package com.example.first_fx_project;


import javafx.fxml.FXML;
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
        GameStatistics.setBestScore();
        gameOverBestScore.setText(Integer.toString(GameStatistics.getBestScore()));
    }

    public void setGameOverScore(String score){
        gameOverScore.setText(score);
    }

    public void setGameOverTokens(String tokens){
        gameOverTokens.setText(tokens);
    }
}
