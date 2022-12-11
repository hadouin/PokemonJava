module com.hadouin.pokemon {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.hadouin.pokemon to javafx.fxml;
    exports com.hadouin.pokemon;

    opens com.hadouin.utils to javafx.graphics;
    exports com.hadouin.pokemon.core;
    opens com.hadouin.pokemon.core to javafx.fxml;
    exports com.hadouin.pokemon.controller;
    opens com.hadouin.pokemon.controller to javafx.fxml;
    exports com.hadouin.pokemon.controls;
    opens com.hadouin.pokemon.controls to javafx.fxml;
}
