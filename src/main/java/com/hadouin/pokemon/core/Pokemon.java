package com.hadouin.pokemon.core;

import com.hadouin.pokemon.Main;
import com.hadouin.utils.GUI;
import com.hadouin.utils.InputParser;
import javafx.scene.image.Image;

import java.util.Arrays;
import java.util.Map;

public class Pokemon {
    private String name;
    private Type type;

    private short[] stats, battleStats;
    private int XP = 0;
    private byte LVL;

    private Move[] moves;
    private Image back;
    private Image front;
    private Species specie;

    Pokemon(Builder builder){
        specie = builder.specie;
        name = specie.name;
        type = specie.type;
        XP = builder.XP;
        this.LVL = this.calcLVL();
        moves = getBaseMoves();
        this.stats = new short[Stat.values().length];
        calculateStats(this.calcLVL());
        this.battleStats = stats.clone();
        this.front = getFrontImage();
        this.back = getBackImage();
    }

    private Move[] getBaseMoves() {
        Move[] possiblemoves = new Move[4];
        int i = 0;
        for ( Map.Entry<BaseMove, Integer> baseMoveEntry : specie.moveSet.entrySet()) {
            if (baseMoveEntry.getValue() <= this.LVL && i < 4){
                possiblemoves[i] = baseMoveEntry.getKey().buildAttack();
                i++;
            }
        }
        return possiblemoves;

    }

    private Image getFrontImage() {
        return new Image(Main.class.getResourceAsStream("PokemonSprites/" + specie.name + "/front.png"));
    }

    private Image getBackImage() {
        return new Image(Main.class.getResourceAsStream("PokemonSprites/" + specie.name + "/back.png"));
    }

    Pokemon(Species evolveTo, Pokemon p){
        this.specie = evolveTo;
        name = specie.name;
        type = specie.type;
        XP = p.XP;
        this.LVL = this.calcLVL();
        moves = getBaseMoves();
        this.stats = p.stats;
        calculateStats(this.LVL);
        this.battleStats = stats.clone();
        this.front = getFrontImage();
        this.back = getBackImage();
    }

    public static class Builder {
        // Params obligatoires
        private final Species specie;
        // Params facultatifs
        private int XP = 0; // set to lvl 1 by default;

        public Builder(Species specie) {
            this.specie = specie;
        }
        public Builder baseXP(int XP){
            this.XP = XP; return this;
        }
        public Pokemon build(){
            return new Pokemon(this);
        }
    }

    // ALL about XP
    public void earnXP(int xpGain) {
        this.XP += xpGain;
        this.calculateStats(this.calcLVL());
    }
    public int getXPtoNext(){
        byte lvl = this.calcLVL();
        return Levels.FAST.tableXpLevel[lvl + 1] - Levels.FAST.tableXpLevel[lvl];
    }
    public int getXPforLevel(byte lvl){
        return Levels.FAST.tableXpLevel[lvl];
    }
    public int getXPProgress(){
        return this.XP - this.getXPforLevel(this.calcLVL());
    }
    public byte calcLVL(){
        if (this.XP == 0){
            return 1;
        }
        int[] myXpTable = Levels.FAST.getTableXpLevel();
        // get first value that is not reached
        for (int i = 0; i < myXpTable.length; i++){
            if (myXpTable[i] > this.XP) {
                return (byte) (i - 1);
            }
        }
        return 1;
    }

    public int getXPGain(){
        return (this.specie.baseXP * this.calcLVL()) / 7;
    }

    private void calculateStats(byte lvl) {
        for (int i = 0; i < Stat.values().length; i++){
            this.stats[i] = Stat.values()[i].formula(this.specie.stats[i], (byte) 15, (short) 0, lvl);
        }
    }

    // ALL about PV
    public boolean isFainted() {
        return this.battleStats[Stat.PV.ordinal()] <= 0;
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


    public void askNickname() {
        InputParser gui = new GUI();
        if (gui.promptBoolean("Do you want to give a name to your pokemon")){
            this.name = gui.promptWord("What should we call your new friend ?");
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
    public Move[] getAttacks() {
        return this.moves;
    }

    public int getPV() {
        return this.battleStats[Stat.PV.ordinal()];
    }
    public int getMaxPV() {
        return this.stats[Stat.PV.ordinal()];
    }
    public short getStat(int ordinal) {
        return this.battleStats[ordinal];
    }

}
