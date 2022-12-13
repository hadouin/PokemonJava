package com.hadouin.pokemon.core;

public enum Species {
    Tiplouf("Tiplouf", Type.WATER, new short[]{53, 51, 53, 61, 56, 40}, 66, new Move[]{BaseMove.POUND.buildAttack(), BaseMove.BUBBLES.buildAttack()}),
    Ouisticram("Ouisticram", Type.FIRE, new short[]{44, 58, 44, 58, 44, 61}, 65, new Move[]{BaseMove.SCRATCH.buildAttack(), BaseMove.EMBER.buildAttack()}),
    Tortipouss("Tortipouss", Type.GRASS, new short[]{55, 68, 64, 45, 55, 31}, 64, new Move[]{BaseMove.TACKLE.buildAttack(), BaseMove.ABSORB.buildAttack()}),

    Arcko("Arcko", Type.GRASS, new short[]{44,58,44,58,44,61}),
    Poussifeu("Poussifeu", Type.FIRE, new short[]{44,58,44,58,44,61} ),
    Gobou("Gobou", Type.WATER, new short[]{44,58,44,58,44,61}),

    Bulbizarre("Bulbizarre", Type.GRASS, new short[]{44,58,44,58,44,61}),
    Salamèche("Salamèche", Type.FIRE, new short[]{44,58,44,58,44,61}),
    Carapuce("Carapuce", Type.WATER, new short[]{44,58,44,58,44,61});

    public final short[] stats;
    final String name;
    final Type type;
    Move[] moves;
    int baseXP;

    Species(String name, Type type, short[] stats) {
        this.name = name;
        this.type = type;
        this.stats = stats;
    }
    Species(String name, Type type, short[] stats, int baseXP , Move[] moves) {
        this.name = name;
        this.type = type;
        this.stats = stats;
        this.baseXP = baseXP;
        this.moves = moves;
    }
}
