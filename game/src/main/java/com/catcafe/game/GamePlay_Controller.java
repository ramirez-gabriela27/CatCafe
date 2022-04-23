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
import java.util.HashMap;

public class GamePlay_Controller {
    // Handles TitleBar (minimize and close window)
    //https://mkyong.com/java/how-to-write-an-image-to-file-imageio/
    //File f = new File("../");
    //System.out.println(f.list());


    double x,y;
    Image walkImg;

    PlayableCharacter playableCharacter;
    InGameInteractiveUser user;
    HashMap<Location, Pair<Double, Double>> locations;
    double baristaX = 360.0;
    double baristaY = 360.0;
    public GamePlay_Controller() throws IOException {
        System.out.println(System.getProperty("user.dir"));
        walkImg = new Image(new FileInputStream("game/src/main/resources/assets/characters/KatyChar/Walk.gif" ));
        initializeLocations();
        //TODO make playable character character selected dynamically based on character selection page
        playableCharacter = new PlayableCharacter(Character.ANJALA);
        user = new InGameInteractiveUser(playableCharacter);
        //start game logic
        //https://stackoverflow.com/questions/3489543/how-to-call-a-method-with-a-separate-thread-in-java
        //interactive
        DemoLevel test = new DemoLevel(user, playableCharacter, this);
        Thread t = new Thread(test);
        t.start();
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



    //@FXML
    //private ImageView barista;
    private CharacterView mybarista = new KatyView(new Pair<>(360.0, 360.0));
    @FXML
    private ImageView customer;

    @FXML
    private Button coffee_button;
    @FXML
    private void handleCoffeeAction(ActionEvent event) {
        System.out.println("coffee machine activate...heading to it");
        //path from location, to coffee machine
        walk(Location.COFFEE_MACHINE, mybarista);

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
        walk(Location.MILK_STEAMER, mybarista);


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
        walk(Location.SYRUPS, mybarista);


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
        walk(Location.REGISTER, mybarista);

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
        walk(Location.TRASH, mybarista);
        InGameCommand trashCommand = user.commandOptions.get(4);
        user.getInvoker().addCommand(trashCommand);//adding orderup command to queue
    }
    private void initializeLocations(){
        locations = new  HashMap<Location, Pair<Double, Double>>();
        locations.put(Location.REGISTER, new Pair<Double, Double>(360.0, 360.0));
        locations.put(Location.COFFEE_MACHINE, new Pair<Double, Double>(100.0, 260.0));
        locations.put(Location.MILK_STEAMER, new Pair<Double,Double>(350.0, 260.0));
        locations.put(Location.SYRUPS, new Pair<Double,Double>(250.0, 260.0));
        locations.put(Location.TRASH, new Pair<Double, Double>(450.0, 260.0));
    }
    protected void walk( Location destination, CharacterView character){
        Pair<Double,Double> currentLoc = character.getLocation();
        Double currentX = currentLoc.getKey();
        Double currentY = currentLoc.getValue();
        Pair<Double,Double> newLoc = locations.get(destination);
        Double newX = newLoc.getKey();
        Double newY = newLoc.getValue();
        Polyline myPath = new Polyline();
        myPath.getPoints().addAll(new Double[]{
                currentX, currentY,
                newX, newY
        });
        PathTransition baristaPath = new PathTransition();
        baristaPath.setNode(mybarista.getImageView());
        baristaPath.setPath(myPath);
        baristaPath.setDuration(Duration.seconds(3));
        if(newX - currentX <0){
            character.setWalkingCarryRight();
        }
        else{
            character.setWalkingCarryRight();
        }
        baristaPath.play();
        character.setFrontImage();
        character.setLocation(newLoc);
    }
}