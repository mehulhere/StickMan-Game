package com.example.first_fx_project;


import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GameOverController extends SceneController{

    @FXML
    private Label gameOverScore;

    public void setGameOverScore(String score){
        gameOverScore.setText(score);
    }

}
