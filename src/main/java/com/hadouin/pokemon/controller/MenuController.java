package com.hadouin.pokemon.controller;

import com.hadouin.pokemon.core.Battle;
import com.hadouin.pokemon.core.Player;
import com.hadouin.pokemon.core.Pokemon;
import com.hadouin.pokemon.core.Species;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    @FXML
    protected void onBattlePressed(ActionEvent ae) throws IOException {
        Player player1 = new Player("Hadouin");
        player1.addPokemon(new Pokemon.Builder(Species.Ouisticram).baseXP(150).build());
        player1.addPokemon(new Pokemon.Builder(Species.Tortipouss).baseXP(125).build());
        player1.addPokemon(new Pokemon.Builder(Species.Tiplouf).baseXP(125).build());
        Player player2 = new Player("Sacha");
        player2.addPokemon(new Pokemon.Builder(Species.Tiplouf).baseXP(215).build());
        player2.addPokemon(new Pokemon.Builder(Species.Tortipouss).baseXP(125).build());
        player2.addPokemon(new Pokemon.Builder(Species.Ouisticram).baseXP(125).build());
        Battle battle = new Battle(player1, player2);
        Node source = (Node) ae.getSource();
        Stage theStage = (Stage) source.getScene().getWindow();
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();

        battle.start(theStage);

        theStage.setX((primScreenBounds.getWidth() - theStage.getWidth()) / 2);
        theStage.setY((primScreenBounds.getHeight() - theStage.getHeight()) / 2);
    }
}
