package com.catcafe.game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class CatCafe_App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CatCafe_App.class.getResource("catcafe-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        //title and icon
        stage.setTitle("Cat Cafe");
        Image icon = new Image(String.valueOf((getClass().getResource("/assets/icon.png"))));
        stage.getIcons().add(icon);
        //makes it so it's a single size
        stage.setResizable(false);
        //and removes the title bar we can have our own
            // TODO: make the window draggable, make buttons close, max, and min the screen
        //stage.initStyle(StageStyle.UNDECORATED);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}