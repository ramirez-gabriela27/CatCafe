module com.catcafe.game {
    requires javafx.controls;
    requires javafx.fxml;
    requires commons.math3;


    opens com.catcafe.game to javafx.fxml;
    exports com.catcafe.game;
}