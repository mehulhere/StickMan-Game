package com.example.first_fx_project;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Objects;

public class ThemesController extends SceneController{

    @FXML
    private ImageView themeImageView;

    @FXML
    private Button themeSwitchLeft;

    @FXML
    private Button themeSwitchRight;

    private ArrayList<Image> themeList = new ArrayList<Image>();

    private static int themeIndex = 0;

    public void initialize(){
        themeList.add(new Image(Objects.requireNonNull(getClass().getResource("assets/theme_City.png")).toExternalForm()));
        themeList.add(new Image(Objects.requireNonNull(getClass().getResource("assets/theme_Mountains.png")).toExternalForm()));
        themeList.add(new Image(Objects.requireNonNull(getClass().getResource("assets/theme_Dungeon.png")).toExternalForm()));
        themeImageView.setImage(themeList.get(themeIndex));
    }

    public void changeThemeRight(){
        if(themeIndex < themeList.size()-1){
            themeIndex++;
            themeImageView.setImage(themeList.get(themeIndex));
        }
    }

    public void changeThemeLeft(){
        if(themeIndex > 0){
            themeIndex--;
            themeImageView.setImage(themeList.get(themeIndex));
        }
    }

    public static int getThemeIndex() {
        return themeIndex;
    }

    public ImageView getThemeImageView() {
        return themeImageView;
    }

    public void setThemeImageView(ImageView themeImageView) {
        this.themeImageView = themeImageView;
    }

    public Button getThemeSwitchLeft() {
        return themeSwitchLeft;
    }

    public void setThemeSwitchLeft(Button themeSwitchLeft) {
        this.themeSwitchLeft = themeSwitchLeft;
    }

    public Button getThemeSwitchRight() {
        return themeSwitchRight;
    }

    public void setThemeSwitchRight(Button themeSwitchRight) {
        this.themeSwitchRight = themeSwitchRight;
    }

    public ArrayList<Image> getThemeList() {
        return themeList;
    }

    public void setThemeList(ArrayList<Image> themeList) {
        this.themeList = themeList;
    }

    public static void setThemeIndex(int themeIndex) {
        ThemesController.themeIndex = themeIndex;
    }
}
