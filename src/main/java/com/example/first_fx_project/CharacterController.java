package com.example.first_fx_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;

public class CharacterController extends SceneController{

    @FXML
    private ToggleButton characterTB0;

    @FXML
    private ToggleButton characterTB1;

    @FXML
    private ToggleButton characterTB2;

    @FXML
    private ToggleButton characterTB3;

    @FXML
    private ToggleButton characterTB4;

    @FXML
    private ToggleButton characterTB5;

    @FXML
    private ToggleButton characterTB6;

    @FXML
    private ToggleButton characterTB7;

    @FXML
    private ToggleButton characterTB8;

    @FXML
    private ImageView imgDefaultCharacter;

    @FXML
    private ToggleGroup characterToggleGroup;

    private static int characterIndex = 2;

    private static int numChars = 9;

    public static int getCharacterIndex() {
        return characterIndex;
    }

    @FXML
    private void handleToggle(ActionEvent event) {
        ToggleButton sourceButton = (ToggleButton) event.getSource();
        for(int i = 0; i < numChars; i++){
            if(sourceButton.getId().equals("characterTB" + i)){
                characterIndex = i;
                break;
            }
        }
    }

    public ToggleButton getCharacterTB0() {
        return characterTB0;
    }

    public void setCharacterTB0(ToggleButton characterTB0) {
        this.characterTB0 = characterTB0;
    }

    public ToggleButton getCharacterTB1() {
        return characterTB1;
    }

    public void setCharacterTB1(ToggleButton characterTB1) {
        this.characterTB1 = characterTB1;
    }

    public ToggleButton getCharacterTB2() {
        return characterTB2;
    }

    public void setCharacterTB2(ToggleButton characterTB2) {
        this.characterTB2 = characterTB2;
    }

    public ToggleButton getCharacterTB3() {
        return characterTB3;
    }

    public void setCharacterTB3(ToggleButton characterTB3) {
        this.characterTB3 = characterTB3;
    }

    public ToggleButton getCharacterTB4() {
        return characterTB4;
    }

    public void setCharacterTB4(ToggleButton characterTB4) {
        this.characterTB4 = characterTB4;
    }

    public ToggleButton getCharacterTB5() {
        return characterTB5;
    }

    public void setCharacterTB5(ToggleButton characterTB5) {
        this.characterTB5 = characterTB5;
    }

    public ToggleButton getCharacterTB6() {
        return characterTB6;
    }

    public void setCharacterTB6(ToggleButton characterTB6) {
        this.characterTB6 = characterTB6;
    }

    public ToggleButton getCharacterTB7() {
        return characterTB7;
    }

    public void setCharacterTB7(ToggleButton characterTB7) {
        this.characterTB7 = characterTB7;
    }

    public ToggleButton getCharacterTB8() {
        return characterTB8;
    }

    public void setCharacterTB8(ToggleButton characterTB8) {
        this.characterTB8 = characterTB8;
    }

    public ImageView getImgDefaultCharacter() {
        return imgDefaultCharacter;
    }

    public void setImgDefaultCharacter(ImageView imgDefaultCharacter) {
        this.imgDefaultCharacter = imgDefaultCharacter;
    }

    public ToggleGroup getCharacterToggleGroup() {
        return characterToggleGroup;
    }

    public void setCharacterToggleGroup(ToggleGroup characterToggleGroup) {
        this.characterToggleGroup = characterToggleGroup;
    }

    public static void setCharacterIndex(int characterIndex) {
        CharacterController.characterIndex = characterIndex;
    }

    public static int getNumChars() {
        return numChars;
    }

    public static void setNumChars(int numChars) {
        CharacterController.numChars = numChars;
    }
}