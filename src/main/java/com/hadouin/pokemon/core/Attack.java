package com.hadouin.pokemon.core;

public class Attack {
    private String name;
    private int power;
    private int PP;
    private int maxPP;
    private Type type = Type.NORMAL;
    private BaseAttack baseAttack;

    Attack(String name, int power, int pp, Type type, BaseAttack baseAttack) {
        this.name = name;
        this.power = power;
        this.PP = pp;
        this.maxPP = pp;
        this.type = type;
        this.baseAttack = baseAttack;
    }
    Attack(BaseAttack baseAttack) {
        this(baseAttack.name, baseAttack.power, baseAttack.PP, baseAttack.type, baseAttack);
    }

    public String getName() {
        return this.name;
    }
    public int getPower() {
        return this.power;
    }
    public String getAttackFactorString(Pokemon defender) {
        return this.baseAttack.getAttackFactorString(defender);
    }
    public void cast(Pokemon attacker,Pokemon defender){
        this.baseAttack.cast(attacker, defender);
    }
}
