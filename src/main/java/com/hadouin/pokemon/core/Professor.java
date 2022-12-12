package com.hadouin.pokemon.core;

public enum Professor {
    SORBIER("Sorbier", new Pokemon[]{Species.Tiplouf.buildPokemon(), Species.Ouisticram.buildPokemon(), Species.Tortipouss.buildPokemon()}),
    SEKO("Seko", new Pokemon[]{Species.Arcko.buildPokemon(), Species.Poussifeu.buildPokemon(), Species.Gobou.buildPokemon()}),
    CHEN("Chen", new Pokemon[]{Species.Bulbizarre.buildPokemon(), Species.Salamèche.buildPokemon(), Species.Carapuce.buildPokemon()}),
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
