package com.hadouin.pokemon.core;

import com.hadouin.pokemon.Main;
import com.hadouin.pokemon.PokemonBattleUI;
import com.hadouin.pokemon.controller.BattleController;
import javafx.animation.PauseTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class Battle {
    Player player;
    Pokemon playerPokemon;
    Player enemy;
    Pokemon enemyPokemon;
    public Player currentPlayer;
    public Pokemon currentPokemon;
    PokemonBattleUI ui;
    private String moveQuote;


    public Battle(Player player, Player enemy) {
        this.player = player;
        this.enemy = enemy;
        playerPokemon = player.getPokemons().get(0);
        enemyPokemon = enemy.getPokemons().get(0);
        currentPlayer = player;
        currentPokemon = playerPokemon;
    }

    public void start(Stage stage) throws IOException {
        BattleController bc = new BattleController(this);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("battle-view.fxml"));
        fxmlLoader.setController(bc);
        Scene scene = new Scene(fxmlLoader.load(), 620, 700);
        ui = bc;
        stage.setTitle("Battle!");
        stage.setScene(scene);
        stage.show();
        ui.displayPokemons(playerPokemon, enemyPokemon);
        ui.chooseAction();
    }

    private void nextPlayer(){
        if (currentPlayer == player) {
            currentPlayer = enemy;
            currentPokemon = enemyPokemon;
        } else {
            currentPlayer = player;
            currentPokemon = playerPokemon;
        }
    }

    private void update(){
        ui.displayPokemons(playerPokemon, enemyPokemon);
        currentPokemon = (currentPlayer == player) ? playerPokemon : enemyPokemon;
    }

    public void changePokemon(Pokemon pokemon) {
        if (this.currentPlayer == player){
            playerPokemon = pokemon;
        }
        if (this.currentPlayer == enemy){
            enemyPokemon = pokemon;
        }
        ui.setMessage(pokemon.getName() + " à toi de jouer !");
        nextPlayer();
        update();
        doAfter(2000, () -> {
            ui.setMessage("Que doit faire " + currentPokemon.getName() + "?");
        });
        ui.chooseAction();
    }

    public void resolveMove(Move move) {
        Pokemon attacker;
        Pokemon defender;
        attacker = currentPokemon;
        defender = (currentPokemon == playerPokemon) ? enemyPokemon : playerPokemon;
        ui.clearChoices();
        ui.setMessage(attacker.getName() + " utilise " + move.getName());
        move.cast(attacker, defender);
        update();
        this.moveQuote = move.getAttackFactorString(defender);
        doAfter(2000, () -> {
            ui.setMessage(moveQuote);
        });

        doAfter(3000, () ->{
            nextPlayer();
            if (defender.isFainted()) {
                ui.clearChoices();
                ui.setMessage(defender.getName() + " est K.O. Choisir un nouveau pokemon: ");
                ui.choosePokemon(currentPlayer);
            } else {
                ui.chooseAction();
            }
        });
        update();
    }

    // small functions not in interface
    private interface IVoid {
        void doit();
    }
    private void doAfter(int millis, IVoid todo){
        PauseTransition pauseTransition = new PauseTransition((Duration.millis(millis)));
        pauseTransition.setOnFinished(e -> {
            todo.doit();
        });
        pauseTransition.play();
    }
}
