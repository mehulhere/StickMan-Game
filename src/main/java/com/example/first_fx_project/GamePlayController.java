package com.example.first_fx_project;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class GamePlayController extends SceneController{
    @FXML
    public ImageView imgToken;
    @FXML
    public ImageView imgToken2;

    @FXML
    public Button pauseButton;
    public ImageView imgToken1;

    @FXML
    private ImageView imgCharacter;

    @FXML
    private Line stickLine1;

    @FXML
    private Line stickLine2;

    @FXML
    private Button extendStickButton;

    @FXML
    private AnchorPane movableComponents;

    @FXML
    private AnchorPane firstController;

    @FXML
    private Button invertPlayerButton;

    @FXML
    public Rectangle platformRectangle1;

    @FXML
    private Rectangle platformRectangle2;

    @FXML
    private Rectangle platformRectangle3;

    @FXML
    private Rectangle hitPointRectangle2;

    @FXML
    private Rectangle hitPointRectangle1;

    @FXML
    private Label scoreLabel;

    @FXML
    private Label tokenLabel;

    @FXML
    private Label highScoreText;

    @FXML
    private ImageView bgImg1;

    @FXML
    private ImageView bgImg2;

    @FXML
    private ImageView bgImg3;

    private Platform platform1;
    private Platform platform2;
    private Platform platform3;
    private static HitPoint hitPointFront;
    private static HitPoint hitPointBack;

    private Stick stick1;

    private Stick stick2;
    private Player player;
    private Token token1;
    private Token token2;
    private GameMechanics gameMechanics;

    private boolean hitsPoint = false;
    private double totalShiftDistance;

    private DefaultCharacter defaultCharacter;

    private GameStatistics gameStatistics = GameStatistics.getInstance();

    private ArrayList<Image> backgroundList = new ArrayList<>();
    @FXML
    public void initialize() {
        Platform.platformReintialize();
        GameMechanics.getInstance().setGamePlayController(this);
        platform1 = Platform.getInstance(1); //Using Flyweight Design Pattern
        platform2 = Platform.getInstance(2);
        platform3 = Platform.getInstance(3);
        platform1.setPlatformRectangle(platformRectangle1);
        platform2.setPlatformRectangle(platformRectangle2);
        platform3.setPlatformRectangle(platformRectangle3);
        double platform2startX = platform2.getPlatformRectangle().getX();
        System.out.println("Platform2StartX: " + platform2startX);
        double platform2HalfWidth = platform2.getPlatformRectangle().getWidth()/2;
        double hitPointInitialPosition = platform2startX + platform2HalfWidth - (double) HitPoint.getWidth() /2;
        System.out.println("Hitpoint Posn: "+ hitPointInitialPosition);
        hitPointFront = new HitPoint(hitPointRectangle1, hitPointInitialPosition);
        hitPointBack = new HitPoint(hitPointRectangle2, hitPointFront.getHitPointPosition());
        stick1 = new Stick(true, stickLine1);
        stick2 = new Stick(false, stickLine2);
        token1 = new Token(this, imgToken, getCurrentPlatformRectangle(), getTargetPlatformRectangle(), true);
        token2 = new Token(this, imgToken2, getTargetPlatformRectangle(), getInvisiblePlatformRectangle(), false);
        defaultCharacter = new DefaultCharacter(this, imgCharacter);
        gameMechanics = GameMechanics.getInstance(); //Design Pattern: Singleton Used here
        gameMechanics.setGamePlayController(this);
        Stick.initializeStick(getCurrentPlatform(), getStickLine());
        tokenLabel.setText(Integer.toString(gameStatistics.getTokens()));
        GameStatistics.setCurrentScore(0);
        GameStatistics.setHighScoreChecked(false);
        GameStatistics.setRevivals(0);
        bgImg1.setX(0);
        bgImg2.setX(bgImg1.getX() + bgImg1.getFitWidth());
        bgImg3.setX(bgImg2.getX() + bgImg1.getFitWidth());
        loadThemes();

        bgImg1.setImage(backgroundList.get(ThemesController.getThemeIndex()));
        bgImg2.setImage(bgImg1.getImage());
        bgImg3.setImage(bgImg1.getImage());

        Image selectedImage = new Image(Objects.requireNonNull(getClass().getResource("assets/stickHero" + CharacterController.getCharacterIndex() + ".png")).toExternalForm());
        setCharacterPosition(selectedImage);
    }

    public void loadThemes(){
        backgroundList.add(new Image(Objects.requireNonNull(getClass().getResource("assets/background_City.png")).toExternalForm()));
        backgroundList.add(new Image(Objects.requireNonNull(getClass().getResource("assets/background_Mountains.png")).toExternalForm()));
        backgroundList.add(new Image(Objects.requireNonNull(getClass().getResource("assets/background_Dungeon.png")).toExternalForm()));
    }

    public void setCharacterPosition(Image selectedImage) {
        imgCharacter.setImage(selectedImage);
        double preserveRatio = selectedImage.getWidth()/40;
        imgCharacter.setFitHeight(selectedImage.getHeight()/preserveRatio);
        imgCharacter.setFitWidth(40);
        double pillarY = 555;
        imgCharacter.setY(pillarY - imgCharacter.getFitHeight());
    }

    void redefineVariables() {
        System.out.println("Redefining Variables");
        platform1.redefinePlatform();
        platform2.redefinePlatform();
        platform3.redefinePlatform();
        Stick.invertStickConfiguration(stick1, stick2); //Inverts currentStick Variable
        Stick.initializeStick(getCurrentPlatform(), getStickLine());

    }


    void midChangeSceneRedefineVariables(){
        System.out.println("Mid Scene Variable Redefinition");
        Token.invertTokenConfiguration(token1, token2);
        Token.reinitialize(token1, token2, getTargetPlatformRectangle(), Platform.setPlatform3Distance(getTargetPlatformRectangle().getX() + getTargetPlatformRectangle().getWidth()));

        System.out.println("Platform2X: "+ getTargetPlatformRectangle().getX());
        System.out.println("Platform3X: "+ Platform.getPlatform3X());
        getInvisiblePlatformRectangle().setX(1100 + totalShiftDistance);
    }


    void changeScene(){
        System.out.println("Changing Scene Midway");
        double shiftDistance = getTargetPlatformRectangle().getX()-getCurrentPlatformRectangle().getX();
        totalShiftDistance += shiftDistance;
        System.out.println("LastShiftDistance: "+shiftDistance);
        System.out.println("TotalShiftDistance: " +totalShiftDistance);
        gameMechanics.changeScene(getTargetPlatform(), movableComponents, shiftDistance, extendStickButton);
        midChangeSceneRedefineVariables();
        Platform.animateTranslateInvisiblePlatform(getTargetPlatformRectangle(), getInvisiblePlatformRectangle(), totalShiftDistance);
        double platform3startX = Platform.getPlatform3X();
        double platform3HalfWidth = getInvisiblePlatformRectangle().getWidth() / 2;
        double hitPointInitialPosition = platform3startX + platform3HalfWidth - (double) HitPoint.getWidth() /2;
        System.out.println("HitPoint Front NewX: "+hitPointInitialPosition);
        setHitPointPosition(getHitPointFront(), hitPointInitialPosition);
        System.out.println("Mid Scene Ended");
        //Variables redefined Beyond
    }


    @FXML
    void extendStick(KeyEvent event) {
        if (event.getCode() == KeyCode.SPACE) {
            getStickLine().setOpacity(1);
            Timeline timeline = new Timeline();
            timeline.getKeyFrames().clear(); // Clear existing keyframes
            timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(0.03), new KeyValue(getStickLine().endYProperty(), getStickLine().getEndY() - 25)));
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
            flipInverted();
        }
    }

    void flipInverted(){
        defaultCharacter.isInverted = !defaultCharacter.isInverted;
        System.out.println("Flip");
    }

    void checkStickCollision() {
        gameMechanics.checkCollision(getStickLine(), getTargetPlatformRectangle(), getTargetPlatform(), imgCharacter);
    }
    void enableInvertButton(){
        invertPlayerButton.setDisable(false);
        invertPlayerButton.requestFocus();
    }


    void playerMove(double playerFinalX, double stickEndX , boolean alive){
        defaultCharacter.move(playerFinalX,getCurrentPlatform(), getTargetPlatform(), Token.getCurrentToken(token1, token2), alive, stickEndX);
    }

    void playerFall(){
        defaultCharacter.fall();
        System.out.println("Fall finished");
    }

    void disableInvertButton(){
        invertPlayerButton.setDisable(true);
    }

    public void updateScore(){
        GameStatistics.updateScore(scoreLabel, hitsPoint);
    }

    public void checkHighScore(){
        if(gameStatistics.checkHighScore() && !GameStatistics.isHighScoreChecked()){
            FadeTransition fadeIn = new FadeTransition(Duration.millis(2000), highScoreText);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);

            FadeTransition fadeOut = new FadeTransition(Duration.millis(2000), highScoreText);
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);

            fadeIn.setOnFinished(event -> {
                fadeOut.play();
                GameStatistics.setHighScoreChecked(true);
            });
            fadeIn.play();
        }
    }

    public void updateTokenCount(){
        tokenLabel.setText(String.valueOf(gameStatistics.getTokens()));
        System.out.println("Number of Tokens"+gameStatistics.getTokens());
    }


    @FXML
    void rotateStick(int num) {
        getStick().rotateStick(extendStickButton, num, this);
    }

    public void setHitPointPosition(HitPoint hitPoint, double x){
        System.out.println("Hitpoint position: "+x);
        hitPoint.setHitPointPosition(x);
    }


    public void switchToGameOverPage() throws IOException {
        System.out.println(tokenLabel.getText());
        System.out.println("Hello");
        System.out.println(gameStatistics.getTokens());
        System.out.println(scoreLabel.getText());
        super.switchToGameOverPage(movableComponents, scoreLabel.getText(), String.valueOf("ada"));
    }

    public void rotateBackground(double shiftDistance) {
        System.out.println("Rotating Background");
        double imgWidth = bgImg1.getFitWidth();

        double img1LagDistance = totalShiftDistance- bgImg1.getX();
        double img2LagDistance = totalShiftDistance- bgImg2.getX();
        double img3LagDistance = totalShiftDistance- bgImg3.getX();
        boolean img1PassedFrame = img1LagDistance > 1440 + shiftDistance;
        boolean img2PassedFrame = img2LagDistance > 1440 + shiftDistance;
        boolean img3PassedFrame = img3LagDistance > 1440 + shiftDistance;
        if(img1PassedFrame) {
            bgImg1.setX(bgImg3.getX()+imgWidth);
            System.out.println("Image 1 shifted\n\n");
        }
        if(img2PassedFrame){
            bgImg2.setX(bgImg1.getX()+imgWidth);
            System.out.println("Image 2 shifted\n\n");
        }
        if(img3PassedFrame) {
            bgImg3.setX(bgImg2.getX()+imgWidth);
            System.out.println("Image 3 shifted\n\n");
        }
    }

    private Scene overlayScene;

    public void enableExtendButton(){
        extendStickButton.setDisable(false);
        extendStickButton.requestFocus();
    }

    public void disableExtendButton(){
        extendStickButton.setDisable(true);
        System.out.println("Extend Button is Disabled");
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

    public static HitPoint getHitPointFront() {
        return hitPointFront;
    }

    public static HitPoint getHitPointBack() {
        return hitPointBack;
    }

    public void setHitsPoint(boolean hitsPoint) {
        this.hitsPoint = hitsPoint;
    }

    public Line getStickLine() {
        return getStick().getStickLine();
    }

    public ImageView getBgImg1() {
        return bgImg1;
    }

    public ImageView getBgImg2() {
        return bgImg2;
    }

    public Stick getStick() {
        if (stick1.isCurrentStick()) {
            return stick1;
        }
        else{
            return stick2;
        }
    }

    public ImageView getImgToken() {
        return imgToken;
    }

    public void setImgToken(ImageView imgToken) {
        this.imgToken = imgToken;
    }

    public ImageView getImgToken2() {
        return imgToken2;
    }

    public void setImgToken2(ImageView imgToken2) {
        this.imgToken2 = imgToken2;
    }

    public Button getPauseButton() {
        return pauseButton;
    }

    public void setPauseButton(Button pauseButton) {
        this.pauseButton = pauseButton;
    }

    public ImageView getImgToken1() {
        return imgToken1;
    }

    public void setImgToken1(ImageView imgToken1) {
        this.imgToken1 = imgToken1;
    }

    public ImageView getImgCharacter() {
        return imgCharacter;
    }

    public void setImgCharacter(ImageView imgCharacter) {
        this.imgCharacter = imgCharacter;
    }

    public Line getStickLine1() {
        return stickLine1;
    }

    public void setStickLine1(Line stickLine1) {
        this.stickLine1 = stickLine1;
    }

    public Line getStickLine2() {
        return stickLine2;
    }

    public void setStickLine2(Line stickLine2) {
        this.stickLine2 = stickLine2;
    }

    public Button getExtendStickButton() {
        return extendStickButton;
    }

    public void setExtendStickButton(Button extendStickButton) {
        this.extendStickButton = extendStickButton;
    }

    public AnchorPane getMovableComponents() {
        return movableComponents;
    }

    public void setMovableComponents(AnchorPane movableComponents) {
        this.movableComponents = movableComponents;
    }

    public AnchorPane getFirstController() {
        return firstController;
    }

    public void setFirstController(AnchorPane firstController) {
        this.firstController = firstController;
    }

    public Button getInvertPlayerButton() {
        return invertPlayerButton;
    }

    public void setInvertPlayerButton(Button invertPlayerButton) {
        this.invertPlayerButton = invertPlayerButton;
    }

    public Rectangle getPlatformRectangle1() {
        return platformRectangle1;
    }

    public void setPlatformRectangle1(Rectangle platformRectangle1) {
        this.platformRectangle1 = platformRectangle1;
    }

    public Rectangle getPlatformRectangle2() {
        return platformRectangle2;
    }

    public void setPlatformRectangle2(Rectangle platformRectangle2) {
        this.platformRectangle2 = platformRectangle2;
    }

    public Rectangle getPlatformRectangle3() {
        return platformRectangle3;
    }

    public void setPlatformRectangle3(Rectangle platformRectangle3) {
        this.platformRectangle3 = platformRectangle3;
    }

    public Rectangle getHitPointRectangle2() {
        return hitPointRectangle2;
    }

    public void setHitPointRectangle2(Rectangle hitPointRectangle2) {
        this.hitPointRectangle2 = hitPointRectangle2;
    }

    public Rectangle getHitPointRectangle1() {
        return hitPointRectangle1;
    }

    public void setHitPointRectangle1(Rectangle hitPointRectangle1) {
        this.hitPointRectangle1 = hitPointRectangle1;
    }

    public Label getScoreLabel() {
        return scoreLabel;
    }

    public void setScoreLabel(Label scoreLabel) {
        this.scoreLabel = scoreLabel;
    }

    public Label getTokenLabel() {
        return tokenLabel;
    }

    public void setTokenLabel(Label tokenLabel) {
        this.tokenLabel = tokenLabel;
    }

    public Label getHighScoreText() {
        return highScoreText;
    }

    public void setHighScoreText(Label highScoreText) {
        this.highScoreText = highScoreText;
    }

    public void setBgImg1(ImageView bgImg1) {
        this.bgImg1 = bgImg1;
    }

    public void setBgImg2(ImageView bgImg2) {
        this.bgImg2 = bgImg2;
    }

    public ImageView getBgImg3() {
        return bgImg3;
    }

    public void setBgImg3(ImageView bgImg3) {
        this.bgImg3 = bgImg3;
    }

    public Platform getPlatform1() {
        return platform1;
    }

    public void setPlatform1(Platform platform1) {
        this.platform1 = platform1;
    }

    public Platform getPlatform2() {
        return platform2;
    }

    public void setPlatform2(Platform platform2) {
        this.platform2 = platform2;
    }

    public Platform getPlatform3() {
        return platform3;
    }

    public void setPlatform3(Platform platform3) {
        this.platform3 = platform3;
    }

    public static void setHitPointFront(HitPoint hitPointFront) {
        GamePlayController.hitPointFront = hitPointFront;
    }

    public static void setHitPointBack(HitPoint hitPointBack) {
        GamePlayController.hitPointBack = hitPointBack;
    }

    public Stick getStick1() {
        return stick1;
    }

    public void setStick1(Stick stick1) {
        this.stick1 = stick1;
    }

    public Stick getStick2() {
        return stick2;
    }

    public void setStick2(Stick stick2) {
        this.stick2 = stick2;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Token getToken1() {
        return token1;
    }

    public void setToken1(Token token1) {
        this.token1 = token1;
    }

    public Token getToken2() {
        return token2;
    }

    public void setToken2(Token token2) {
        this.token2 = token2;
    }

    public GameMechanics getGameMechanics() {
        return gameMechanics;
    }

    public void setGameMechanics(GameMechanics gameMechanics) {
        this.gameMechanics = gameMechanics;
    }

    public boolean isHitsPoint() {
        return hitsPoint;
    }

    public double getTotalShiftDistance() {
        return totalShiftDistance;
    }

    public void setTotalShiftDistance(double totalShiftDistance) {
        this.totalShiftDistance = totalShiftDistance;
    }

    public DefaultCharacter getDefaultCharacter() {
        return defaultCharacter;
    }

    public void setDefaultCharacter(DefaultCharacter defaultCharacter) {
        this.defaultCharacter = defaultCharacter;
    }

    public GameStatistics getGameStatistics() {
        return gameStatistics;
    }

    public void setGameStatistics(GameStatistics gameStatistics) {
        this.gameStatistics = gameStatistics;
    }

    public ArrayList<Image> getBackgroundList() {
        return backgroundList;
    }

    public void setBackgroundList(ArrayList<Image> backgroundList) {
        this.backgroundList = backgroundList;
    }

    public Scene getOverlayScene() {
        return overlayScene;
    }

    public void setOverlayScene(Scene overlayScene) {
        this.overlayScene = overlayScene;
    }
}

