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
            int attackDamage = (int) (this.power / 2 * getRandom());
            int damageDealt = defender.losePV(attackDamage);
            attacker.gainPV(damageDealt / 2);
        }
    },
    ;
    String name;
    int power;
    int PP;
    Type type;
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

    public void cast(Pokemon attacker, Pokemon defender) {
        int damage = (int) ((this.power / 2) * getRandom() * getTypeFactor(defender)) ;
        defender.losePV(damage);
    }
}
