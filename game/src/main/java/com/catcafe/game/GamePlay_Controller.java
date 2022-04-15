package com.catcafe.game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GamePlay_Controller {
    // Handles TitleBar (minimize and close window)
    double x,y;
    InGameInteractiveUser user = new InGameInteractiveUser();


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
    private Button coffee_button;
    @FXML
    private void handleCoffeeAction(ActionEvent event){
        System.out.println("coffee machine activate");
        // TODO: make a simple coffee functionality
        Command coffeeCommand = user.commandOptions.get(0);
        user.invoker.addCommand(coffeeCommand);//adding make coffee command to queue
        //time step in Katy's GameFlow class will trigger command to be made

    }

    @FXML
    private Button milk_button;
    @FXML
    private void handleMilkAction(ActionEvent event){
        System.out.println("milk activate");
        // TODO: make a latte functionality
    }

    @FXML
    private Button syrup_button;
    @FXML //close window with custom button
    protected void handleSyrupAction(ActionEvent event){
        System.out.println("lavender syrup activate");
        // TODO: add lavender syrup functionality
    }

    @FXML
    private Button cash_button;
    @FXML
    protected void handleCashAction(ActionEvent event){
        System.out.println("cash activate");
        // TODO: cashier check functionality
    }
}