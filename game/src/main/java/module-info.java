module com.catcafe.game {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.catcafe.game to javafx.fxml;
    exports com.catcafe.game;
}