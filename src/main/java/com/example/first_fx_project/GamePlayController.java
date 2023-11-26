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
import javafx.scene.transform.Rotate;
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

    private int totalIncrement;

    private int invisiblePlatform = 0;
    private int targetPlatform = 0;

    public Line getStickLine() {
        if (stick1.isCurrentStick()) {
            return stickLine1;
        }
        else{
            return stickLine2;
        }
    }


    public ImageView getImgDefaultCharacter() {
        return imgDefaultCharacter;
    }

    public Rectangle getCurrentPlatformRectangle() {
        if (platform1.getPlatformType() == 1) {
            return platformRectangle1;
        }
        else if (platform2.getPlatformType() == 1){
            return platformRectangle2;
        }
        else if (platform3.getPlatformType() == 1){
            return platformRectangle3;
        }
        System.exit(1);
        return platformRectangle1;
    }

    public Rectangle getTargetPlatformRectangle() {
        if (platform1.getPlatformType() == 2) {
            return platformRectangle1;
        }
        else if (platform2.getPlatformType() == 2){
            return platformRectangle2;
        }
        else if (platform3.getPlatformType() == 2){
            return platformRectangle3;
        }
        System.out.println("Hello");
        System.exit(1);
        return platformRectangle1;
    }

    public Platform getInvisiblePlatform() {
        if(platform1.getPlatformType() == 3){
            return platform1;
        }
        else if(platform2.getPlatformType() == 3){
            return platform2;
        }
        else if(platform3.getPlatformType() == 3){
            return platform3;
        }
        System.out.println("Hello");
        System.exit(1);
        return platform1;
    }

    public Platform getTargetPlatform() {
        if(platform1.getPlatformType() == 2){
            return platform1;
        }
        else if(platform2.getPlatformType() == 2){
            return platform2;
        }
        else if(platform3.getPlatformType() == 2){
            return platform3;
        }
        System.out.println("Hello");
        System.exit(1);
        return platform1;
    }

    public Platform getCurrentPlatform() {
        if(platform1.getPlatformType() == 1){
            return platform1;
        }
        else if(platform2.getPlatformType() == 1){
            return platform2;
        }
        else if(platform3.getPlatformType() == 1){
            return platform3;
        }
        System.out.println("Hlo");
        System.exit(1);
        return platform1;
    }

    public Rectangle getInvsiblePlatformRectangle() {
        if(platform1.getPlatformType() == 3){
            return platformRectangle1;
        }
        else if(platform2.getPlatformType() == 3){
            return platformRectangle2;
        }
        else if(platform3.getPlatformType() == 3){
            return platformRectangle3;
        }
        System.out.println("Hello1");
        System.exit(1);
        return platformRectangle1;
    }


    @FXML
    public void initialize() {
        platform1 = new Platform(1); // Initialize with appropriate parameters
        platform2 = new Platform(2); // Initialize with appropriate parameters
        platform3 = new Platform(3); // Initialize with appropriate parameters
        stick1 = new Stick(true); // Initialize with appropriate parameters
        stick2 = new Stick(false); // Initialize with appropriate parameters
        player = new Player(); // Initialize with appropriate parameters

        // Now you can use these objects in your controller as needed
        // For example:
        platformRectangle1.setX(platform1.getPosition().getX());
        platformRectangle1.setWidth(platform1.getWidth());
        platformRectangle2.setX(platform2.getPosition().getX());
        platformRectangle2.setWidth(platform2.getWidth());
        platformRectangle3.setX(platform3.getPosition().getX());
        platformRectangle3.setWidth(platform3.getWidth());
        initializeStick();

    }

    private void initializeStick() {
        int stickStartX = getCurrentPlatform().getPosition().getX() + getCurrentPlatform().getWidth() - 3;
        System.out.println("K: " +getCurrentPlatform().getWidth());
        System.out.println("<E<E<E<E: "+stickStartX + totalIncrement);
        getStickLine().getTransforms().clear();
        getStickLine().setEndY(getStickLine().getStartY());

        getStickLine().setStartX(stickStartX + totalIncrement);
        getStickLine().setEndX(stickStartX + 1 + totalIncrement);
        System.out.println(stickLine2.getStartX());

        System.out.println(stickLine1.getStartX());
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
    void invertPlayer(KeyEvent event) {
        if (event.getCode() == KeyCode.SPACE) {
        }
    }

    boolean checkStickCollision() {
        double stickLength = getStickLine().getStartY() - getStickLine().getEndY();
        double stickX = stickLength + getStickLine().getStartX();
        if (stickX > getTargetPlatformRectangle().getX() && stickX < (getTargetPlatformRectangle().getX() + getTargetPlatformRectangle().getWidth())) {
            //score ++
            //check collision with hitPoint
            //Player Moves
            //Calls Scenes Change
            System.out.println("Collision!! RUN");
            changeScene();
            return true;
        }
        System.out.println("NO Collision!! DONT RUN");
        changeScene();
        return false;
    }


    void changeScene(){
        int increment = getTargetPlatform().getMidX() - 125;
        System.out.println("I AM MOVING"+ increment);
        // Create a TranslateTransition for the AnchorPane
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), movableComponents);
        translateTransition.setToX(movableComponents.getTranslateX() - increment);
        translateTransition.play();
        totalIncrement += increment;

        translateTransition.setOnFinished(event -> {
            redefineVariables(increment);
            extendStickButton.setDisable(false);
        });
        }


    private void invertStickConfiguration() {
        if (stick1.isCurrentStick()){
            stick1.setCurrentStick(false);
            stick2.setCurrentStick(true);
        }
        else{
            stick1.setCurrentStick(true);
            stick2.setCurrentStick(false);
        }
    }

    private void redefineVariables(int increment) {
        platform1.redefinePosition(increment);
        platform2.redefinePosition(increment);
        platform3.redefinePosition(increment);

        invertStickConfiguration(); //Inverts currentStick Variable
        initializeStick(); //
        initializeInvisiblePlatform();
       // stick1.redefinePosition(increment);
       // stick2.redefinePosition(increment);
    }

    private void initializeInvisiblePlatform() {
        getInvsiblePlatformRectangle().setX(getInvisiblePlatform().getPosition().getX()+totalIncrement);
        getInvsiblePlatformRectangle().setWidth(getInvisiblePlatform().getWidth());

    }


    @FXML
    void rotateStick(KeyEvent event) {
        if (event.getCode() == KeyCode.SPACE){
            extendStickButton.setDisable(true);
            //invertPlayerButton.setDisable(false);
            Rotate rotate = new Rotate();
            rotate.setPivotX(getStickLine().getStartX());
            rotate.setPivotY(getStickLine().getStartY());

            getStickLine().getTransforms().add(rotate);

            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(rotate.angleProperty(), 0)),
                    new KeyFrame(Duration.seconds(1), new KeyValue(rotate.angleProperty(), 90))
            );

            timeline.setCycleCount(1);

            timeline.setOnFinished(e -> {
                checkStickCollision(); // Call the collision check after animation finishes
            });

            timeline.play();
        }
    }




}

