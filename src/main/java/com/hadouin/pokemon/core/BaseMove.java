package com.hadouin.pokemon.core;

import java.util.Random;

enum BaseMove {
    POUND("Écras'face", 40, 35),
    SCRATCH("Griffe", 40, 35),
    TACKLE("CHARGE", 40, 35),
    BUBBLES("Bulles d'O", 40, 35, Type.WATER),
    EMBER("Flammèche", 40, 25, Type.FIRE),
    ABSORB("Vol-Vie",20,25, Type.GRASS) {
        @Override
        public void cast(Pokemon attacker, Pokemon defender){
            int damage = getDamage(attacker, defender);
            int damageDealt = defender.losePV(damage);
            attacker.gainPV(damageDealt / 2);
        }
    },
    ;
    final String name;
    final int power;
    final int PP;
    final Type type;
    BaseMove(String name, int power, int pp) {
        this.name = name;
        this.power = power;
        this.PP = pp;
        this.type = Type.NORMAL;
    }
    BaseMove(String name, int power, int pp, Type type) {
        this.name = name;
        this.power = power;
        this.PP = pp;
        this.type = type;
    }
    public Move buildAttack(){
        return new Move(this);
    }

    public double getSTAB(Pokemon attacker){
        if (attacker.getType() == this.type){
            return 1.5;
        }
        return 1;
    }
    public double getRandom(){
        Random r = new Random();
        return r.nextDouble(0.9, 1.1);
    }

    public double getTypeFactor(Pokemon defender){
        return Type.typeFactor[this.type.ordinal()][defender.getType().ordinal()];
    }

    public String getAttackFactorString(Pokemon defender){
        double typeFactor = getTypeFactor(defender);
        if ( typeFactor == 0.5){
            return "Ce n'est pas très efficace ...";
        }
        if (typeFactor == 2) {
            return "C'est super efficace!";
        }
        return "";
    }

    int getDamage(Pokemon attacker, Pokemon defender){
        double A = attacker.getStat(Stat.ATTACK.ordinal());
        double D = defender.getStat(Stat.DEFENSE.ordinal());
        double level = attacker.getLVL();
        double random = getRandom();
        double type = getTypeFactor(defender);
        double STAB = getSTAB(attacker);

        double baseCalculation = ((((((2 * level ) / 5) + 2) * this.power * (A/D)) / 50) + 2);
        return (short) (baseCalculation * random * type * STAB);
    }

    public void cast(Pokemon attacker, Pokemon defender) {
        int damage = getDamage(attacker, defender);
        defender.losePV(damage);
    }


}
