package com.hadouin.pokemon;

import com.hadouin.pokemon.core.Player;
import com.hadouin.pokemon.core.Pokemon;

public interface PokemonBattleUI {
    void displayPlayerPokemon(Pokemon playerPokemon);
    void displayEnemyPokemon(Pokemon enemyPokemon);
    void displayPokemons(Pokemon playerPokemon, Pokemon enemyPokemon);
    void setMessage(String string);
    void chooseMove(Pokemon pokemon);
    void choosePokemon(Player player);
}
