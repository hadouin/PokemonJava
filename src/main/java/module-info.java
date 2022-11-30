module com.hadouin.pokemon {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.hadouin.pokemon to javafx.fxml;
    exports com.hadouin.pokemon;

    opens com.hadouin.utils to javafx.graphics;
}
