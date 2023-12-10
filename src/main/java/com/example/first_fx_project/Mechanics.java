package com.example.first_fx_project;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public interface Mechanics {
    void changeScene(Platform platform2, AnchorPane movableComponents, double shiftDistance, Button extendStickButton);
    void checkCollision(Line stickLine, Rectangle platformRectangle, Platform platform2, ImageView playerImage);
}
