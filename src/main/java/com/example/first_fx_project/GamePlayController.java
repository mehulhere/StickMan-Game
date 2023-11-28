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
    public ImageView imgToken;
    @FXML
    public ImageView imgToken2;
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
    private Token token1;
    private Token token2;
    private GameMechanics gameMechanics;

    private double totalShiftDistance;


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
        token1 = new Token(this, imgToken, getCurrentPlatformRectangle(), getTargetPlatformRectangle(), true);
        token2 = new Token(this, imgToken2, getTargetPlatformRectangle(), getInvisiblePlatformRectangle(), false);
        defaultCharacter = new DefaultCharacter(this, imgDefaultCharacter);
        gameMechanics = new GameMechanics(this);
        Stick.initializeStick(getCurrentPlatform(), getStickLine());
    }

    void redefineVariables(double increment) {
        System.out.println("Redefining Variables");
        platform1.redefinePosition(increment, totalShiftDistance);
        platform2.redefinePosition(increment, totalShiftDistance);
        platform3.redefinePosition(increment, totalShiftDistance);
        Stick.invertStickConfiguration(stick1, stick2); //Inverts currentStick Variable
        Stick.initializeStick(getCurrentPlatform(), getStickLine()); //
    }

    void midChangeSceneRedefineVariables(){
        System.out.println("Mid Scene Variable Redefinition");
        Token.invertTokenConfiguration(token1, token2);
        Token.reinitialize(token1, token2, getTargetPlatformRectangle(), Platform.setPlatform3Distance(getTargetPlatformRectangle().getX() + getTargetPlatformRectangle().getWidth()));
        System.out.println("Platform2X: "+ getTargetPlatformRectangle().getX());
        System.out.println("Platform3X: "+ Platform.getPlatform3X());
        getInvisiblePlatformRectangle().setX(800 + totalShiftDistance);
    }

    void notCurrentTokenMovementAnimation(){

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

    void playerMove(double playerFinalX, boolean alive){
        defaultCharacter.move(playerFinalX, getTargetPlatform(), Token.getCurrentToken(token1, token2), alive);
    }

    void playerFall(){
        defaultCharacter.fall();
    }

    void stopInversion(){
        invertPlayerButton.setDisable(true);
    }


    void changeScene(){
        System.out.println("Changing Scene");
        double shiftDistance = getTargetPlatformRectangle().getX()-getCurrentPlatformRectangle().getX();
        totalShiftDistance += shiftDistance;
        System.out.println("LastShiftDistance: "+shiftDistance);
        System.out.println("TotalShiftDistance: " +totalShiftDistance);
        gameMechanics.changeScene(getTargetPlatform(), movableComponents, shiftDistance, extendStickButton);
        midChangeSceneRedefineVariables();
        Platform.animateTranslateInvisiblePlatform(getTargetPlatformRectangle(), getInvisiblePlatformRectangle(), totalShiftDistance);
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

    public Rectangle getInvisiblePlatformRectangle() {
        return getInvisiblePlatform().getPlatformRectangle();
    }

    public Rectangle getCurrentPlatformRectangle() {
        return getCurrentPlatform().getPlatformRectangle();
    }

    public Rectangle getTargetPlatformRectangle() {
        return getTargetPlatform().getPlatformRectangle();
    }

}

