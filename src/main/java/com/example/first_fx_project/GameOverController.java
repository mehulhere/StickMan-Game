package com.example.first_fx_project;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

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

    @FXML
    private Button reviveButton;


    public void setGameOverScore(String score){
        gameOverScore.setText(score);
    }


    public void setGameOverTokens(String tokens){
        gameOverTokens.setText(tokens);
    }



}
