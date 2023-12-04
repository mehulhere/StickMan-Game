package com.example.first_fx_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("menuPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),1440,810);
        stage.setMaximized(true);
        stage.setFullScreen(true);
        stage.setTitle("Pause Menu");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setMaximized(true);
        stage.setFullScreenExitHint("");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}