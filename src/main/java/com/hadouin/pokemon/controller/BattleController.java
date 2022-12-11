package com.hadouin.pokemon.controller;

import com.hadouin.pokemon.controls.PokemonCard;
import com.hadouin.pokemon.core.Move;
import com.hadouin.pokemon.core.Player;
import com.hadouin.pokemon.core.Pokemon;
import javafx.animation.Animation;
import javafx.animation.PauseTransition;
import javafx.animation.Transition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

public class BattleController {

    private final double MAX_VALUE = 1.7976931348623157E308;
    Player player;
    Pokemon playerPokemon;
    Player enemy;
    Pokemon enemyPokemon;
    Player currentPlayer;
    Pokemon currentPokemon;

    @FXML private GridPane choicesGrid;

    @FXML private PokemonCard playerCard;
    @FXML private ImageView playerImage;

    @FXML private PokemonCard enemyCard;
    @FXML private ImageView enemyImage;

    @FXML private Label labelMessage;
    @FXML private Label actionLabel;
    private String moveMessage;

    public BattleController(Player player, Player enemy) {
        this.player = player;
        this.enemy = enemy;
        this.playerPokemon = player.getPokemons().get(0);
        this.enemyPokemon = enemy.getPokemons().get(0);
        currentPlayer = this.player;
        currentPokemon = this.playerPokemon;
    }

    public void initialize() {
        update();
        setMessage("C'est au tour de " + currentPlayer.getName());
        chooseAction();
    }

    private void update() {
        playerCard.setPokemon(playerPokemon);
        enemyCard.setPokemon(enemyPokemon);
        playerImage.setImage(playerPokemon.getImageBack());
        enemyImage.setImage(enemyPokemon.getImageFront());
        actionLabel.setText(currentPlayer.getName());
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
        changeButton.setOnAction(e -> {
            choosePokemon();
        });
        this.choicesGrid.add(attackButton, 0,0);
        this.choicesGrid.add(changeButton,1 ,0);
    }

    private void chooseAttack() {
        clearChoices();
        int x = 0;
        int y = 0;
        for ( Move move : currentPokemon.getAttacks()) {

            Button button = new Button(move.getName());
            button.setMaxWidth(MAX_VALUE);
            button.setMaxHeight(MAX_VALUE);
            button.setOnAction(e -> {
                doAttack(move);
            });
            this.choicesGrid.add(button, x, y);

            x++;
            if (x > 1){
                y++;
                x=0;
            }
        }
    }

    private void doAttack(Move move) {
        Pokemon attacker;
        Pokemon defender;
        attacker = currentPokemon;
        defender = (currentPokemon == playerPokemon) ? enemyPokemon : playerPokemon;
        clearChoices();
        setMessage(attacker.getName() + " utilise " + move.getName());
        move.cast(attacker, defender);
        update();
        this.moveMessage = move.getAttackFactorString(defender);
        doAfter(this::afterAttack, 3000);
    }

    private void afterAttack() {
        Pokemon defender = (currentPokemon == playerPokemon) ? enemyPokemon : playerPokemon;
        if (defender.getPV() == 0) {
            nextPlayer();
            clearChoices();
            setMessage(defender.getName() + " est K.O. Choisir un nouveau pokemon: ");
            doAfter(this::choosePokemon, 3000);
        } else {
            setMessage(moveMessage);
            doAfter(this::nextPlayer, 3000);
        }
    }

    private interface IVoid {
        void doit();
    }

    private void setMessage(String string) {
        final Animation animation = new Transition() {
            {
                setCycleDuration(Duration.millis(1000));
            }
            protected void interpolate(double frac) {
                final int length = string.length();
                final int n = Math.round(length * (float) frac);
                labelMessage.setText(string.substring(0, n));
            }
        };
        animation.play();
    }

    private void clearMessage(){
        labelMessage.setText("");
    }

    private void doAfter(IVoid todo, int millis){
        PauseTransition pauseTransition = new PauseTransition((Duration.millis(millis)));
        pauseTransition.setOnFinished(e -> {
            todo.doit();
        });
        pauseTransition.play();
    }

    private void clearChoices(){
        this.choicesGrid.getChildren().clear();
    }

    private void choosePokemon() {
        clearChoices();
        int x = 0;
        int y = 0;
        for (Pokemon pokemon : currentPlayer.getPokemons()) {
            Button button = new Button(pokemon.getName() + " " + pokemon.getPV() +"/"+ pokemon.getMaxPV());
            button.setMaxWidth(MAX_VALUE);
            button.setMaxHeight(MAX_VALUE);
            if (!pokemon.isFainted()) {
                button.setOnAction(e -> {
                    changePokemon(pokemon);
                });
            }
            this.choicesGrid.add(button, x,y);
            x++;
            if (x > 1) {
                y++;
                x=0;
            }
        }
    }

    private void changePokemon(Pokemon pokemon) {
        if (this.currentPlayer == player){
            playerPokemon = pokemon;
            update();
            clearChoices();
            setMessage(playerPokemon.getName() + " à toi de jouer !");
        }
        if (this.currentPlayer == enemy){
            enemyPokemon = pokemon;
            update();
            clearChoices();
            setMessage(enemyPokemon.getName() + " à toi de jouer !");
        }
        doAfter(this::nextPlayer, 3000);
    }

    private void nextPlayer(){
        if (currentPlayer == player) {
            currentPlayer = enemy;
            currentPokemon = enemyPokemon;
        } else {
            currentPlayer = player;
            currentPokemon = playerPokemon;
        }
        update();
        chooseAction();
    }
}
