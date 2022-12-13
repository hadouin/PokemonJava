package com.hadouin.pokemon.core;

public enum Professor {
    SORBIER("Sorbier", new Pokemon[]{new Pokemon.Builder(Species.Tiplouf).build(), new Pokemon.Builder(Species.Ouisticram).build(), new Pokemon.Builder(Species.Tortipouss).build()}),
    SEKO("Seko", new Pokemon[]{new Pokemon.Builder(Species.Arcko).build(), new Pokemon.Builder(Species.Poussifeu).build(), new Pokemon.Builder(Species.Gobou).build()}),
    CHEN("Chen", new Pokemon[]{new Pokemon.Builder(Species.Bulbizarre).build(), new Pokemon.Builder(Species.Salamèche).build(), new Pokemon.Builder(Species.Carapuce).build()}),
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
