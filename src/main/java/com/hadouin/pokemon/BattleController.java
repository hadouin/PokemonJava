package com.hadouin.pokemon;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

public class BattleController {

    private final double MAX_VALUE = 1.7976931348623157E308;

    Player player1;
    int p1CurrentPokemonIndex;
    Player player2;
    int p2CurrentPokemonIndex;
    Player currentPlayer;
    int cpPokemonIndex;

    @FXML
    public GridPane choicesGrid;
    @FXML
    public Label Player1NameLabel;
    @FXML
    public Label Player2NameLabel;
    @FXML
    public Label Player1PokemonNameLabel;
    @FXML
    public Label Player2PokemonNameLabel;

    BattleController(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        p1CurrentPokemonIndex = 0;
        p2CurrentPokemonIndex = 0;
        currentPlayer = this.player1;
    }

    public void initialize() {
        Player1NameLabel.setText(player1.name);
        Player2NameLabel.setText(player2.name);
        Player1PokemonNameLabel.setText(player1.pokemons.get(p1CurrentPokemonIndex).getName());
        Player1PokemonNameLabel.setText(player2.pokemons.get(p2CurrentPokemonIndex).getName());
        chooseAction();
    }

    private void chooseAction() {
        this.choicesGrid.getChildren().clear();
        Button attackButton = new Button("Attack");
        attackButton.setMaxHeight(MAX_VALUE);
        attackButton.setMaxWidth(MAX_VALUE);
        attackButton.setOnAction(e -> {
            chooseAttack();
        });
        Button changeButton = new Button("Changer de Pokemon");
        changeButton.setMaxHeight(MAX_VALUE);
        changeButton.setMaxWidth(MAX_VALUE);
        this.choicesGrid.add(attackButton, 0,0);
        this.choicesGrid.add(changeButton,1 ,0);
    }

    private void chooseAttack() {
        this.choicesGrid.getChildren().clear();
        int x = 0;
        int y = 0;
        Attack[] currentAttacks = currentPlayer.pokemons.get(cpPokemonIndex).getAttacks();
        for ( Attack attack : currentAttacks) {
            if (attack != null) {
                Button button = new Button(attack.getName());
                button.setMaxWidth(MAX_VALUE);
                button.setMaxHeight(MAX_VALUE);
                button.setOnAction(e -> {
                    doAttack(attack);
                });
                this.choicesGrid.add(button, x, y);
            }
            x++;
            if (x > 1){
                y++;
                x=0;
            }
        }
    }

    private void doAttack(Attack attack) {
        Pokemon defender;
        if (currentPlayer == player1) {
            defender = player2.getPokemons().get(p2CurrentPokemonIndex);
        } else if (currentPlayer == player2) {
            defender = player1.getPokemons().get(p1CurrentPokemonIndex);
        }
        castAttackFromTo(attack, currentPlayer.getPokemons().get(cpPokemonIndex), defender);

    }

    private void castAttackFromTo(Attack attack, Pokemon attacker, Pokemon defender) {

    }

}
