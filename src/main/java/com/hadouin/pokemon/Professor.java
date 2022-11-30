package com.hadouin.pokemon;

import com.hadouin.pokemon.Pokemon.Starters;

public enum Professor {
    SORBIER("Sorbier", new Pokemon[]{Starters.Tiplouf.buildPokemon(), Starters.Ouisticram.buildPokemon(), Starters.Tortipouss.buildPokemon()}),
    SEKO("Seko", new Pokemon[]{Starters.Arcko.buildPokemon(), Starters.Poussifeu.buildPokemon(), Starters.Gobou.buildPokemon()}),
    CHEN("Chen", new Pokemon[]{Starters.Bulbizarre.buildPokemon(), Starters.Salamèche.buildPokemon(), Starters.Carapuce.buildPokemon()}),
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
