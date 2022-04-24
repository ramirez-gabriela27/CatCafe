package com.catcafe.game;

import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Instant;
import java.util.HashMap;
import java.nio.file.Path;

public class GamePlay_Controller {
    // Handles TitleBar (minimize and close window)
    //https://mkyong.com/java/how-to-write-an-image-to-file-imageio/
    //File f = new File("../");
    //System.out.println(f.list());


    double x,y;
    PlayableCharacter playableCharacter;

    private CharacterView mybarista;
    InGameInteractiveUser user;
    @FXML private ImageView barista;
    HashMap<Location, Pair<Double, Double>> locations;
    HashMap<Integer, Pair<ImageView, CharacterView>> inGameCharacters;
    public GamePlay_Controller() throws IOException {
        initializeLocations();
        playableCharacter = PlayableCharacter.getInstance();
        mybarista= CharacterView.makeCharacter(playableCharacter.getCharacter(),playableCharacter.getId(), new Pair<>(360.0, 360.0));
        inGameCharacters = new HashMap<Integer, Pair<ImageView, CharacterView>>();
        user = new InGameInteractiveUser(playableCharacter);
        //start game logic
        //https://stackoverflow.com/questions/3489543/how-to-call-a-method-with-a-separate-thread-in-java
        //interactive
        DemoLevel test = new DemoLevel(user, playableCharacter, this);
        Thread t = new Thread(test);
        t.start();

    }

    public synchronized void initializeImageViews(ImageView barista){
        inGameCharacters.put(mybarista.getObjectID(), new Pair(barista, mybarista));
        barista.setImage(mybarista.frontImage);

    }
    @FXML
    private Button close_button;
    @FXML //close window with custom button
    protected void handleCloseAction(ActionEvent event){
        System.out.println("Closing window");
        Stage stage = (Stage) close_button.getScene().getWindow();
        stage.close();
    }

    @FXML
    private Button window_button;
    //this one will be disabled for now, we'll see if I can resize -- Gaby

    @FXML
    private Button min_button;
    @FXML //minimize window with custom button
    protected void handleMinAction(ActionEvent event){
        System.out.println("Minimizing window");
        Stage stage = (Stage) min_button.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private Pane top_pane;
    @FXML //drag window with custom bar
    protected void handleClickAction(MouseEvent event){
        Stage stage = (Stage) top_pane.getScene().getWindow();
        x = event.getSceneX();
        y = event.getSceneY();
    }
    @FXML
    protected void handleDragAction(MouseEvent event){
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setX(event.getScreenX()-x);
        stage.setY(event.getScreenY()-y);
    }

    //gameplay controls start here
    @FXML
    private Button startButton;
    @FXML
    private ImageView startButtonPicture;
    @FXML
    private void handleStartAction(ActionEvent event){
        if(inGameCharacters.size() ==0){
            initializeImageViews(barista);
        }
        barista.setImage(mybarista.frontImage);
        startButtonPicture.setOpacity(0);
        startButtonPicture.setDisable(true);
        startButton.setDisable(true);
    }

    @FXML
    private ImageView customer;

    @FXML
    private Button coffee_button;
    @FXML
    private void handleCoffeeAction(ActionEvent event) {
        System.out.println("coffee machine activate...heading to it");
        //path from location, to coffee machine
        //walk(Location.COFFEE_MACHINE, mybarista, barista);
        // TODO: make a simple coffee functionality
        InGameCommand coffeeCommand = user.commandOptions.get(0);
        user.getInvoker().addCommand(coffeeCommand);//adding make coffee command to queue
        //startGame() in GameFlow.java triggers the first command on the queue

    }

    @FXML
    private Button milk_button;
    @FXML
    private void handleMilkAction(ActionEvent event){
        System.out.println("milk activate...heading over");
        //path from location, to milk
        //walk(Location.MILK_STEAMER, mybarista, barista);
        // TODO: make a latte functionality
        InGameCommand milkCommand = user.commandOptions.get(2);
        user.getInvoker().addCommand(milkCommand);//adding milk command to queue
        //startGame() in GameFlow.java triggers the first command on the queue

    }

    @FXML
    private Button syrup_button;
    @FXML //close window with custom button
    protected void handleSyrupAction(ActionEvent event){
        System.out.println("lavender syrup activate...heading to it");
        //path from location, to lavender
        //walk(Location.SYRUPS, mybarista, barista);


        // TODO: add lavender syrup functionality
        InGameCommand syrupCommand = user.commandOptions.get(1);
        user.getInvoker().addCommand(syrupCommand);//adding syrup command to queue
    }

    @FXML
    private Button cash_button;
    @FXML
    protected void handleCashAction(ActionEvent event){
        System.out.println("cash activate...heading to register");

        //path from location to register
        //walk(Location.REGISTER, mybarista,barista);

        // TODO: cashier check functionality
        InGameCommand orderCommand = user.commandOptions.get(3);
        user.getInvoker().addCommand(orderCommand);//adding orderup command to queue
    }
    @FXML
    private Button trash_button;
    @FXML
    protected void handleTrashAction(ActionEvent event){
        System.out.println("Trash activate...heading to trash");
        //path from location to trash
       // walk(Location.TRASH, mybarista, barista);
        InGameCommand trashCommand = user.commandOptions.get(4);
        user.getInvoker().addCommand(trashCommand);//adding trash command to queue
    }
    private void initializeLocations(){
        locations = new  HashMap<Location, Pair<Double, Double>>();
        locations.put(Location.REGISTER, new Pair<Double, Double>(360.0, 360.0));
        locations.put(Location.COFFEE_MACHINE, new Pair<Double, Double>(100.0, 260.0));
        locations.put(Location.MILK_STEAMER, new Pair<Double,Double>(350.0, 260.0));
        locations.put(Location.SYRUPS, new Pair<Double,Double>(250.0, 260.0));
        locations.put(Location.TRASH, new Pair<Double, Double>(450.0, 260.0));
    }
    protected synchronized void walk( Location destination, CharacterView character, ImageView characterImageView){
        //uppack the current location coordinates from the chracter data structure
        Pair<Double,Double> currentLoc = character.getLocation();
        Double currentX = currentLoc.getKey();
        Double currentY = currentLoc.getValue();

        //get coordinates of new location based on enum Location argument
        Pair<Double,Double> newLoc = locations.get(destination);
        Double newX = newLoc.getKey();
        Double newY = newLoc.getValue();

        //update the new location in character data structure
        character.setLocation(newLoc);
        if(newX == currentX && newY == currentY){
            //Do nothing if new location is same as current
            return;
        }
        else if(newX - currentX <0){
            //set the image displayed to be the walking left gif if the character is going left
            characterImageView.setImage(character.getWalkingCarryLeft());
        }
        else{
            //set the image displayed to be the walking right gif if the character is going right
            characterImageView.setImage(character.getWalkingCarryRight());
        }

        //creating path based on coordinates of current and new locations
        Polyline myPath = new Polyline();
        myPath.getPoints().addAll(new Double[]{
                currentX, currentY,
                newX, newY
        });
        PathTransition walkPath = new PathTransition();

        //character will spend 3 seconds walking to destination walkEndTime is the unix timestamp of when (approx) the
        //walking animation will be done
        int duration = 3;
        long walkEndTime = Instant.now().getEpochSecond() +duration;

        //setting up walking animation
        walkPath.setNode(characterImageView);
        walkPath.setPath(myPath);
        walkPath.setDuration(Duration.seconds(duration));


        //https://www.demo2s.com/java/javafx-pathtransition-setonfinished-eventhandler-actionevent-value.html
        //https://stackoverflow.com/questions/37752207/javafx-wait-for-animation-method-to-finish-before-going-to-next-method
        //walkPath.setOnFinished((ActionEvent actionEvent) -> {characterImageView.setImage(character.getFrontImage());});

        //plays the walking animation
        walkPath.play();

        //This is to force waiting until the walking animation is finished to change back to the
        //front facing image and also to make sure that this method does not exit until the walking
        // animation is done (if we do itll execute the next command and that's how we get
        // teleporting)
        while(Instant.now().getEpochSecond() < walkEndTime){
            assert(true);
        }

        //change image back to the fron facing image
        characterImageView.setImage(character.getFrontImage());
    }
    @FXML
    public void updateLocation(int objectId, Location location){
        if(!inGameCharacters.containsKey(objectId)){
            throw new IllegalArgumentException("Invalid ID" + objectId+ "Current: " + inGameCharacters.keySet());
        }
        else {
            Pair<ImageView, CharacterView> charInfo = inGameCharacters.get(objectId);
            if(charInfo.getKey() == null){
                System.out.println("Null");
            }
            walk(location, charInfo.getValue(), charInfo.getKey());
        }

    }
}