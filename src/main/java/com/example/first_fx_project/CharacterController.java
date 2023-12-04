package com.example.first_fx_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
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
    private ImageView imgDefaultCharacter;

    @FXML
    private ToggleGroup characterToggleGroup;

    private static int characterIndex = 0;

    private static int numChars = 7;

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
}