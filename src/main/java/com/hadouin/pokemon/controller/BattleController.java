package com.hadouin.pokemon.controller;

import com.hadouin.pokemon.PokemonBattleUI;
import com.hadouin.pokemon.controls.PokemonCard;
import com.hadouin.pokemon.core.Battle;
import com.hadouin.pokemon.core.Move;
import com.hadouin.pokemon.core.Player;
import com.hadouin.pokemon.core.Pokemon;
import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

public class BattleController implements PokemonBattleUI {
    private final double MAX_VALUE = 1.7976931348623157E308;
    @FXML private GridPane choicesGrid;
    @FXML private PokemonCard playerCard;
    @FXML private ImageView playerImage;
    @FXML private PokemonCard enemyCard;
    @FXML private ImageView enemyImage;
    @FXML private Label labelMessage;

    Battle battle;

    public BattleController(Battle battle) {
        this.battle = battle;
    }

    @Override
    public void updatePlayerPokemon(Pokemon playerPokemon) {
        playerCard.updatePokemon(playerPokemon);
        playerImage.setImage(playerPokemon.getImageBack());
    }

    @Override
    public void updateEnemyPokemon(Pokemon enemyPokemon) {
        enemyCard.updatePokemon(enemyPokemon);
        enemyImage.setImage(enemyPokemon.getImageFront());
    }

    @Override
    public void updatePokemons(Pokemon playerPokemon, Pokemon enemyPokemon) {
        updatePlayerPokemon(playerPokemon);
        updateEnemyPokemon(enemyPokemon);
    }

    @Override
    public void chooseAction() {
        this.choicesGrid.getChildren().clear();
        Button attackButton = new Button("Attack");
        attackButton.setMaxHeight(MAX_VALUE);
        attackButton.setMaxWidth(MAX_VALUE);
        attackButton.setOnAction(e -> {
            chooseMove(battle.currentPokemon);
        });
        Button changeButton = new Button("Changer de Pokemon");
        changeButton.setMaxHeight(MAX_VALUE);
        changeButton.setMaxWidth(MAX_VALUE);
        changeButton.setOnAction(e -> {
            choosePokemon(battle.currentPlayer);
        });
        this.choicesGrid.add(attackButton, 0,0);
        this.choicesGrid.add(changeButton,1 ,0);
    }

    @Override
    public void setMessage(String string) {
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

    @Override
    public void chooseMove(Pokemon pokemon) {
        clearChoices();
        int x = 0;
        int y = 0;
        for ( Move move : pokemon.getAttacks()) {

            Button button = new Button(move.getName());
            button.setMaxWidth(MAX_VALUE);
            button.setMaxHeight(MAX_VALUE);
            button.setOnAction(e -> {
                battle.resolveMove(move);
            });
            this.choicesGrid.add(button, x, y);

            x++;
            if (x > 1){
                y++;
                x=0;
            }
        }
    }

    @Override
    public void choosePokemon(Player player) {
        clearChoices();
        int x = 0;
        int y = 0;
        for (Pokemon pokemon : player.getPokemons()) {
            Button button = new Button(pokemon.getName() + " " + pokemon.getPV() +"/"+ pokemon.getMaxPV());
            button.setMaxWidth(MAX_VALUE);
            button.setMaxHeight(MAX_VALUE);
            if (!pokemon.isFainted()) {
                button.setOnAction(e -> {
                    battle.changePokemon(pokemon);
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

    @Override
    public void clearChoices(){
        this.choicesGrid.getChildren().clear();
    }

    @Override
    public void setEnemyPokemon(Pokemon enemyPokemon) {
        enemyCard.setPokemon(enemyPokemon);
        enemyImage.setImage(enemyPokemon.getImageFront());
    }

    @Override
    public void setPlayerPokemon(Pokemon playerPokemon) {
        playerCard.setPokemon(playerPokemon);
        playerImage.setImage(playerPokemon.getImageBack());
    }

    @Override
    public void setPokemons(Pokemon playerPokemon, Pokemon enemyPokemon) {
        setPlayerPokemon(playerPokemon);
        setEnemyPokemon(enemyPokemon);
    }


}
