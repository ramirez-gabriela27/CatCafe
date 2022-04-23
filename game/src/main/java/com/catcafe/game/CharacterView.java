package com.catcafe.game;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Pair;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class CharacterView{
    Double x;
    Double y;
    ImageView imageView;
    Image frontImage;
    Image walkingCarryRight;
    Image walkingCarryLeft;
    Image walkingRight;
    Image walkingLeft;
    public CharacterView(Pair<Double, Double>initialLocation){
        x = initialLocation.getKey();
        y = initialLocation.getValue();
    }
    public Pair<Double, Double> getLocation(){
        return new Pair<>(x,y);
    }
    public void setWalkingCarryRight(){
        imageView.setImage(walkingCarryRight);
    }
    public void setWalkingCarryLeft(){
        imageView.setImage(walkingCarryLeft);
    }
    public void setWalkingLeft(){
        imageView.setImage(walkingLeft);
    }
    public void setWalkingRight(){
        imageView.setImage(walkingCarryRight);
    }
    public void setFrontImage(){
        imageView.setImage(frontImage);
    }
    public ImageView getImageView(){
        return imageView;
    }
    public void setLocation(Pair<Double, Double> newLoc){
        x = newLoc.getKey();;
        y = newLoc.getValue();
    }
}

class KatyView extends CharacterView{
    public KatyView(Pair<Double, Double>initialLocation) throws IOException {
        super(initialLocation);
        FXMLLoader fxmlLoader = new FXMLLoader(CatCafe_App.class.getResource("gameplay-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        frontImage = new Image(new FileInputStream("game/src/main/resources/assets/characters/KatyChar/Katy.png" ));
        walkingCarryLeft = new Image(new FileInputStream("game/src/main/resources/assets/characters/KatyChar/WalkCarry.gif" ));
        walkingLeft = new Image(new FileInputStream("game/src/main/resources/assets/characters/KatyChar/Walk.gif" ));
        //this.imageView = (ImageView) gameplay_scene.lookup("#barista");
        //imageView.setImage(frontImage);
    }
}
