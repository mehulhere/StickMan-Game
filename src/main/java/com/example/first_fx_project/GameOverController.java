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
        System.out.println("Best Score: "+GameStatistics.getBestScore());
        gameOverTokens.setText(Integer.toString(GameStatistics.getTokens()));
        System.out.println("Inside GameOver Page: "+GameStatistics.getTokens());
        System.out.println(gameOverTokens.getText());
        gameOverScore.setText(Integer.toString(GameStatistics.getCurrentScore()));
    }

    @FXML
    private Button reviveButton;

    public void setDisableReviveButton(boolean disableReviveButton) {
        reviveButton.setDisable(disableReviveButton);
    }
}
