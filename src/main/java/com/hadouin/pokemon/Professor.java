package com.hadouin.pokemon;

import com.hadouin.pokemon.StarterPokemons;

public enum Professor {
    SORBIER("Sorbier", new Pokemon[]{StarterPokemons.Tiplouf.buildPokemon(), StarterPokemons.Ouisticram.buildPokemon(), StarterPokemons.Tortipouss.buildPokemon()}),
    SEKO("Seko", new Pokemon[]{StarterPokemons.Arcko.buildPokemon(), StarterPokemons.Poussifeu.buildPokemon(), StarterPokemons.Gobou.buildPokemon()}),
    CHEN("Chen", new Pokemon[]{StarterPokemons.Bulbizarre.buildPokemon(), StarterPokemons.Salamèche.buildPokemon(), StarterPokemons.Carapuce.buildPokemon()}),
    ;

    private String name;
    private Pokemon[] pokemons;

    Professor(String nameString, Pokemon[] pokemons) {
        this.name = nameString;
        this.pokemons = pokemons;
    }

    public Pokemon[] getPokemons() {
        return pokemons;
    }

    public String getName() {
        return name;
    }
}
