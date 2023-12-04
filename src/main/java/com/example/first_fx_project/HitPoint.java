package com.example.first_fx_project;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class HitPoint {
    private static int width = 20;
    private static double height = 555;

    private Rectangle hitPointRectangle;
    public HitPoint(Rectangle hitPointRectangle, double newHitPointStartX){
        this.hitPointRectangle = hitPointRectangle;
        hitPointRectangle.setWidth(width);
        hitPointRectangle.setY(height);
        hitPointRectangle.setX(newHitPointStartX);
    }

    public static int getWidth() {
        return width;
    }

    public void setHitPointPosition(double x){
        hitPointRectangle.setX(x);
    }

    public double getHitPointPosition(){
        return hitPointRectangle.getX();
    }

    public void changeColor(boolean hit){
        if(hit){
            this.hitPointRectangle.setFill(Color.LAWNGREEN);
        }
        else{
            this.hitPointRectangle.setFill(Color.RED);
        }
    }

    public void isVisible(boolean visible){
        hitPointRectangle.setVisible(visible);
    }
}
