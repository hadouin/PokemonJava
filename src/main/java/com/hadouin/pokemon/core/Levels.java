package com.hadouin.pokemon.core;

import java.util.HashMap;

public enum Levels {
    FAST {
        @Override
        int xpNeeded(int level){
            return (int) Math.pow(level, 3);
        }
    };
    abstract int xpNeeded(int level);

    private final int[] tableXpLevel = new int[100];

    Levels() {
        for (int i = 0; i<100; i++){
            tableXpLevel[i] = xpNeeded(i);
        }
    }

    public int[] getTableXpLevel() {
        return tableXpLevel;
    }
}
