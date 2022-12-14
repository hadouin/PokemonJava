package com.hadouin.pokemon.core;

public enum Type {
    // order is important will get factor from table with ordinate
    NORMAL,
    FIRE,
    GRASS,
    WATER,
    STEEL;

    public static final double[][] typeFactor = {
            {1, 1, 1, 1, 0.5},
            {1, 0.5, 2, 0.5, 2},
            {1, 0.5, 0.5, 2, 0.5},
            {1, 2, 0.5, 0.5, 1},
            {1, 0.5, 1, 0.5, 0.5}
    };
}
