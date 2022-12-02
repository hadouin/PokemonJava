package com.hadouin.pokemon;

import javafx.animation.Animation;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Window;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class BattleController {

    private final double MAX_VALUE = 1.7976931348623157E308;

    Player player1;
    Pokemon p1CurrentPokemon;
    Player player2;
    Pokemon p2CurrentPokemon;
    Player currentPlayer;
    int cpPokemonIndex;

    @FXML private GridPane choicesGrid;
    @FXML private Label Player1NameLabel;
    @FXML private Label Player2NameLabel;
    @FXML private Label Player1PokemonNameLabel;
    @FXML private Label Player2PokemonNameLabel;
    @FXML private PokemonCard playerCard;
    @FXML private PokemonCard enemyCard;
    @FXML private Label labelMessage;

    BattleController(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.p1CurrentPokemon = player1.getPokemons().get(0);
        this.p2CurrentPokemon = player2.getPokemons().get(0);
        currentPlayer = this.player1;
    }

    public void initialize() {
        Player1NameLabel.setText(player1.name);
        Player2NameLabel.setText(player2.name);
        Player1PokemonNameLabel.setText(p1CurrentPokemon.getName());
        Player2PokemonNameLabel.setText(p2CurrentPokemon.getName());
        playerCard.setPokemon(p1CurrentPokemon);
        enemyCard.setPokemon(p2CurrentPokemon);
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
            defender = p2CurrentPokemon;
        } else {
            defender = p1CurrentPokemon;
        }
        castAttackFromTo(attack, currentPlayer.getPokemons().get(cpPokemonIndex), defender);

    }

    private void castAttackFromTo(Attack attack, Pokemon attacker, Pokemon defender) {
        clearChoices();
        sendMessage("C'est super efficace !");
        defender.losePV(attack.getPower() / 2);
        playerCard.setPokemon(p1CurrentPokemon);
        enemyCard.setPokemon(p2CurrentPokemon);
    }

    public void sendMessage(String string) {
        String content = string;
        labelMessage.opacityProperty().setValue(1);
        final Animation animation = new Transition() {
            {
                setCycleDuration(Duration.millis(2000));
            }
            protected void interpolate(double frac) {
                final int length = content.length();
                final int n = Math.round(length * (float) frac);
                labelMessage.setText(content.substring(0, n));
            }
        };
        SequentialTransition sequentialTransition = new SequentialTransition(animation, new PauseTransition(Duration.seconds(3)));
        sequentialTransition.play();
        sequentialTransition.setOnFinished(e -> {
            labelMessage.opacityProperty().setValue(0);
            chooseAction();
        });

    }

    private void clearChoices(){
        this.choicesGrid.getChildren().clear();
    }

}
