package com.catcafe.game;
//MVC Pattern
import javafx.animation.*;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;
import javafx.scene.text.Text;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.Instant;
import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;
import java.util.Set;

public class GamePlay_Controller {
    // Handles TitleBar (minimize and close window)
    //https://mkyong.com/java/how-to-write-an-image-to-file-imageio/
    //File f = new File("../");
    //System.out.println(f.list());


    double x, y;
    PlayableCharacter playableCharacter;

    private CharacterView mybarista;
    InGameInteractiveUser user;
    @FXML
    private ImageView barista;
    @FXML
    private Text amountDisplay;
    @FXML
    private ImageView customer1;
    @FXML
    private ImageView customer2;
    @FXML
    private ImageView customer3;
    @FXML
    private ImageView customer4;
    @FXML
    private ImageView line0Heart;
    @FXML
    private ImageView line1Heart;
    @FXML
    private ImageView line2Heart;
    @FXML
    private ImageView line3Heart;
    @FXML
    private Text highScoreText;
    @FXML
    private Text moneyMinus;
    @FXML
    private Text moneyPlus;
    @FXML
    private Text tip;

    //@FXML private ImageView cup;
    //private DrinkView drinkView;
    Level currentLevel;
    HashMap<Location, Pair<Double, Double>> locations;
    HashMap<Location, Boolean> waitingLocations;
    HashMap<Integer, Pair<ImageView, CharacterView>> inGameCharacters;
    LevelName[] gameLevelList = {LevelName.ONE, LevelName.TWO, LevelName.THREE};
    private int levelNum = 0;
    HeartView heartView = new HeartView();

    public GamePlay_Controller() throws IOException {
        playableCharacter = PlayableCharacter.getInstance();
        mybarista = CharacterView.makeCharacter(playableCharacter.getCharacter(), playableCharacter.getId(), new Pair<>(360.0, 360.0));
        inGameCharacters = new HashMap<Integer, Pair<ImageView, CharacterView>>();
        user = new InGameInteractiveUser(playableCharacter);
        initializeLocations();
        waitingLocations = new HashMap<Location, Boolean>();
        //start game logic
        //https://stackoverflow.com/questions/3489543/how-to-call-a-method-with-a-separate-thread-in-java
        //interactive
    }

    public void newGame(LevelName level){
        currentLevel = getLevel(level);
        Thread t = new Thread(currentLevel);
        t.start();
    }

    public Level getLevel(LevelName level){
        switch (level) {
            case ONE -> {
                return new Level1(user, playableCharacter, this);
            }
            case TWO -> {
                return new Level2(user, playableCharacter, this);
            }
            case THREE -> {
                return new Level3(user, playableCharacter, this);
            }
            default -> throw new IllegalArgumentException("Illegal level choice.");
        }
    }

    public synchronized void initializeImageViews() {
        inGameCharacters.put(mybarista.getObjectID(), new Pair(barista, mybarista));
        //https://www.tabnine.com/code/java/methods/javafx.scene.image.ImageView/setVisible
        customer1.setVisible(false);
        customer2.setVisible(false);
        customer3.setVisible(false);
        customer4.setVisible(false);
        moneyMinus.setVisible(false);
        moneyPlus.setVisible(false);
        tip.setOpacity(0.0);
        tip.setVisible(true);
        inGameCharacters.put(-1, new Pair<>(customer1, null));
        inGameCharacters.put(-2, new Pair<>(customer2, null));
        inGameCharacters.put(-3, new Pair<>(customer3, null));
        inGameCharacters.put(-4, new Pair<>(customer4, null));
        waitingLocations.put(Location.WAITING_1, true);
        waitingLocations.put(Location.WAITING_2, true);
        waitingLocations.put(Location.WAITING_3, true);
        waitingLocations.put(Location.WAITING_4, true);
        line0Heart.toFront();
        line1Heart.toFront();
        line2Heart.toFront();
        line3Heart.toFront();
        line0Heart.setVisible(false);
        line1Heart.setVisible(false);
        line2Heart.setVisible(false);
        line3Heart.setVisible(false);
        barista.setImage(mybarista.frontImage);
    }

    @FXML
    private Button close_button;

    @FXML //close window with custom button
    protected void handleCloseAction(ActionEvent event) {
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
    protected void handleMinAction(ActionEvent event) {
        System.out.println("Minimizing window");
        Stage stage = (Stage) min_button.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private Pane top_pane;

    @FXML //drag window with custom bar
    protected void handleClickAction(MouseEvent event) {
        Stage stage = (Stage) top_pane.getScene().getWindow();
        x = event.getSceneX();
        y = event.getSceneY();
    }

    @FXML
    protected void handleDragAction(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);
    }

    //gameplay controls start here
    @FXML
    private Button startButton;
    @FXML

    private ImageView levelScreenPicture;

    private ImageView startButtonPicture;

    @FXML
    private void handleStartAction(ActionEvent event) {
        if (inGameCharacters.size() == 0) {
            initializeImageViews();
        }
        //barista.setImage(mybarista.frontImage);
        amountDisplay.setText("$0.00");
        levelScreenPicture.setOpacity(0);
        levelScreenPicture.setDisable(true);
        startButton.setDisable(true);
        highScoreText.setOpacity(0);
        //start game logic
        //https://stackoverflow.com/questions/3489543/how-to-call-a-method-with-a-separate-thread-in-java
        //interactive
        newGame(gameLevelList[levelNum]);
    }


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
        amountDisplay.setText("$" + Account.getInstance().getAmountString());
        //checkTheLine();
    }

    @FXML
    private Button milk_button;

    @FXML
    private void handleMilkAction(ActionEvent event) {
        System.out.println("milk activate...heading over");
        //path from location, to milk
        //walk(Location.MILK_STEAMER, mybarista, barista);
        // TODO: make a latte functionality
        InGameCommand milkCommand = user.commandOptions.get(2);
        user.getInvoker().addCommand(milkCommand);//adding milk command to queue
        //startGame() in GameFlow.java triggers the first command on the queue
        amountDisplay.setText("$" + Account.getInstance().getAmountString());
        //checkTheLine();
    }

    @FXML
    private Button syrup_button;

    @FXML //close window with custom button
    protected void handleSyrupAction(ActionEvent event) {
        System.out.println("lavender syrup activate...heading to it");
        InGameCommand syrupCommand = user.commandOptions.get(1);
        user.getInvoker().addCommand(syrupCommand);//adding syrup command to queue
        amountDisplay.setText("$" + Account.getInstance().getAmountString());
        // checkTheLine();
    }

    @FXML
    private Button cash_button;

    @FXML
    protected void handleCashAction(ActionEvent event) {
        System.out.println("cash activate...heading to register");

        //path from location to register
        //walk(Location.REGISTER, mybarista,barista);

        // TODO: cashier check functionality
        InGameCommand orderCommand = user.commandOptions.get(3);
        user.getInvoker().addCommand(orderCommand);//adding orderup command to queue
        amountDisplay.setText("$" + Account.getInstance().getAmountString());
        //checkTheLine();
    }

    @FXML
    private Button trash_button;

    @FXML
    protected void handleTrashAction(ActionEvent event) {
        System.out.println("Trash activate...heading to trash");
        //path from location to trash
        // walk(Location.TRASH, mybarista, barista);
        InGameCommand trashCommand = user.commandOptions.get(4);
        user.getInvoker().addCommand(trashCommand);//adding trash command to queue
        //checkTheLine();
    }

    private void initializeLocations() {
        locations = new HashMap<Location, Pair<Double, Double>>();
        locations.put(Location.REGISTER, new Pair<Double, Double>(360.0, 360.0));
        locations.put(Location.COFFEE_MACHINE, new Pair<Double, Double>(100.0, 260.0));
        locations.put(Location.MILK_STEAMER, new Pair<Double, Double>(350.0, 260.0));
        locations.put(Location.SYRUPS, new Pair<Double, Double>(250.0, 260.0));
        locations.put(Location.TRASH, new Pair<Double, Double>(450.0, 260.0));
        locations.put(Location.OFF_SCREEN, new Pair<>(1200.0, 480.0));
        locations.put(Location.LINE_0, new Pair<>(300.0, 480.0));
        locations.put(Location.LINE_1, new Pair<>(500.0, 480.0));
        locations.put(Location.LINE_2, new Pair<>(700.0, 480.0));
        locations.put(Location.LINE_3, new Pair<>(900.0, 480.0));
        locations.put(Location.WAITING_1, new Pair<>(500.0, 250.0));
        locations.put(Location.WAITING_2, new Pair<>(700.0, 250.0));
        locations.put(Location.WAITING_3, new Pair<>(900.0, 250.0));
        locations.put(Location.WAITING_4, new Pair<>(1100.0, 250.0));
        locations.put(Location.EMERGENCY_WAITING_LOC, new Pair<>(800.0, 150.0));
    }

    protected void walk(Location destination, CharacterView character, ImageView characterImageView) {
        //uppack the current location coordinates from the chracter data structure
        Pair<Double, Double> currentLoc = character.getLocation();
        Double currentX = currentLoc.getKey();
        Double currentY = currentLoc.getValue();

        //get coordinates of new location based on enum Location argument
        Pair<Double, Double> newLoc = locations.get(destination);
        Double newX = newLoc.getKey();
        Double newY = newLoc.getValue();

        //update the new location in character data structure
        character.setLocation(newLoc);
        if (Objects.equals(newX, currentX) && Objects.equals(newY, currentY)) {
            //Do nothing if new location is same as current
            return;
        } else if (newX - currentX < 0) {
            //set the image displayed to be the walking left gif if the character is going left
            if (playableCharacter.getCarryingItem() != null) {
                System.out.println(playableCharacter.getCarryingItem().graphicName);
                switch (playableCharacter.getCarryingItem().graphicName) {
                    case NONE:
                        characterImageView.setImage(character.getWalkingLeft());
                        break;
                    case COFFEE:
                        characterImageView.setImage(character.getWalkingCarryLeftCoffee());
                        break;
                    case LATTE:
                        characterImageView.setImage(character.getWalkingCarryLeftLatte());
                        break;
                    case SYRUP_LATTE:
                        characterImageView.setImage(character.getWalkingCarryLeftLavLatte());
                        break;
                    case SYRUP_COFFEE:
                        characterImageView.setImage(character.getWalkingCarryLeftLavCoffee());
                        break;
                }
            } else {
                characterImageView.setImage(character.getWalkingLeft());
            }
        } else {
            //set the image displayed to be the walking right gif if the character is going right
            if (playableCharacter.getCarryingItem() != null) {

                switch (playableCharacter.getCarryingItem().graphicName) {
                    case NONE:
                        characterImageView.setImage(character.getWalkingRight());
                        break;
                    case COFFEE:
                        characterImageView.setImage(character.getWalkingCarryRightCoffee());
                        break;
                    case LATTE:
                        characterImageView.setImage(character.getWalkingCarryRightLatte());
                        break;
                    case SYRUP_LATTE:
                        characterImageView.setImage(character.getWalkingCarryRightLavLatte());
                        break;
                    case SYRUP_COFFEE:
                        characterImageView.setImage(character.getWalkingCarryRightLavCoffee());
                        break;
                }
            } else {
                characterImageView.setImage(character.getWalkingRight());
            }
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
        long walkEndTime = Instant.now().getEpochSecond() + duration;

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
        while (Instant.now().getEpochSecond() < walkEndTime) {
            assert (true);
        }
        //change image back to the front facing image
        if (playableCharacter.getCarryingItem() != null) {
            switch (playableCharacter.getCarryingItem().graphicName) {
                case NONE -> characterImageView.setImage(character.getFrontImage());
                case COFFEE -> characterImageView.setImage(character.getFrontImageCoffee());
                case SYRUP_COFFEE -> characterImageView.setImage(character.getFrontImageLavCoffee());
                case SYRUP_LATTE -> characterImageView.setImage(character.getFrontImageLavLatte());
                case LATTE -> characterImageView.setImage(character.getFrontImageLatte());
            }
        } else {
            characterImageView.setImage(character.getFrontImage());
        }
    }

    public void updateLocation(int objectId, Location location) {
        System.out.println(objectId);
        if (!inGameCharacters.containsKey(objectId)) {
            throw new IllegalArgumentException("Invalid ID" + objectId + "Current: " + inGameCharacters.keySet());
        } else {
            Pair<ImageView, CharacterView> charInfo = inGameCharacters.get(objectId);
            if (charInfo.getKey() == null) {
                System.out.println("Null");
            }
            walk(location, charInfo.getValue(), charInfo.getKey());
        }
    }

    public synchronized void updateLocationNoWalk(int objectId, Location location) {
        System.out.println(objectId);
        if (!inGameCharacters.containsKey(objectId)) {
            throw new IllegalArgumentException("Invalid ID" + objectId + "Current: " + inGameCharacters.keySet());
        } else {
            Pair<ImageView, CharacterView> charInfo = inGameCharacters.get(objectId);
            if (charInfo.getKey() == null) {
                System.out.println("Null");
            }
            if (charInfo.getValue().getLocation() == locations.get(location)) {
                return;
            }

            charInfo.getValue().setLocation(locations.get(location));
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    charInfo.getKey().setLayoutX(locations.get(location).getKey());
                    charInfo.getKey().setLayoutY(locations.get(location).getValue());
                }
            });
            printAllLocations();
        }
    }

    //https://stackoverflow.com/questions/17850191/why-am-i-getting-java-lang-illegalstateexception-not-on-fx-application-thread
    @FXML
    public void updateMoneyDisplay(String newAmountString) {
        System.out.println(amountDisplay);
        System.out.println(newAmountString);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                amountDisplay.setText("$" + newAmountString);
            }
        });
    }

    public synchronized void addNPC(int id, Character character, Location location) throws IOException {
        //https://www.tutorialspoint.com/find-minimum-element-of-hashset-in-java#:~:text=To%20get%20the%20minimum%20element,min()%20method.
        Set<Integer> keys = inGameCharacters.keySet();
        int emptySpot = Collections.min(keys);
        if (emptySpot >= 0) {
            throw new RuntimeException("No space for a new NPC");
        } else {
            Pair<ImageView, CharacterView> newNPC = inGameCharacters.remove(emptySpot);
            ImageView imageView = newNPC.getKey();
            //https://www.geeksforgeeks.org/double-compare-method-in-java-with-examples/#:~:text=The%20compare()%20method%20of,returned%20by%20the%20function%20call.
            Double waitingLocation = imageView.getLayoutX();
            if (Double.compare(waitingLocation, locations.get(Location.WAITING_1).getKey()) == 0) {
                leaveWaitingLocation(Location.WAITING_1);
            } else if (Double.compare(waitingLocation, locations.get(Location.WAITING_2).getKey()) == 0) {
                leaveWaitingLocation(Location.WAITING_2);
            } else if (Double.compare(waitingLocation, locations.get(Location.WAITING_3).getKey()) == 0) {
                leaveWaitingLocation(Location.WAITING_3);
            } else if (Double.compare(waitingLocation, locations.get(Location.WAITING_4).getKey()) == 0) {
                leaveWaitingLocation(Location.WAITING_4);
            } else {

            }

            Pair<Double, Double> startingLocation = locations.get(location);
            CharacterView characterView = CharacterView.makeCharacter(character, id, startingLocation);
            inGameCharacters.put(id, new Pair<>(imageView, characterView));

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    imageView.setLayoutX(startingLocation.getKey());
                    imageView.setLayoutY(startingLocation.getValue());
                    imageView.setImage(characterView.getFrontImage());
                    imageView.setVisible(true);
                }
            });
            //updateLocation(id, location);

            printAllLocations();
        }
    }

    public synchronized void removeNPC(int id) {
        if (inGameCharacters.containsKey(id)) {
            Pair<ImageView, CharacterView> goodbyeNPC = inGameCharacters.remove(id);
            ImageView imageView = goodbyeNPC.getKey();
            //https://www.tabnine.com/code/java/methods/javafx.scene.image.ImageView/setVisible
            //imageView.setVisible(false);

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Pair<Double, Double> waitingLoc = locations.get(getAvailableWaitingLocation());
                    imageView.setLayoutX(waitingLoc.getKey());
                    imageView.setLayoutY(waitingLoc.getValue());
                }
            });

            Set<Integer> keys = inGameCharacters.keySet();
            int negID = Collections.min(keys) - 1;
            inGameCharacters.put(negID, new Pair<ImageView, CharacterView>(imageView, null));
        } else {
            throw new RuntimeException("Trying to remove NPC that doesn't exist. Id = " + id + " ingame characters  = " + inGameCharacters.keySet());
        }
        printAllLocations();
    }

    private void printAllLocations() {
        for (int charID : inGameCharacters.keySet()) {
            if (charID >= 0) {
                System.out.println("ID: "
                        + charID
                        + " name: "
                        + inGameCharacters.get(charID).getValue().getName()
                        + " location: "
                        + inGameCharacters.get(charID).getValue().getLocation()
                        + " true location : "
                        + inGameCharacters.get(charID).getKey().getLayoutX()
                        + " , "
                        + inGameCharacters.get(charID).getKey().getLayoutY()
                        + " visible: "
                        + inGameCharacters.get(charID).getKey().isVisible()
                        + " view: "
                        + inGameCharacters.get(charID).getKey().getId());

            } else {
                System.out.println("ID: "
                        + charID
                        + " name: none"
                        + " true location : "
                        + inGameCharacters.get(charID).getKey().getLayoutX()
                        + " , "
                        + inGameCharacters.get(charID).getKey().getLayoutY()
                        + " visible: "
                        + inGameCharacters.get(charID).getKey().isVisible()
                        + " view: "
                        + inGameCharacters.get(charID).getKey().getId());

            }
        }
    }

    private void checkTheLine() {
        for (Integer id : inGameCharacters.keySet()) {
            if (id > 0) {
                ImageView imageView = inGameCharacters.get(id).getKey();
                CharacterView characterView = inGameCharacters.get(id).getValue();
                Pair<Double, Double> currentLocation = locations.get(Model.getInstance().getData(id, Attribute.LOCATION));
                imageView.setLayoutX(currentLocation.getKey());
                imageView.setLayoutY(currentLocation.getValue());
                characterView.setLocation(currentLocation);
                imageView.setVisible(true);
            }
        }
    }

    private Location getAvailableWaitingLocation() {
        for (Location location : waitingLocations.keySet()) {
            if (waitingLocations.get(location)) {
                waitingLocations.replace(location, false);
                return location;
            }
        }
        System.out.println("ERROR: No available waiting location. " + waitingLocations);
        //TODO
        return Location.EMERGENCY_WAITING_LOC;
    }

    private void leaveWaitingLocation(Location location) {
        waitingLocations.replace(location, true);
    }

    @FXML
    private ImageView requestGraphic;

    @FXML
    public void updateCurrentRequestBubble(Requestable newRequest) {
        System.out.println("Updating request bubble" + newRequest.toString());
        try {
            DrinkView updateRequest = DrinkView.makeDrink(newRequest, 100, new Pair<>(376.0, 430.0));
            requestGraphic.setImage(updateRequest.thoughtBubbleImage);
            requestGraphic.setOpacity(100.0);
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @FXML
    public void hideDrinkRequest(){
        System.out.println("Hiding request bubble");
        requestGraphic.setOpacity(0.0);
    }

    @FXML
    private Button tryAgainButton;
    @FXML
    private Button loseQuitButton;
    @FXML
    private Button winQuitButton1;
    @FXML
    private Button winQuitButton2;
    @FXML
    private Button nextLevelButton;
    @FXML
    public void endGame(boolean wonGame){
        disableGame();
        if (wonGame){
            highScoreText.setText("High Score: $" + currentLevel.getHighScore());
            highScoreText.setOpacity(100.0);
            System.out.println("Going to won game screen");
            try {
                LevelScreenView updateScreen = LevelScreenView.makeLevelView(gameLevelList[levelNum], 101, new Pair<>(0.0, 1.0));
                levelScreenPicture.setImage(updateScreen.winScreenImage);
                if(levelNum == 2){
                    winQuitButton2.setDisable(false);
                }
                else{
                    winQuitButton1.setDisable(false);
                    nextLevelButton.setDisable(false);
                }
                levelScreenPicture.setOpacity(100.0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            System.out.println("Going to lost game screen");
            try {
                LevelScreenView updateScreen = LevelScreenView.makeLevelView(gameLevelList[levelNum],  102, new Pair<>(0.0, 1.0));
                levelScreenPicture.setImage(updateScreen.loseScreenImage);
                tryAgainButton.setDisable(false);
                loseQuitButton.setDisable(false);
                levelScreenPicture.setOpacity(100.0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML void disableGame(){
        Button[] listOfGameButtons = {coffee_button, syrup_button, milk_button, cash_button, trash_button};
        for (Button b : listOfGameButtons){
            b.setDisable(true);
        }
        line0Heart.setVisible(false);
        line1Heart.setVisible(false);
        line2Heart.setVisible(false);
        line3Heart.setVisible(false);
    }

    @FXML void enableGame(){
        Button[] listOfGameButtons = {coffee_button, syrup_button, milk_button, cash_button, trash_button};
        for (Button b : listOfGameButtons){
            b.setDisable(false);
        }
    }

    @FXML
    protected void handleTryAgainAction() throws IOException {
        resetGame();
        tryAgainButton.setDisable(true);
        loseQuitButton.setDisable(true);
    }

    @FXML
    protected void handleNextLevelAction() throws IOException{
        levelNum++;
        resetGame();
        highScoreText.setOpacity(0.0);
        nextLevelButton.setDisable(true);
        winQuitButton1.setDisable(true);
    }

    protected void resetGame() throws IOException{
        //reset singleton classes
        Model.getInstance().clearModel();
        Account.getInstance().clearAccount();
        CustomerManager.getInstance(Account.getInstance()).resetManager();
        PlayableCharacter.getInstance().resetCharacter();
        user = new InGameInteractiveUser(playableCharacter);
        inGameCharacters = new HashMap<Integer, Pair<ImageView, CharacterView>>();
        //enable game buttons
        enableGame();
        //hide end screen
        levelScreenPicture.setOpacity(0.0);
        //show start screen
        LevelScreenView updateScreen = LevelScreenView.makeLevelView(gameLevelList[levelNum],  102, new Pair<>(0.0, 1.0));
        levelScreenPicture.setImage(updateScreen.startSceenImage);
        levelScreenPicture.setOpacity(100.0);
        highScoreText.setOpacity(0.0);
        //enable start button
        startButton.setDisable(false);
    }


    public void changeBaristaItem(int id, Requestable newItem) {
        assert(id == 0);
        System.out.println("IN CHANGE BARISTA ITEM");
        if (inGameCharacters.containsKey(id)) {
            Pair<ImageView, CharacterView> charBarista = inGameCharacters.get(id);
            ImageView characterImageView = charBarista.getKey();
            CharacterView character = charBarista.getValue();
            switch (newItem) {
                case NONE -> characterImageView.setImage(character.getFrontImage());
                case COFFEE -> characterImageView.setImage(character.getFrontImageCoffee());
                case SYRUP_COFFEE -> characterImageView.setImage(character.getFrontImageLavCoffee());
                case SYRUP_LATTE -> characterImageView.setImage(character.getFrontImageLavLatte());
                case LATTE -> characterImageView.setImage(character.getFrontImageLatte());
                default -> characterImageView.setImage(character.getFrontImage());
            }
        }
    }

    @FXML
    public void moneyMinusAnimation(double amount){
        moneyMinus.setVisible(true);
        moneyMinus.setOpacity(1);
        DecimalFormat df = new DecimalFormat("#.00");
        String formattedValue = df.format(amount);
        moneyMinus.setText("- $" + formattedValue);
        moneyMinus.setTranslateY(50);
        moneyMinus.setLayoutX(10);
        moneyMinus.setLayoutY(-50);
        Timeline timeline = new Timeline();
        KeyValue keyValue = new KeyValue(moneyMinus.translateYProperty(), -50, Interpolator.EASE_IN);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), keyValue);
        timeline.getKeyFrames().add(keyFrame);

        // Add a key frame to the timeline to fade the label out
        keyValue = new KeyValue(moneyMinus.opacityProperty(), 0);
        keyFrame = new KeyFrame(Duration.seconds(1), keyValue);
        timeline.getKeyFrames().add(keyFrame);
        timeline.setOnFinished(event -> moneyMinus.setVisible(false));
        // Play the timeline
        timeline.play();
    }
    @FXML
    public void moneyPlusAnimation(double amount){
        moneyPlus.setVisible(true);
        moneyPlus.setOpacity(1);
        DecimalFormat df = new DecimalFormat("#.00");
        String formattedValue = df.format(amount);
        moneyPlus.setText("+ $" + formattedValue);
        moneyPlus.setTranslateY(50);
        moneyPlus.setLayoutX(10);
        moneyPlus.setLayoutY(-50);
        Timeline timeline = new Timeline();
        KeyValue keyValue = new KeyValue(moneyPlus.translateYProperty(), -50, Interpolator.EASE_IN);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), keyValue);
        timeline.getKeyFrames().add(keyFrame);

        // Add a key frame to the timeline to fade the label out
        keyValue = new KeyValue(moneyPlus.opacityProperty(), 0);
        keyFrame = new KeyFrame(Duration.seconds(1), keyValue);
        timeline.getKeyFrames().add(keyFrame);
        timeline.setOnFinished(event -> moneyPlus.setVisible(false));
        // Play the timeline
        timeline.play();
    }

    @FXML
    public void tipAnimation(double amount){
        tip.setVisible(true);

        Timeline timeline1 = new Timeline(new KeyFrame(Duration.millis(500.0), event -> {
            // Set the opacity to 1 to make the text fully visible
            tip.setOpacity(1);
        }));

        DecimalFormat df = new DecimalFormat("#.00");
        String formattedValue = df.format(amount);
        tip.setText("+ $" + formattedValue);
        tip.setTranslateY(50);
        tip.setLayoutX(25);
        tip.setLayoutY(-50);
        Timeline timeline = new Timeline();
        KeyValue keyValue = new KeyValue(tip.translateYProperty(), -50, Interpolator.EASE_IN);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), keyValue);
        timeline.getKeyFrames().add(keyFrame);

        // Add a key frame to the timeline to fade the label out
        keyValue = new KeyValue(tip.opacityProperty(), 0);
        keyFrame = new KeyFrame(Duration.seconds(1), keyValue);
        timeline.getKeyFrames().add(keyFrame);
        //timeline.setOnFinished(event -> tip.setVisible(false));
        // Play the timeline
        timeline.setDelay(Duration.millis(500));
        timeline1.setOnFinished(event -> timeline.play());
        timeline1.play();
    }

    public void changePatienceHeart(Location location, double patienceLevel){
        if (location == Location.LINE_0){
            line0Heart.setImage(heartView.getHeartFromPatience(patienceLevel));
        } else if (location == Location.LINE_1){
            line1Heart.setImage(heartView.getHeartFromPatience(patienceLevel));
        } else if (location == Location.LINE_2){
            line2Heart.setImage(heartView.getHeartFromPatience(patienceLevel));
        } else if (location == Location.LINE_3){
            line3Heart.setImage(heartView.getHeartFromPatience(patienceLevel));
        }
    }

    public void showPatienceHeart(Location location){
        if (location == Location.LINE_0){
            line0Heart.setVisible(true);
        } else if (location == Location.LINE_1){
            line1Heart.setVisible(true);
        } else if (location == Location.LINE_2){
            line2Heart.setVisible(true);
        } else if (location == Location.LINE_3){
            line3Heart.setVisible(true);
        }
    }

    public void hidePateinceHeart(Location location){
        if (location == Location.LINE_0){
            line0Heart.setVisible(false);
        } else if (location == Location.LINE_1){
            line1Heart.setVisible(false);
        } else if (location == Location.LINE_2){
            line2Heart.setVisible(false);
        } else if (location == Location.LINE_3){
            line3Heart.setVisible(false);
        }
    }
}
