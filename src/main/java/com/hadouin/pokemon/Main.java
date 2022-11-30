package com.hadouin.pokemon;

import com.hadouin.utils.ConsoleInterface;
import com.hadouin.utils.GUI;
import com.hadouin.utils.InputParser;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.lang.reflect.Array;
import java.util.*;

public class Main extends Application {
    // static InputParser inputParser = new ConsoleInterface(System.in, System.out);
    static InputParser inputParser = new GUI();

    private static Pokemon choosePokemon(Pokemon[] pokemons){
        Random random = new Random();

        // get Pokemon names
        List<String> pokemonNameList = new ArrayList<String>();
        for (Pokemon pokemon : pokemons) {
            pokemonNameList.add(pokemon.getName());
        }

        // Remove random pokemon
        int removeIndex = random.nextInt(3);
        pokemonNameList.remove(removeIndex);

        String[] pokemonNamesArray = pokemonNameList.toArray(String[]::new);
        int chosenIndex = inputParser.chooseStringIndex("Which pokemon do you want to choose?", pokemonNamesArray);

        return pokemons[chosenIndex];
    }
    private static Professor chooseProfessorByIndex() {
        List<Professor> profsDB = Arrays.asList(Professor.CHEN, Professor.SORBIER, Professor.SEKO);
        Iterator<Professor> pIt = profsDB.iterator();
        List<String> profNames = new ArrayList<>();
        while (pIt.hasNext()){
            profNames.add(pIt.next().getName());
        }

        int chosenIndex = inputParser.chooseStringIndex("Who is your professor?", profNames.toArray(String[]::new));

        return profsDB.get(chosenIndex);
    }

    @Override
    public void start(Stage stage) throws Exception {
        // sampleRun1();
        Player player1 = new Player("Hadouin");
        player1.addPokemon(Pokemon.Starters.Tiplouf.buildPokemon());
        Player player2 = new Player("Sacha");
        player2.addPokemon(Pokemon.Starters.Tortipouss.buildPokemon());

        Battle battle = new Battle(player1, player2);
        battle.start(stage);
    }

    private static void sampleRun1() {
        Player player = new Player("Hadouin");
        Professor prof = chooseProfessorByIndex();
        Pokemon pokemon = choosePokemon(prof.getPokemons());
        pokemon.askNickname();
        player.addPokemon(pokemon);
        System.out.println(player.getPokemons().get(0).getName());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
