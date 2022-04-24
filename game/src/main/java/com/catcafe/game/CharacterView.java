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
    int objectID;
    Double x;
    Double y;
    Image frontImage;
    Image walkingCarryRight;
    Image walkingCarryLeft;
    Image walkingRight;
    Image walkingLeft;
    static Image noImage;
    public CharacterView(int objectId, Pair<Double, Double>initialLocation){
        this.objectID = objectId;
        x = initialLocation.getKey();
        y = initialLocation.getValue();
        try {
            noImage = new Image(new FileInputStream("src/main/resources/assets/characters/empty.png" ));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public int getObjectID(){
        return objectID;
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
    public static CharacterView makeCharacter(Character choice, int objectID,Pair<Double, Double> initialLocation ) throws IOException {
        switch (choice){
            case ANJALA -> {
                return new AnjalaView(objectID, initialLocation);
            }
            case EMMA ->{
            return new EmmaView(objectID, initialLocation);
            }
            case GABY ->{
                return new GabyView(objectID, initialLocation);
            }
            case KATY ->{
                return new KatyView(objectID, initialLocation);
            }
            default -> throw new IllegalArgumentException("Illegal character choice.");
        }
    }
    public static Image getNoImg(){
        return noImage;
    }
}

class AnjalaView extends CharacterView{
    public AnjalaView(int objectId,Pair<Double, Double>initialLocation) throws IOException {
        super(objectId,initialLocation);
        System.out.print(System.getProperty("user.dir"));
        frontImage = new Image(new FileInputStream("src/main/resources/assets/characters/AnjalaChar/Anjala.png" ));
        walkingCarryLeft = new Image(new FileInputStream("src/main/resources/assets/characters/AnjalaChar/WalkCarryLeft.gif" ));
        walkingCarryRight = new Image(new FileInputStream("src/main/resources/assets/characters/AnjalaChar/WalkCarryRight.gif" ));
        walkingLeft = new Image(new FileInputStream("src/main/resources/assets/characters/AnjalaChar/WalkLeft.gif" ));
        walkingRight = new Image(new FileInputStream("src/main/resources/assets/characters/AnjalaChar/WalkRight.gif" ));
    }
}
class EmmaView extends CharacterView{
    public EmmaView(int objectId,Pair<Double, Double>initialLocation) throws IOException {
        super(objectId,initialLocation);
        frontImage = new Image(new FileInputStream("src/main/resources/assets/characters/EmmaChar/Emma.png" ));
        walkingCarryLeft = new Image(new FileInputStream("src/main/resources/assets/characters/EmmaChar/WalkCarryLeft.gif" ));
        walkingCarryRight = new Image(new FileInputStream("src/main/resources/assets/characters/EmmaChar/WalkCarryRight.gif" ));
        walkingLeft = new Image(new FileInputStream("src/main/resources/assets/characters/EmmaChar/WalkLeft.gif" ));
        walkingRight = new Image(new FileInputStream("src/main/resources/assets/characters/EmmaChar/WalkRight.gif" ));
    }
}
class GabyView extends CharacterView{
    public GabyView(int objectId,Pair<Double, Double>initialLocation) throws IOException {
        super(objectId,initialLocation);
        frontImage = new Image(new FileInputStream("src/main/resources/assets/characters/GabyChar/Gaby.png" ));
        walkingCarryLeft = new Image(new FileInputStream("src/main/resources/assets/characters/GabyChar/WalkCarryLeft.gif" ));
        walkingCarryRight = new Image(new FileInputStream("src/main/resources/assets/characters/GabyChar/WalkCarryRight.gif" ));
        walkingLeft = new Image(new FileInputStream("src/main/resources/assets/characters/GabyChar/WalkLeft.gif" ));
        walkingRight = new Image(new FileInputStream("src/main/resources/assets/characters/GabyChar/WalkRight.gif" ));
    }
}
class KatyView extends CharacterView{
    public KatyView(int objectId,Pair<Double, Double>initialLocation) throws IOException {
        super(objectId,initialLocation);
        frontImage = new Image(new FileInputStream("src/main/resources/assets/characters/KatyChar/Katy.png" ));
        walkingCarryLeft = new Image(new FileInputStream("src/main/resources/assets/characters/KatyChar/WalkCarryLeft.gif" ));
        walkingCarryRight = new Image(new FileInputStream("src/main/resources/assets/characters/KatyChar/WalkCarryRight.gif" ));
        walkingLeft = new Image(new FileInputStream("src/main/resources/assets/characters/KatyChar/WalkLeft.gif" ));
        walkingRight = new Image(new FileInputStream("src/main/resources/assets/characters/KatyChar/WalkRight.gif" ));
    }
}
