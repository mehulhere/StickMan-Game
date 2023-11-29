package com.example.first_fx_project;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class pausePageController extends SceneController{

        @FXML
        private Button resumeButton;

        public void initialize(){
            resumeButton.requestFocus();
        }

}
