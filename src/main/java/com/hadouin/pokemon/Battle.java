package com.hadouin.pokemon;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Battle {
    Player player1;
    Player player2;

    public Battle(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }
    public void start(Stage stage) throws IOException {
        BattleController battleController = new BattleController(this.player1, this.player2);
        FXMLLoader fxmlLoader = new FXMLLoader(Battle.class.getResource("battle-view.fxml"));
        fxmlLoader.setController(battleController);

        Scene scene = new Scene(fxmlLoader.load(), 620, 700);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}
