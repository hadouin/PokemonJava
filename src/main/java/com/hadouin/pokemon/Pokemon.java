package com.hadouin.pokemon;

import com.hadouin.utils.GUI;
import com.hadouin.utils.InputParser;

public class Pokemon {
    public Attack[] getAttacks() {
        return this.attacks;
    }

    enum Starters {
        Tiplouf("Tiplouf", Type.WATER, 53, new Attack[]{Attack.Base.POUND.buildAttack()}),
        Ouisticram("Ouisticram",Type.FIRE, 44),
        Tortipouss("Tortipouss",Type.GRASS, 55, new Attack[]{Attack.Base.TACKLE.buildAttack()}),

        Arcko("Arcko", Type.GRASS, 40),
        Poussifeu("Poussifeu", Type.FIRE, 45 ),
        Gobou("Gobou", Type.WATER, 50),

        Bulbizarre("Bulbizarre", Type.GRASS, 45),
        Salamèche("Salamèche", Type.FIRE, 39),
        Carapuce("Carapuce", Type.WATER, 44);

        private String name;
        private Type type;
        private int PV;
        private Attack[] attacks;


        Starters(String name, Type type, int pv) {
            this.name = name;
            this.type = type;
            this.PV = pv;
            this.attacks = new Attack[4];
        }
        Starters(String name, Type type, int pv, Attack[] attacks) {
            this.name = name;
            this.type = type;
            this.PV = pv;
            this.attacks = attacks;
        }

        Pokemon buildPokemon(){
            return new Pokemon(this);
        }
    }

    private String name;
    public String getName() {
        return this.name;
    }

    private Type type;
    private int PV;
    private Attack[] attacks;

    Pokemon(String name, Type type, int pv, Attack[] attacks){
        this.name = name;
        this.type = type;
        this.PV = pv;
        this.attacks = attacks;
    }

    public void askNickname() {
        InputParser gui = new GUI();
        if (gui.promptBoolean("Do you want to give a name to your pokemon")){
            this.name = gui.promptWord("What should we call your new friend ?");
        }
    }

    Pokemon(Starters starter){
        this(starter.name, starter.type, starter.PV, starter.attacks);
    }

}
