package com.hadouin.pokemon.core;

import com.hadouin.pokemon.Main;
import com.hadouin.utils.GUI;
import com.hadouin.utils.InputParser;
import javafx.scene.image.Image;

public class Pokemon {
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

    Pokemon(String name, Type type, int pv, Attack[] attacks){
        this.name = name;
        this.type = type;
        this.PV = pv;
        this.maxPV = pv;
        this.attacks = attacks;
        this.front = new Image(Main.class.getResourceAsStream("PokemonSprites/"+this.name+"/front.png"));
        this.back = new Image(Main.class.getResourceAsStream("PokemonSprites/"+this.name+"/back.png"));
    }

    Pokemon(StarterPokemons starter){
        this(starter.name, starter.type, starter.PV, starter.attacks);
        this.LVL = 5;
    }

    public boolean isFainted() {
        return this.PV <= 0;
    }

    public void askNickname() {
        InputParser gui = new GUI();
        if (gui.promptBoolean("Do you want to give a name to your pokemon")){
            this.name = gui.promptWord("What should we call your new friend ?");
        }
    }

    public int losePV(int pv) {
        if (this.PV == 0){
            return 0;
        }
        this.PV -= pv;
        if (this.PV < 0){
            int lostPV = pv + this.PV;
            this.PV = 0;
            return lostPV;
        }
        return pv;
    }

    public void gainPV(int pv) {
        this.PV += pv;
        if (this.PV > this.maxPV){
            this.PV = this.maxPV;
        }
    }

    public Type getType() {
        return this.type;
    }
    public Image getImageFront(){
        return this.front;
    }
    public Image getImageBack(){
        return this.back;
    }

    public int getLVL(){
        return this.LVL;
    }
    public Attack[] getAttacks() {
        return this.attacks;
    }

    public int getPV() {
        return this.PV;
    }

    public int getMaxPV() {
        return this.maxPV;
    }

}
