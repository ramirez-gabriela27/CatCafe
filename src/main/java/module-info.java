module com.catcafe.game {
    requires javafx.controls;
    requires javafx.fxml;
    requires commons.math3;
    requires java.desktop;


    opens com.catcafe.game to javafx.fxml;
    exports com.catcafe.game;
}