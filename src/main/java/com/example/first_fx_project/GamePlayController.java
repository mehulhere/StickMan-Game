package com.example.first_fx_project;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class GamePlayController extends SceneController{

    @FXML
    private ImageView imgDefaultCharacter;

    @FXML
    private Line stickLine1;

    @FXML
    private Line stickLine2;

    @FXML
    private Button extendStickButton;

    @FXML
    private AnchorPane movableComponents;

    @FXML
    private Button invertPlayerButton;

    @FXML
    public Rectangle platformRectangle1;

    @FXML
    private Rectangle platformRectangle2;

    @FXML
    private Rectangle platformRectangle3;

    private Platform platform1;
    private Platform platform2;
    private Platform platform3;

    private Stick stick1;

    private Stick stick2;
    private Player player;
    private GameMechanics gameMechanics;

    private int totalIncrement;


    public Line getStickLine() {
        return getStick().getStickLine();
    }

    public Stick getStick() {
        if (stick1.isCurrentStick()) {
            return stick1;
        }
        else{
            return stick2;
        }
    }
    public DefaultCharacter defaultCharacter;

    @FXML
    public void initialize() {
        platform1 = new Platform(1, platformRectangle1);
        platform2 = new Platform(2, platformRectangle2);
        platform3 = new Platform(3, platformRectangle3);
        stick1 = new Stick(true, stickLine1);
        stick2 = new Stick(false, stickLine2);
        player = new Player(this, imgDefaultCharacter);
        defaultCharacter = new DefaultCharacter(this, imgDefaultCharacter);
        gameMechanics = new GameMechanics(this);
        Stick.initializeStick(getCurrentPlatform(), getStickLine());
    }

    @FXML
    void extendStick(KeyEvent event) {
        if (event.getCode() == KeyCode.SPACE) {
            Timeline timeline = new Timeline();
            timeline.getKeyFrames().clear(); // Clear existing keyframes
            timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(0.03), new KeyValue(getStickLine().endYProperty(), getStickLine().getEndY() - 10)));
            timeline.play();
        }
    }

    @FXML
    void stopExtendStick(KeyEvent event) {
        if (event.getCode() == KeyCode.SPACE) {
            extendStickButton.setDisable(true);
            rotateStick(0);
        }
    }

    @FXML
    void invertPlayer(KeyEvent event) {
        if (event.getCode() == KeyCode.SPACE) {
            defaultCharacter.invert();
        }
    }

    void checkStickCollision() {
        if(gameMechanics.checkCollision(getStickLine(), getTargetPlatformRectangle(), getTargetPlatform())) {
            invertPlayerButton.setDisable(false);
            invertPlayerButton.requestFocus();
        }
    }

    void playerMove(double increment, boolean alive){
        defaultCharacter.move(increment, getTargetPlatform(), getStickLine(), alive);
    }

    void playerFall(){
        defaultCharacter.fall();
    }


    void changeScene(){
        invertPlayerButton.setDisable(true);
        int increment = getTargetPlatform().getMidX() - 125;
        totalIncrement += increment;
        System.out.println("Total Increment is" +totalIncrement);
        gameMechanics.changeScene(getTargetPlatform(), movableComponents, increment, extendStickButton);
    }



    void redefineVariables(int increment) {
        platform1.redefinePosition(increment, totalIncrement);
        platform2.redefinePosition(increment, totalIncrement);
        platform3.redefinePosition(increment, totalIncrement );

        Stick.invertStickConfiguration(stick1, stick2); //Inverts currentStick Variable
        Stick.initializeStick(getCurrentPlatform(), getStickLine()); //

    }




    @FXML
    void rotateStick(int num) {
        getStick().rotateStick(extendStickButton, num, this);
    }


    //Helpers

    public Platform getInvisiblePlatform() {
        return Platform.checkPlatformType(platform1, platform2, platform3, 3);
    }
    public Platform getTargetPlatform() {
        return Platform.checkPlatformType(platform1, platform2, platform3, 2);
    }
    public Platform getCurrentPlatform() {
        return Platform.checkPlatformType(platform1, platform2, platform3, 1);
    }

    public Rectangle getInvsiblePlatformRectangle() {
        return getInvisiblePlatform().getPlatformRectangle();
    }

    public Rectangle getCurrentPlatformRectangle() {
        return getCurrentPlatform().getPlatformRectangle();
    }

    public Rectangle getTargetPlatformRectangle() {
        return getTargetPlatform().getPlatformRectangle();
    }

}

