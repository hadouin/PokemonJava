package com.hadouin.pokemon.core;

public enum Stat {
    PV {
        @Override
        short formula(short base, byte IV, short EV, byte level){
            return (short) ((((2*base+IV+EV/4) * level) / 100) + level + 10);
        }
    },
    ATTACK, DEFENSE, SP_ATTACK, SP_DEFENSE, SPEED;

    short formula(short base, byte IV, short EV, byte level){
        return (short) (((((2 * base) + IV + (EV/4)) * level) / 100) + 5);
    }

}
