package com.hadouin.pokemon;

import java.util.Random;

public class Test {
    public static void main(String[] args) {
        for (int i = 0; i<20; i++) {
            System.out.print(randomVariation(20) + " ");
        }
    }
    private static int randomVariation(int damage){
        double tenPercentDamage = damage * 0.1;
        Random r = new Random();
        return  r.nextInt((int) (damage - tenPercentDamage), (int) (damage + tenPercentDamage) + 1);
    }
}
