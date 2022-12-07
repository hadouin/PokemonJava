package com.hadouin.pokemon;

import com.hadouin.utils.GUI;
import com.hadouin.utils.InputParser;
import javafx.scene.image.Image;

public class Pokemon {
    public Attack[] getAttacks() {
        return this.attacks;
    }

    public int getPV() {
        return this.PV;
    }

    public int getMaxPV() {
        return this.maxPV;
    }

    public void losePV(int power) {
        this.PV -= power;
        if (this.PV < 0 ){
            this.PV = 0;
        }
    }

    enum Starters {
        Tiplouf("Tiplouf", Type.WATER, 53, new Attack[]{Attack.Base.POUND.buildAttack(), Attack.Base.TACKLE.buildAttack()}),
        Ouisticram("Ouisticram",Type.FIRE, 44, new Attack[]{Attack.Base.POUND.buildAttack()}),
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
        private Attack[] attacks = new Attack[4];

        Starters(String name, Type type, int pv) {
            this.name = name;
            this.type = type;
            this.PV = pv;
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
    private int maxPV;
    private int XP = 0;
    private int LVL;

    private Attack[] attacks;
    private Image back;
    private Image front;
    public Image getImageFront(){
        return this.front;
    }
    public Image getImageBack(){
        return this.back;
    }
    public int getLVL(){
        return this.LVL;
    }

    Pokemon(String name, Type type, int pv, Attack[] attacks){
        this.name = name;
        this.type = type;
        this.PV = pv;
        this.maxPV = pv;
        this.attacks = attacks;
        this.front = new Image(this.getClass().getResourceAsStream("PokemonSprites/"+this.name+"/front.png"));
        this.back = new Image(this.getClass().getResourceAsStream("PokemonSprites/"+this.name+"/back.png"));
    }

    public void askNickname() {
        InputParser gui = new GUI();
        if (gui.promptBoolean("Do you want to give a name to your pokemon")){
            this.name = gui.promptWord("What should we call your new friend ?");
        }
    }

    Pokemon(Starters starter){
        this(starter.name, starter.type, starter.PV, starter.attacks);
        this.LVL = 5;
    }

}
