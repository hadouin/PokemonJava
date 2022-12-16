package com.hadouin.pokemon.core;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public enum Species {
    PrinPlouf("Prinplouf", Type.WATER, new short[]{64, 66, 68, 81, 76, 50}, 142,convertToMap(new int[]{16}, new Move[]{Move.METAL_CLAW})),
    Tiplouf("Tiplouf", Type.WATER, new short[]{53, 51, 53, 61, 56, 40}, 66,convertToMap(new int[]{1,1}, new Move[]{Move.POUND, Move.BUBBLES} ), Species.PrinPlouf, 16),
    Ouisticram("Ouisticram", Type.FIRE, new short[]{44, 58, 44, 58, 44, 61}, 65,convertToMap(new int[]{1,1}, new Move[]{Move.SCRATCH, Move.EMBER} )),
    Tortipouss("Tortipouss", Type.GRASS, new short[]{55, 68, 64, 45, 55, 31}, 64,convertToMap(new int[]{1,1}, new Move[]{Move.TACKLE, Move.ABSORB} )),

    Arcko("Arcko", Type.GRASS, new short[]{44,58,44,58,44,61}),
    Poussifeu("Poussifeu", Type.FIRE, new short[]{44,58,44,58,44,61}),
    Gobou("Gobou", Type.WATER, new short[]{44,58,44,58,44,61}),

    Bulbizarre("Bulbizarre", Type.GRASS, new short[]{44,58,44,58,44,61}),
    Salamèche("Salamèche", Type.FIRE, new short[]{44,58,44,58,44,61}),
    Carapuce("Carapuce", Type.WATER, new short[]{44,58,44,58,44,61});

    final short[] stats;
    final String name;
    final Type type;

    int baseXP;
    Map<Move,Integer> moveSet;
    public int evolvesAt;
    public Species evolvesTo;

    Species(String name, Type type, short[] stats, int baseXP , Map<Move, Integer> moveSet, Species evolvesTo, int evolvesAt ) {
        this.name = name;
        this.type = type;
        this.stats = stats;
        this.baseXP = baseXP;
        this.moveSet = moveSet;
        this.evolvesAt = evolvesAt;
        this.evolvesTo = evolvesTo;
    }

    Species(String name, Type type, short[] stats) {
        this(name, type, stats, 63, null, null, -1);
    }
    Species(String name, Type type, short[] stats, int baseXP , Map<Move, Integer> moveSet) {
        this(name, type, stats, baseXP, moveSet, null, -1);
    }

    private static Map<Move, Integer> convertToMap(int[] a, Move[] b)
    {
        Map<Move, Integer> m = new TreeMap<>();

        for(int i = 0; i < a.length; i++)
        {
                m.put(b[i], a[i]);
        }

        return m;
    }
}
