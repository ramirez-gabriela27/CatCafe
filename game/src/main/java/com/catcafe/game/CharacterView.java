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
    public Image getWalkingCarryRight(){
        return walkingCarryRight;
    }
    public Image getWalkingCarryLeft(){
        return walkingCarryLeft;
    }
    public Image getWalkingLeft(){
        return walkingLeft;
    }
    public Image getWalkingRight(){
        return walkingRight;
    }
    public Image getFrontImage(){
        return frontImage;
    }
    public void setLocation(Pair<Double, Double> newLoc){
        x = newLoc.getKey();;
        y = newLoc.getValue();
    }
}

class AnjalaView extends CharacterView{
    public AnjalaView(Pair<Double, Double>initialLocation) throws IOException {
        super(initialLocation);
        frontImage = new Image(new FileInputStream("game/src/main/resources/assets/characters/AnjalaChar/Anjala.png" ));
        walkingCarryLeft = new Image(new FileInputStream("game/src/main/resources/assets/characters/AnjalaChar/WalkCarryLeft.gif" ));
        walkingCarryRight = new Image(new FileInputStream("game/src/main/resources/assets/characters/AnjalaChar/WalkCarryRight.gif" ));
        walkingLeft = new Image(new FileInputStream("game/src/main/resources/assets/characters/AnjalaChar/WalkLeft.gif" ));
        walkingRight = new Image(new FileInputStream("game/src/main/resources/assets/characters/AnjalaChar/WalkRight.gif" ));
    }
}
class EmmaView extends CharacterView{
    public EmmaView(Pair<Double, Double>initialLocation) throws IOException {
        super(initialLocation);
        frontImage = new Image(new FileInputStream("game/src/main/resources/assets/characters/EmmaChar/Emma.png" ));
        walkingCarryLeft = new Image(new FileInputStream("game/src/main/resources/assets/characters/EmmaChar/WalkCarryLeft.gif" ));
        walkingCarryRight = new Image(new FileInputStream("game/src/main/resources/assets/characters/EmmaChar/WalkCarryRight.gif" ));
        walkingLeft = new Image(new FileInputStream("game/src/main/resources/assets/characters/EmmaChar/WalkLeft.gif" ));
        walkingRight = new Image(new FileInputStream("game/src/main/resources/assets/characters/EmmaChar/WalkRight.gif" ));
    }
}
class GabyView extends CharacterView{
    public GabyView(Pair<Double, Double>initialLocation) throws IOException {
        super(initialLocation);
        frontImage = new Image(new FileInputStream("game/src/main/resources/assets/characters/GabyChar/Gaby.png" ));
        walkingCarryLeft = new Image(new FileInputStream("game/src/main/resources/assets/characters/GabyChar/WalkCarryLeft.gif" ));
        walkingCarryRight = new Image(new FileInputStream("game/src/main/resources/assets/characters/GabyChar/WalkCarryRight.gif" ));
        walkingLeft = new Image(new FileInputStream("game/src/main/resources/assets/characters/GabyChar/WalkLeft.gif" ));
        walkingRight = new Image(new FileInputStream("game/src/main/resources/assets/characters/GabyChar/WalkRight.gif" ));
    }
}
class KatyView extends CharacterView{
    public KatyView(Pair<Double, Double>initialLocation) throws IOException {
        super(initialLocation);
        frontImage = new Image(new FileInputStream("game/src/main/resources/assets/characters/KatyChar/Katy.png" ));
        walkingCarryLeft = new Image(new FileInputStream("game/src/main/resources/assets/characters/KatyChar/WalkCarryLeft.gif" ));
        walkingCarryRight = new Image(new FileInputStream("game/src/main/resources/assets/characters/KatyChar/WalkCarryRight.gif" ));
        walkingLeft = new Image(new FileInputStream("game/src/main/resources/assets/characters/KatyChar/WalkLeft.gif" ));
        walkingRight = new Image(new FileInputStream("game/src/main/resources/assets/characters/KatyChar/WalkRight.gif" ));
    }
}
