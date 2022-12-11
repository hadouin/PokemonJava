package com.hadouin.pokemon.core;

public enum StarterPokemons {
    Tiplouf("Tiplouf", Type.WATER, 53, new Attack[]{BaseAttack.POUND.buildAttack(), BaseAttack.BUBBLES.buildAttack()}),
    Ouisticram("Ouisticram",Type.FIRE, 44, new Attack[]{BaseAttack.SCRATCH.buildAttack(), BaseAttack.EMBER.buildAttack()}),
    Tortipouss("Tortipouss",Type.GRASS, 55, new Attack[]{BaseAttack.TACKLE.buildAttack(), BaseAttack.ABSORB.buildAttack()}),

    Arcko("Arcko", Type.GRASS, 40),
    Poussifeu("Poussifeu", Type.FIRE, 45 ),
    Gobou("Gobou", Type.WATER, 50),

    Bulbizarre("Bulbizarre", Type.GRASS, 45),
    Salamèche("Salamèche", Type.FIRE, 39),
    Carapuce("Carapuce", Type.WATER, 44);

    String name;
    Type type;
    int PV;
    Attack[] attacks = new Attack[4];

    StarterPokemons(String name, Type type, int pv) {
        this.name = name;
        this.type = type;
        this.PV = pv;
    }
    StarterPokemons(String name, Type type, int pv, Attack[] attacks) {
        this.name = name;
        this.type = type;
        this.PV = pv;
        this.attacks = attacks;
    }


    public Pokemon buildPokemon(){
        return new Pokemon(this);
    }
}
