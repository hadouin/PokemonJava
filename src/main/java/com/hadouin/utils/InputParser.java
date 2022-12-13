package com.hadouin.utils;

public interface InputParser {
    public abstract int promptInt(String message);
    public abstract char promptChar(String message);
    public abstract String promptWord(String message);
    public abstract boolean promptBoolean(String message);
    public abstract int chooseStringIndex(String message, String[] choices);
    public abstract void println(String string);
    public abstract void print(String string);
}
