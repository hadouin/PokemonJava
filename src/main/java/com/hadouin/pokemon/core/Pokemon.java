package com.hadouin.pokemon.core;

import com.hadouin.pokemon.Main;
import com.hadouin.utils.GUI;
import com.hadouin.utils.InputParser;
import javafx.scene.image.Image;

public class Pokemon {
    private String name;
    private Type type;

    private short[] stats, battleStats;
    private int XP = 0;

    private Move[] moves;
    private Image back;
    private Image front;
    private Species specie;

    private void calculateStats() {
        for (int i = 0; i < Stat.values().length; i++){
            this.stats[i] = Stat.values()[i].formula(this.specie.stats[i], (byte) 15, (short) 0, this.getLVL());
        }
    }

    Pokemon(Species starter){
        this.specie = starter;
        this.name = starter.name;
        this.type = starter.type;
        this.moves = starter.moves;
        this.XP = 216;
        this.stats = new short[Stat.values().length];
        calculateStats();
        this.battleStats = stats;
        this.front = new Image(Main.class.getResourceAsStream("PokemonSprites/"+this.name+"/front.png"));
        this.back = new Image(Main.class.getResourceAsStream("PokemonSprites/"+this.name+"/back.png"));
    }

    public boolean isFainted() {
        return this.battleStats[Stat.PV.ordinal()] <= 0;
    }

    public void askNickname() {
        InputParser gui = new GUI();
        if (gui.promptBoolean("Do you want to give a name to your pokemon")){
            this.name = gui.promptWord("What should we call your new friend ?");
        }
    }

    public int losePV(int pv) {
        if (this.battleStats[Stat.PV.ordinal()] == 0){
            return 0;
        }
        this.battleStats[Stat.PV.ordinal()] -= pv;
        if (this.battleStats[Stat.PV.ordinal()] < 0){
            int lostPV = pv + this.battleStats[Stat.PV.ordinal()];
            this.battleStats[Stat.PV.ordinal()] = 0;
            return lostPV;
        }
        return pv;
    }

    public void gainPV(int pv) {
        this.battleStats[0] += pv;
        if (this.battleStats[0] > this.stats[Stat.PV.ordinal()]){
            this.battleStats[0] = this.stats[Stat.PV.ordinal()];
        }
    }

    public String getName() {
        return this.name;
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

    public byte getLVL(){
        int[] myXpTable = Levels.FAST.getTableXpLevel();
        // get first value that is not reached
        for (int i = 0; i<myXpTable.length; i++){
            if (myXpTable[i]> this.XP) {
                return (byte) (i - 1);
            }
        }
        return 100;
    }
    public Move[] getAttacks() {
        return this.moves;
    }

    public int getPV() {
        return this.battleStats[Stat.PV.ordinal()];
    }

    public int getMaxPV() {
        return this.stats[Stat.PV.ordinal()];
    }

}
