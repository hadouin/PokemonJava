package com.hadouin.pokemon;

import com.hadouin.pokemon.core.Player;
import com.hadouin.pokemon.core.Pokemon;

public interface PokemonBattleUI {
    void updatePlayerPokemon(Pokemon playerPokemon);
    void updateEnemyPokemon(Pokemon enemyPokemon);
    void updatePokemons(Pokemon playerPokemon, Pokemon enemyPokemon);
    void setMessage(String string);
    void chooseMove(Pokemon pokemon);
    void choosePokemon(Player player);
    void chooseAction();
    void clearChoices();
    void setEnemyPokemon(Pokemon enemyPokemon);
    void setPlayerPokemon(Pokemon playerPokemon);
    void setPokemons(Pokemon playerPokemon, Pokemon enemyPokemon);
}
