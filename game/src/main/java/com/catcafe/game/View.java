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

public abstract class View{
    int objectID;
    Double x;
    Double y;

    public int getObjectID() {
        return objectID;
    }

    public Pair<Double, Double> getLocation() {
        return new Pair<>(x, y);
    }

    public void setLocation(Pair<Double, Double> newLoc) {
        x = newLoc.getKey();
        ;
        y = newLoc.getValue();
    }
}

abstract class CharacterView extends View {
    Image frontImage;
    Image walkingCarryRight;
    Image walkingCarryLeft;
    Image walkingRight;
    Image walkingLeft;
    Image frontImageCoffee;
    Image walkingCarryRightCoffee;
    Image walkingCarryLeftCoffee;
    Image frontImageLatte;
    Image walkingCarryRightLatte;
    Image walkingCarryLeftLatte;
    Image frontImageLavLatte;
    Image walkingCarryRightLavLatte;
    Image walkingCarryLeftLavLatte;
    Image frontImageLavCoffee;
    Image walkingCarryRightLavCoffee;
    Image walkingCarryLeftLavCoffee;
    static Image noImage;

    public CharacterView(int objectId, Pair<Double, Double> initialLocation) {
        this.objectID = objectId;
        x = initialLocation.getKey();
        y = initialLocation.getValue();
        try {
            noImage = new Image(new FileInputStream("src/main/resources/assets/characters/empty.png" ));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Image getNoImg(){
        return noImage;
    }
    public Image getWalkingCarryRight() {
        return walkingCarryRight;
    }

    public Image getWalkingCarryLeft() {
        return walkingCarryLeft;
    }

    public Image getWalkingLeft() {
        return walkingLeft;
    }

    public Image getWalkingRight() {
        return walkingRight;
    }

    public Image getFrontImage() {
        return frontImage;
    }

    public Image getFrontImageCoffee() {
        return frontImageCoffee;
    }

    public Image getWalkingCarryLeftCoffee() {
        return walkingCarryLeftCoffee;
    }
    public Image getWalkingCarryRightCoffee(){
        return walkingCarryRightCoffee;
    }

    public Image getFrontImageLatte() {
        return frontImageLatte;
    }

    public Image getWalkingCarryLeftLatte() {
        return walkingCarryLeftLatte;
    }

    public Image getWalkingCarryRightLatte() {
        return walkingCarryRightLatte;
    }

    public Image getFrontImageLavCoffee() {
        return frontImageLavCoffee;
    }

    public Image getWalkingCarryLeftLavCoffee() {
        return walkingCarryLeftLavCoffee;
    }

    public Image getWalkingCarryRightLavCoffee() {
        return walkingCarryRightLavCoffee;
    }

    public Image getFrontImageLavLatte() {
        return frontImageLavLatte;
    }

    public Image getWalkingCarryLeftLavLatte() {
        return walkingCarryLeftLavLatte;
    }

    public Image getWalkingCarryRightLavLatte() {
        return walkingCarryRightLavLatte;
    }

    public static CharacterView makeCharacter(Character choice, int objectID, Pair<Double, Double> initialLocation) throws IOException {
        switch (choice) {
            case ANJALA -> {
                return new AnjalaView(objectID, initialLocation);
            }
            case EMMA -> {
                return new EmmaView(objectID, initialLocation);
            }
            case GABY -> {
                return new GabyView(objectID, initialLocation);
            }
            case KATY -> {
                return new KatyView(objectID, initialLocation);
            }
            default -> throw new IllegalArgumentException("Illegal character choice.");
        }
    }
}

class AnjalaView extends CharacterView{
    public AnjalaView(int objectId,Pair<Double, Double>initialLocation) throws IOException {
        super(objectId,initialLocation);
        frontImage = new Image(new FileInputStream("src/main/resources/assets/characters/AnjalaChar/Anjala.png" ));
        walkingCarryLeft = new Image(new FileInputStream("src/main/resources/assets/characters/AnjalaChar/WalkCarryLeft.gif" ));
        walkingCarryRight = new Image(new FileInputStream("src/main/resources/assets/characters/AnjalaChar/WalkCarryRight.gif" ));
        walkingLeft = new Image(new FileInputStream("src/main/resources/assets/characters/AnjalaChar/WalkLeft.gif" ));
        walkingRight = new Image(new FileInputStream("src/main/resources/assets/characters/AnjalaChar/WalkRight.gif" ));
        walkingCarryLeftCoffee = new Image(new FileInputStream("src/main/resources/assets/characters/AnjalaChar/WalkCarryLeftCoffee.gif" ));
        walkingCarryRightCoffee = new Image(new FileInputStream("src/main/resources/assets/characters/AnjalaChar/WalkCarryRightCoffee.gif" ));
        //replace these with the real gifs when done
        walkingCarryLeftLatte = new Image(new FileInputStream("src/main/resources/assets/characters/AnjalaChar/WalkCarryLeftLatte.gif" ));
        walkingCarryRightLatte = new Image(new FileInputStream("src/main/resources/assets/characters/AnjalaChar/WalkCarryRightLatte.gif" ));
        walkingCarryLeftLavCoffee = new Image(new FileInputStream("src/main/resources/assets/characters/AnjalaChar/WalkCarryLeftLavCoffee.gif" ));
        walkingCarryRightLavCoffee = new Image(new FileInputStream("src/main/resources/assets/characters/AnjalaChar/WalkCarryRightLavCoffee.gif" ));
        walkingCarryLeftLavLatte = new Image(new FileInputStream("src/main/resources/assets/characters/AnjalaChar/WalkCarryLeftLavLatte.gif" ));
        walkingCarryRightLavLatte = new Image(new FileInputStream("src/main/resources/assets/characters/AnjalaChar/WalkCarryRightLavLatte.gif" ));

        frontImageCoffee = new Image(new FileInputStream("src/main/resources/assets/characters/AnjalaChar/AnjalaCoffee.png" ));
        frontImageLatte = new Image(new FileInputStream("src/main/resources/assets/characters/AnjalaChar/AnjalaLatte.png" ));
        frontImageLavCoffee = new Image(new FileInputStream("src/main/resources/assets/characters/AnjalaChar/AnjalaLavCoffee.png" ));
        frontImageLavLatte = new Image(new FileInputStream("src/main/resources/assets/characters/AnjalaChar/AnjalaLavLatte.png" ));
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
        walkingCarryLeftCoffee = new Image(new FileInputStream("src/main/resources/assets/characters/EmmaChar/WalkCarryLeftCoffee.gif" ));
        walkingCarryRightCoffee = new Image(new FileInputStream("src/main/resources/assets/characters/EmmaChar/WalkCarryRightCoffee.gif" ));
        //replace these with the real gifs when done
        walkingCarryLeftLatte = new Image(new FileInputStream("src/main/resources/assets/characters/EmmaChar/WalkCarryLeft.gif" ));
        walkingCarryRightLatte = new Image(new FileInputStream("src/main/resources/assets/characters/EmmaChar/WalkCarryRight.gif" ));
        walkingCarryLeftLavCoffee = new Image(new FileInputStream("src/main/resources/assets/characters/EmmaChar/WalkCarryLeft.gif" ));
        walkingCarryRightLavCoffee = new Image(new FileInputStream("src/main/resources/assets/characters/EmmaChar/WalkCarryRight.gif" ));
        walkingCarryLeftLavLatte = new Image(new FileInputStream("src/main/resources/assets/characters/EmmaChar/WalkCarryLeft.gif" ));
        walkingCarryRightLavLatte = new Image(new FileInputStream("src/main/resources/assets/characters/EmmaChar/WalkCarryRight.gif" ));

        frontImageCoffee = new Image(new FileInputStream("src/main/resources/assets/characters/EmmaChar/EmmaCoffee.png" ));
        frontImageLatte = new Image(new FileInputStream("src/main/resources/assets/characters/EmmaChar/Emma.png" ));
        frontImageLavCoffee = new Image(new FileInputStream("src/main/resources/assets/characters/EmmaChar/Emma.png" ));
        frontImageLavLatte = new Image(new FileInputStream("src/main/resources/assets/characters/EmmaChar/Emma.png" ));
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
        //replace these with the real gifs when done
        walkingCarryLeftCoffee = new Image(new FileInputStream("src/main/resources/assets/characters/GabyChar/WalkCarryLeft.gif" ));
        walkingCarryRightCoffee = new Image(new FileInputStream("src/main/resources/assets/characters/GabyChar/WalkCarryRight.gif" ));
        walkingCarryLeftLatte = new Image(new FileInputStream("src/main/resources/assets/characters/GabyChar/WalkCarryLeft.gif" ));
        walkingCarryRightLatte = new Image(new FileInputStream("src/main/resources/assets/characters/GabyChar/WalkCarryRight.gif" ));
        walkingCarryLeftLavCoffee = new Image(new FileInputStream("src/main/resources/assets/characters/GabyChar/WalkCarryLeft.gif" ));
        walkingCarryRightLavCoffee = new Image(new FileInputStream("src/main/resources/assets/characters/GabyChar/WalkCarryRight.gif" ));
        walkingCarryLeftLavLatte = new Image(new FileInputStream("src/main/resources/assets/characters/GabyChar/WalkCarryLeft.gif" ));
        walkingCarryRightLavLatte = new Image(new FileInputStream("src/main/resources/assets/characters/GabyChar/WalkCarryRight.gif" ));

        frontImageCoffee = new Image(new FileInputStream("src/main/resources/assets/characters/GabyChar/Gaby.png" ));
        frontImageLatte = new Image(new FileInputStream("src/main/resources/assets/characters/GabyChar/Gaby.png" ));
        frontImageLavCoffee = new Image(new FileInputStream("src/main/resources/assets/characters/GabyChar/Gaby.png" ));
        frontImageLavLatte = new Image(new FileInputStream("src/main/resources/assets/characters/GabyChar/Gaby.png" ));
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
        //replace these with the real gifs when done
        walkingCarryLeftLatte = new Image(new FileInputStream("src/main/resources/assets/characters/KatyChar/WalkCarryLeft.gif" ));
        walkingCarryRightLatte = new Image(new FileInputStream("src/main/resources/assets/characters/KatyChar/WalkCarryRight.gif" ));
        walkingCarryLeftCoffee = new Image(new FileInputStream("src/main/resources/assets/characters/KatyChar/WalkCarryLeft.gif" ));
        walkingCarryRightCoffee = new Image(new FileInputStream("src/main/resources/assets/characters/KatyChar/WalkCarryRight.gif" ));
        walkingCarryRightLavCoffee = new Image(new FileInputStream("src/main/resources/assets/characters/KatyChar/WalkCarryRight.gif" ));
        walkingCarryRightLavCoffee = new Image(new FileInputStream("src/main/resources/assets/characters/KatyChar/WalkCarryRight.gif" ));
        walkingCarryLeftLavLatte = new Image(new FileInputStream("src/main/resources/assets/characters/KatyChar/WalkCarryLeft.gif" ));
        walkingCarryRightLavLatte = new Image(new FileInputStream("src/main/resources/assets/characters/KatyChar/WalkCarryRight.gif" ));
        frontImageCoffee = new Image(new FileInputStream("src/main/resources/assets/characters/KatyChar/Katy.png" ));
        frontImageLatte = new Image(new FileInputStream("src/main/resources/assets/characters/KatyChar/Katy.png" ));
        frontImageLavCoffee = new Image(new FileInputStream("src/main/resources/assets/characters/KatyChar/Katy.png" ));
        frontImageLavLatte = new Image(new FileInputStream("src/main/resources/assets/characters/KatyChar/Katy.png" ));
    }
}

class DrinkView extends View{
    Image cupImage;
    Image thoughtBubbleImage;

    public DrinkView(int objectId, Pair<Double, Double> initialLocation) {
        this.objectID = objectId;
        x = initialLocation.getKey();
        y = initialLocation.getValue();
    }

    public void setCupImage(Image cupImage) throws IOException {
        this.cupImage = cupImage;
    }

    public Image getCupImage(){
        return cupImage;
    }

    public Image getThoughtBubbleImage(){
        return thoughtBubbleImage;
    }

    public static DrinkView makeDrink(Requestable choice, int objectID, Pair<Double, Double> initialLocation) throws IOException {
        switch (choice) {
            case COFFEE -> {
                return new CoffeeView(objectID, initialLocation);
            }
            case LATTE -> {
                return new LatteView(objectID, initialLocation);
            }
            case SYRUP_COFFEE -> {
                return new CoffeeSyrupView(objectID, initialLocation);
            }
            case SYRUP_LATTE -> {
                return new LavLatteView(objectID, initialLocation);
            }
            case NONE -> {
                return new NoneDrinkView(objectID, initialLocation);
            }
            default -> throw new IllegalArgumentException("Illegal drink choice.");
        }
    }
}

class CupView extends DrinkView{
    public CupView(int objectId,Pair<Double, Double>initialLocation) throws IOException {
        super(objectId,initialLocation);
        cupImage = new Image(new FileInputStream("src/main/resources/assets/beverages/cup.png" ));
        thoughtBubbleImage = new Image(new FileInputStream("src/main/resources/assets/requests/coffeeRequest.png" ));
    }
}

class CoffeeView extends DrinkView{
    public CoffeeView(int objectId,Pair<Double, Double>initialLocation) throws IOException {
        super(objectId,initialLocation);
        cupImage = new Image(new FileInputStream("src/main/resources/assets/beverages/coffee2.png" ));
        thoughtBubbleImage = new Image(new FileInputStream("src/main/resources/assets/requests/coffeeRequest.png" ));
    }
}

class LatteView extends DrinkView{
    public LatteView(int objectId,Pair<Double, Double>initialLocation) throws IOException {
        super(objectId,initialLocation);
        cupImage = new Image(new FileInputStream("src/main/resources/assets/beverages/latte.png" ));
        thoughtBubbleImage = new Image(new FileInputStream("src/main/resources/assets/requests/latteRequest.png" ));
    }
}

class CoffeeSyrupView extends DrinkView{
    public CoffeeSyrupView(int objectId,Pair<Double, Double>initialLocation) throws IOException {
        super(objectId,initialLocation);
        cupImage = new Image(new FileInputStream("src/main/resources/assets/beverages/lavendercoffee2.png" ));
        thoughtBubbleImage = new Image(new FileInputStream("src/main/resources/assets/requests/lavendercoffee2.png" ));
    }
}

class LavLatteView extends DrinkView{
    public LavLatteView(int objectId,Pair<Double, Double>initialLocation) throws IOException {
        super(objectId,initialLocation);
        cupImage = new Image(new FileInputStream("src/main/resources/assets/beverages/lavenderLatte.png" ));
        thoughtBubbleImage = new Image(new FileInputStream("src/main/resources/assets/requests/lavLatteRequest.png" ));
    }
}

class NoneDrinkView extends DrinkView{
    public NoneDrinkView(int objectId,Pair<Double, Double>initialLocation) throws IOException {
        super(objectId,initialLocation);
        cupImage = new Image(new FileInputStream("src/main/resources/assets/characters/empty.png" ));
        thoughtBubbleImage = new Image(new FileInputStream("src/main/resources/assets/requests/requestCloud.png" ));
    }
}
