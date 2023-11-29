package com.example.first_fx_project;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Token { //In our game, instead of berries we have token class
    private ImageView imgToken;

    public ImageView getImgToken() {
        return imgToken;
    }

    private boolean currentToken;

    public Token(GamePlayController gamePlayController, ImageView imgToken, Rectangle platform1, Rectangle platform2, boolean currentToken){
            this.imgToken = imgToken;
            this.currentToken = currentToken;
            generateToken(platform1, platform2.getX());
            System.out.println(" I AM " + currentToken);
        }

        public void generateToken(Rectangle platformCurrent, double platformTargetX){
            Random random = new Random();
            System.out.println("Generating Token");
            double platformCurrentEndX = platformCurrent.getX()+platformCurrent.getWidth()+2;
            double platformTargetStartX = platformTargetX-2;
            double imageWidth = imgToken.getFitWidth();
            System.out.println("platformCurrentEndX: "+ platformCurrentEndX);
            System.out.println("platformTargetStartX: "+ platformTargetStartX);
            double tokenStartX =random.nextDouble(platformCurrentEndX, platformTargetStartX- imageWidth);
            int opacity = random.nextInt(1,2);
            System.out.println("TokenStartX: "+tokenStartX);
            imgToken.setX(tokenStartX);
            imgToken.setOpacity(opacity);
            if(!currentToken){
                System.out.println("Hello");
                imgToken.setOpacity(0);
            }
        }

        public static void reinitialize(Token token1, Token token2, Rectangle platform1, double platform2X){
        if(token1.currentToken){
            token1.generateToken(platform1, platform2X);
            token2.getImgToken().setOpacity(0);
        }
        else{
            token2.generateToken(platform1, platform2X);
            token1.getImgToken().setOpacity(0);
        }
        }
        public void removeToken() {
            // Code to remove the token
            // This method might involve deleting or hiding the token from the game
        }

    public static void invertTokenConfiguration(Token token1, Token token2) {
        token1.setCurrentToken(!token1.isCurrentToken());
        token2.setCurrentToken(!token1.isCurrentToken()); //! because token1 has changed its boolean value
    }

    public static Token getCurrentToken(Token token1, Token token2){
        if (token1.currentToken){
            return token1;
        }
        else{
            return token2;
        }
    }
    public boolean isCurrentToken() {
        return currentToken;
    }

    public void setCurrentToken(boolean currentToken) {
        this.currentToken = currentToken;
    }
    }

