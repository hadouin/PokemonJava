package com.hadouin.pokemon.controller;

import com.hadouin.pokemon.core.Battle;
import com.hadouin.pokemon.core.Player;
import com.hadouin.pokemon.core.StarterPokemons;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class MenuController {

    @FXML
    protected void onBattlePressed(ActionEvent ae) throws IOException {
        Player player1 = new Player("Hadouin");
        player1.addPokemon(StarterPokemons.Ouisticram.buildPokemon());
        player1.addPokemon(StarterPokemons.Tortipouss.buildPokemon());
        player1.addPokemon(StarterPokemons.Tiplouf.buildPokemon());
        Player player2 = new Player("Sacha");
        player2.addPokemon(StarterPokemons.Tortipouss.buildPokemon());
        player2.addPokemon(StarterPokemons.Tiplouf.buildPokemon());
        player2.addPokemon(StarterPokemons.Ouisticram.buildPokemon());
        Battle battle = new Battle(player1, player2);
        Node source = (Node) ae.getSource();
        Stage theStage = (Stage) source.getScene().getWindow();
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        battle.start(theStage);
        theStage.setX((primScreenBounds.getWidth() - theStage.getWidth()) / 2);
        theStage.setY((primScreenBounds.getHeight() - theStage.getHeight()) / 2);
    }
}
