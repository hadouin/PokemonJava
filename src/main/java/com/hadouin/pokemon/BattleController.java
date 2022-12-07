package com.hadouin.pokemon;

import javafx.animation.Animation;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
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

    BattleController(Player player, Player enemy) {
        this.player = player;
        this.enemy = enemy;
        this.playerPokemon = player.getPokemons().get(0);
        this.enemyPokemon = enemy.getPokemons().get(0);
        currentPlayer = this.player;
        currentPokemon = this.playerPokemon;
    }

    public void initialize() {
        update();
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
        for ( Attack attack : currentPokemon.getAttacks()) {

            Button button = new Button(attack.getName());
            button.setMaxWidth(MAX_VALUE);
            button.setMaxHeight(MAX_VALUE);
            button.setOnAction(e -> {
                doAttack(attack);
            });
            this.choicesGrid.add(button, x, y);

            x++;
            if (x > 1){
                y++;
                x=0;
            }
        }
    }

    private void doAttack(Attack attack) {
        if (currentPlayer == player) {
            castAttackFromTo(attack, playerPokemon, enemyPokemon);
        } else {
            castAttackFromTo(attack, enemyPokemon, playerPokemon);
        }
    }

    private void castAttackFromTo(Attack attack, Pokemon attacker, Pokemon defender) {
        clearChoices();
        defender.losePV(attack.getPower() / 2);
        update();
        sendMessage("C'est super efficace !", this::nextPlayer);
    }

    private interface IVoid {
        public abstract void doit();
    }

    public void sendMessage(String string, IVoid onFinish) {
        String content = string;
        labelMessage.opacityProperty().setValue(1);
        final Animation animation = new Transition() {
            {
                setCycleDuration(Duration.millis(1000));
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
            onFinish.doit();
        });

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
            button.setOnAction(e -> {
                changePokemon(pokemon);
            });
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
            sendMessage(playerPokemon.getName() + " à toi de jouer !", this::nextPlayer);
        }
        if (this.currentPlayer == enemy){
            enemyPokemon = pokemon;
            update();
            clearChoices();
            sendMessage(enemyPokemon.getName() + " à toi de jouer !", this::nextPlayer);
        }
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
