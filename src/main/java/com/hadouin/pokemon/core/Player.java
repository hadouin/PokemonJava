package com.hadouin.pokemon.core;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<Pokemon> pokemons;

    public Player(String name) {
        this.name = name;
        pokemons = new ArrayList<>(6);
    }

    public void addPokemon(Pokemon pokemon) {
        this.pokemons.add(pokemon);
    }
    public List<Pokemon> getPokemons(){
        return this.pokemons;
    }

    public String getName() {
        return this.name;
    }
}
