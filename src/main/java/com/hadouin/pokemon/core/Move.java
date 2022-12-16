package com.hadouin.pokemon.core;

import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.Random;

public enum Move {
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
    METAL_CLAW("Griffe acier", 50 , 35, Type.STEEL );

    final String NAME;
    final int power;
    private byte pp, battlePP;
    final Type type;
    Move(String name, int power, int pp) {
        this.NAME = name;
        this.power = power;
        this.pp = (byte) pp;
        battlePP = (byte) pp;
        this.type = Type.NORMAL;
    }
    Move(String name, int power, int pp, Type type) {
        this.NAME = name;
        this.power = power;
        this.pp = (byte) pp;
        battlePP = (byte) pp;
        this.type = type;
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
        double level = attacker.calcLVL();
        double random = getRandom();
        double type = getTypeFactor(defender);
        double STAB = getSTAB(attacker);

        double baseCalculation = ((((((2 * level ) / 5) + 2) * this.power * (A/D)) / 50) + 2);
        return (short) (baseCalculation * random * type * STAB);
    }

    public void cast(Pokemon attacker, Pokemon defender) {
        int damage = getDamage(attacker, defender);
        defender.losePV(damage);
        if (defender.isFainted()){
            attacker.earnXP(defender.getXPGain());
        }
    }


    public void startAnimation(ImageView attackerImageView, ImageView defenderImageView ) {
        TranslateTransition translateTransition =
                new TranslateTransition(Duration.millis(2000), attackerImageView);
        translateTransition.setFromX(50);
        translateTransition.setToX(375);
        translateTransition.setCycleCount(1);
        translateTransition.setAutoReverse(true);

        SequentialTransition animation = new SequentialTransition(translateTransition);
        animation.play();

    }

    public String getName() {
        return this.NAME;
    }
}
