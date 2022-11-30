package com.hadouin.pokemon;

import java.util.ArrayList;
import java.util.List;

public class Player {
    String name;
    List<Pokemon> pokemons;

    Player(String name) {
        this.name = name;
        pokemons = new ArrayList<>(6);
    }

    public void addPokemon(Pokemon pokemon) {
        this.pokemons.add(pokemon);
    }
    public List<Pokemon> getPokemons(){
        return this.pokemons;
    }
}
