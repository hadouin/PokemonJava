package com.hadouin.pokemon.core;

import com.hadouin.pokemon.Main;
import com.hadouin.pokemon.controller.BattleController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("battle-view.fxml"));
        fxmlLoader.setController(battleController);
        Scene scene = new Scene(fxmlLoader.load(), 620, 700);
        stage.setTitle("Battle!");
        stage.setScene(scene);
        stage.show();
    }
}
