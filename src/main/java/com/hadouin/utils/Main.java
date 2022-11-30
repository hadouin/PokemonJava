package com.hadouin.utils;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        InputParser gui = new GUI();
        int input = gui.promptInt("enter an integer");
        System.out.println(input);
        String[] choices = {"rouge", "vert", "bleu", "jaune"};
        int chosenIndex = gui.chooseStringIndex("choose", choices);
        System.out.println(choices[chosenIndex]);
    }
}