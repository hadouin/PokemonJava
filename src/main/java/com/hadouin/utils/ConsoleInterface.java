package com.hadouin.utils;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class ConsoleInterface implements InputParser{
    private final Scanner scanner;
    private final PrintStream out;

    public ConsoleInterface(InputStream in, PrintStream out) {
        scanner = new Scanner(in);
        this.out = out;
    }

    public String promptWord(String message) {
        out.print(message);
        return scanner.next();
    }
    public char promptChar(String message) {
        out.print(message);
        return scanner.next().charAt(0);
    }

    public int promptInt(String message) {
        out.print(message);
        return scanner.nextInt();
    }

    public int chooseStringIndex(String message, String [] entries) {
        out.println(message);
        for (int i = 0; i < entries.length; i++) {
            out.print(i + ". ");
            out.println(entries[i]);
        }
        int choice;
        do {
            choice = promptInt("Your choice: ");
        } while (choice < 0 || choice > entries.length);
        out.println("You chose: " + entries[choice]);
        return choice;
    }

    @Override
    public void println(String string) {
        out.println(string);
    }

    @Override
    public void print(String string) {
        out.print(string);
    }

    public boolean promptBoolean(String message) {
        char choice = promptChar(message + " [Y/n]");
            return !(choice == 'N' || choice == 'n');
    }

}