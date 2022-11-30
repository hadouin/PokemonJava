package com.hadouin.utils;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.*;

public class GUI extends Application implements InputParser {

    static class IntPrompt {
        private final int result;

        IntPrompt(){
            final Stage dialog = new Stage();
            dialog.setTitle("Enter an Integer");

            final TextField input = new TextField();
            // make text field numeric
            input.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue < ? extends String > observable, String oldValue, String newValue){
                    if (!newValue.matches("\\d*")) {
                        input.setText(newValue.replaceAll("[^\\d]", ""));
                    }
                }
            });
            // handle input
            input.setOnAction( new EventHandler<ActionEvent>() {
                @Override
                public void handle (ActionEvent e){
                    // enter has been pressed in the text field.
                    // take whatever action has been required here.
                    dialog.close();
                }
            });

            final Button submit = new Button("submit");
            submit.setDefaultButton(true);

            final VBox layout = new VBox(15, input, submit);
            layout.setPadding(new Insets(15));

            dialog.setScene(new Scene(layout));
            dialog.showAndWait();

            result = Integer.parseInt(input.getText());
        }
        public int getResult() {
            return result;
        }
    }
    @Override
    public int promptInt(String message) {
        IntPrompt prompt = new IntPrompt();
        return prompt.getResult();
    }

    static class ButtonChoices {
        private Button selectedButton;
        final int result;
        ButtonChoices(String[] choiceStrings) {
            // Create pane
            GridPane gp = new GridPane();
            gp.setPadding( new Insets(10) );
            gp.setHgap( 4 );
            gp.setVgap( 8 );

            // Create Dialog
            final Stage dialog = new Stage();
            dialog.setTitle("Choose an option");

            int x = 0;
            int y = 0;
            for (String string : choiceStrings) {
                // create a button
                Button button = new Button();
                button.setText(string);
                button.setOnAction(e -> {
                    buttonClick(button);
                    dialog.close();
                });
                //add it to the grid
                gp.add(button, x, y);
                x++;
                if (x > 1) {
                    y++;
                    x = 0;
                }
            }

            dialog.setScene(new Scene(gp));
            dialog.showAndWait();

            result = Arrays.asList(choiceStrings).indexOf(this.selectedButton.getText());

        }
        private void buttonClick(Button button){
            selectedButton = button;
        }
        private int getResult() {
            return this.result;
        }
    }
    @Override
    public int chooseStringIndex(String message, String[] choices) {
        ButtonChoices buttonChoices = new ButtonChoices(choices);
        return buttonChoices.getResult();
    }
    @Override
    public char promptChar(String message) {
        return 0;
    }


    static class WordPrompt {
        final String result;
        WordPrompt(){
            Stage dialog = new Stage();
            dialog.setTitle("Enter a string");
            final TextField input = new TextField();
            input.setOnAction(e -> {
                dialog.close();
            });

            final VBox layout = new VBox(15, input);
            layout.setPadding(new Insets(15));

            dialog.setScene(new Scene(layout));
            dialog.showAndWait();

            result = input.getText();
        }

        public String getResult() {
            return result;
        }
    }
    @Override
    public String promptWord(String message) {
        WordPrompt wp = new WordPrompt();
        return wp.getResult();
    }

    @Override
    public boolean promptBoolean(String message) {
        ButtonChoices bc = new ButtonChoices(new String[]{"Yes", "No"});
        return bc.getResult() == 0;
    }

    @Override
    public void println(String string) {

    }

    @Override
    public void print(String string) {

    }


    @Override
    public void start(Stage stage) throws Exception {

    }
}
