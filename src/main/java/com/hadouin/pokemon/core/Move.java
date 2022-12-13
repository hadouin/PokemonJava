package com.hadouin.pokemon.core;

public class Move {
    private String name;
    private int power;
    private int PP;
    private int maxPP;
    private Type type = Type.NORMAL;
    private BaseMove baseMove;

    Move(String name, int power, int pp, Type type, BaseMove baseMove) {
        this.name = name;
        this.power = power;
        this.PP = pp;
        this.maxPP = pp;
        this.type = type;
        this.baseMove = baseMove;
    }
    Move(BaseMove baseMove) {
        this(baseMove.name, baseMove.power, baseMove.PP, baseMove.type, baseMove);
    }

    public String getName() {
        return this.name;
    }
    public int getPower() {
        return this.power;
    }
    public String getAttackFactorString(Pokemon defender) {
        return this.baseMove.getAttackFactorString(defender);
    }
    public void cast(Pokemon attacker,Pokemon defender){
        this.baseMove.cast(attacker, defender);
        if (defender.isFainted()){
            attacker.earnXP(defender.getXPGain());
        }
    }
}
