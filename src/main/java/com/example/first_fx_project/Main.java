package com.example.first_fx_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("menuPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1440, 810);
        stage.getIcons().add(new Image(Objects.requireNonNull(Objects.requireNonNull(getClass().getResource("assets/stickHero0.png")).toExternalForm())));
        stage.setMaximized(true);
        stage.setFullScreen(true);
        stage.setTitle("Pause Menu");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setMaximized(true);
        stage.setFullScreenExitHint("");
        stage.setTitle("Stick Hero");

        stage.show();
    }

        public static void deserialize() throws IOException, ClassNotFoundException {
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream (
                    new FileInputStream("GameProgress.txt"));
            GameStatistics gameStatistics = (GameStatistics) in.readObject();
            GameStatistics singletonInstance = GameStatistics.getInstance();
            singletonInstance.setTokens(gameStatistics.getTokens());
            singletonInstance.setBestScore(gameStatistics.getBestScore());
            } finally {
            if(in!=null) {
                in.close();
            }
            }
        }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //JUNIT TESTS
//        CollisionCalculatorTest
        TestRunner.main(args);
        try {
            deserialize();
        } catch (FileNotFoundException e) {
            System.out.println("WELCOME TO GAME");
        }

        launch();
    }
}