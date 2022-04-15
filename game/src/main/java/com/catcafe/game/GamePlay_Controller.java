package com.catcafe.game;

import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.nio.file.Path;

public class GamePlay_Controller {
    // Handles TitleBar (minimize and close window)
    double x,y;
    PlayableCharacter playableCharacter;
    InGameInteractiveUser user;
    double baristaX = 360.0;
    double baristaY = 360.0;
    public GamePlay_Controller(){
        playableCharacter = new PlayableCharacter();
        user = new InGameInteractiveUser(playableCharacter);
        //start game logic
        //https://stackoverflow.com/questions/3489543/how-to-call-a-method-with-a-separate-thread-in-java
        //interactive
        DemoLevel test = new DemoLevel(user);
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

    @FXML
    private ImageView barista;
    @FXML
    private ImageView customer;

    @FXML
    private Button coffee_button;
    @FXML
    private void handleCoffeeAction(ActionEvent event) {
        System.out.println("coffee machine activate...heading to it");
        //path from location, to coffee machine
        Polyline pathToCoffee = new Polyline();
        pathToCoffee.getPoints().addAll(new Double[]{
                baristaX, baristaY,
                100.0, 260.0
        });
        PathTransition baristaPath = new PathTransition();
        baristaPath.setNode(barista);
        baristaPath.setPath(pathToCoffee);
        baristaPath.setDuration(Duration.seconds(3));
        baristaPath.play();
        //new position at coffee machine
        baristaX = 100.0;
        baristaY = 260.0;

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
        Polyline pathToCoffee = new Polyline();
        pathToCoffee.getPoints().addAll(new Double[]{
                baristaX, baristaY,
                350.0, 260.0
        });
        PathTransition baristaPath = new PathTransition();
        baristaPath.setNode(barista);
        baristaPath.setPath(pathToCoffee);
        baristaPath.setDuration(Duration.seconds(3));
        baristaPath.play();
        //new position at milk
        baristaX = 350.0;
        baristaY = 260.0;


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
        Polyline pathToCoffee = new Polyline();
        pathToCoffee.getPoints().addAll(new Double[]{
                baristaX, baristaY,
                250.0, 260.0
        });
        PathTransition baristaPath = new PathTransition();
        baristaPath.setNode(barista);
        baristaPath.setPath(pathToCoffee);
        baristaPath.setDuration(Duration.seconds(3));
        baristaPath.play();
        //new position at coffee machine
        baristaX = 250.0;
        baristaY = 260.0;


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
        Polyline pathToCoffee = new Polyline();
        pathToCoffee.getPoints().addAll(new Double[]{
                baristaX, baristaY,
                360.0,360.0
        });
        PathTransition baristaPath = new PathTransition();
        baristaPath.setNode(barista);
        baristaPath.setPath(pathToCoffee);
        baristaPath.setDuration(Duration.seconds(3));
        baristaPath.play();
        //new position at register
        baristaX = 360.0;
        baristaY = 360.0;

        // TODO: cashier check functionality
    }
}