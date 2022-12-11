package com.hadouin.pokemon.core;

public enum StarterPokemons {
    Tiplouf("Tiplouf", Type.WATER, 53, new Move[]{BaseMove.POUND.buildAttack(), BaseMove.BUBBLES.buildAttack()}),
    Ouisticram("Ouisticram",Type.FIRE, 44, new Move[]{BaseMove.SCRATCH.buildAttack(), BaseMove.EMBER.buildAttack()}),
    Tortipouss("Tortipouss",Type.GRASS, 55, new Move[]{BaseMove.TACKLE.buildAttack(), BaseMove.ABSORB.buildAttack()}),

    Arcko("Arcko", Type.GRASS, 40),
    Poussifeu("Poussifeu", Type.FIRE, 45 ),
    Gobou("Gobou", Type.WATER, 50),

    Bulbizarre("Bulbizarre", Type.GRASS, 45),
    Salamèche("Salamèche", Type.FIRE, 39),
    Carapuce("Carapuce", Type.WATER, 44);

    String name;
    Type type;
    int PV;
    Move[] moves = new Move[4];

    StarterPokemons(String name, Type type, int pv) {
        this.name = name;
        this.type = type;
        this.PV = pv;
    }
    StarterPokemons(String name, Type type, int pv, Move[] moves) {
        this.name = name;
        this.type = type;
        this.PV = pv;
        this.moves = moves;
    }


    public Pokemon buildPokemon(){
        return new Pokemon(this);
    }
}
