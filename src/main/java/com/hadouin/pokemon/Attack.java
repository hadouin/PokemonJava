package com.hadouin.pokemon;

public class Attack {
    enum Base {
        POUND("Écras'face", 40, 35),
        TACKLE("CHARGE", 40, 35);
        private String name;
        private int power;
        private int PP;
        Base(String name, int power, int pp) {
            this.name = name;
            this.power = power;
            this.PP = pp;
        }
        public Attack buildAttack(){
            return new Attack(this);
        }
    }
    private String name;
    private int power;
    private int PP;
    private int maxPP;

    public Attack(String name, int power, int pp) {
        this.name = name;
        this.power = power;
        this.PP = pp;
        this.maxPP = pp;
    }
    Attack(Base baseAttack) {
        this(baseAttack.name, baseAttack.power, baseAttack.PP);
    }

    public String getName() {
        return this.name;
    }
}
