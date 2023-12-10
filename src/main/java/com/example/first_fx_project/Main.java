package com.example.first_fx_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javax.sound.sampled.*;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            URL audioURL = getClass().getResource("assets/BGSound.wav");
            Thread audioThread = new Thread(() -> {
                try {
                    File audioFile = new File(audioURL.toURI());
                    System.out.println(audioFile);
                    AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
                    AudioFormat format = audioStream.getFormat();
                    DataLine.Info info = new DataLine.Info(Clip.class, format);
                    Clip clip = (Clip) AudioSystem.getLine(info);
                    clip.open(audioStream);
                    clip.loop(Clip.LOOP_CONTINUOUSLY);

                    Thread.sleep(clip.getMicrosecondLength() / 1000);
                } catch (UnsupportedAudioFileException | LineUnavailableException | IOException |
                         InterruptedException | NullPointerException e) {
                    e.printStackTrace();
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });

            audioThread.start();
        }
        catch (NullPointerException nullPointerException){
            nullPointerException.printStackTrace();
        }

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
        } catch (Exception e) {
            System.out.println("WELCOME TO GAME");
        }
        launch();
    }
}