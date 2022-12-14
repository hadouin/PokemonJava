package com.hadouin.pokemon.controls;

import com.hadouin.pokemon.Main;
import com.hadouin.pokemon.core.Pokemon;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;

public class PokemonCard extends AnchorPane {
    private static final String RED_BAR = "red-bar";
    private static final String YELLOW_BAR = "yellow-bar";
    private static final String ORANGE_BAR = "orange-bar";
    private static final String GREEN_BAR = "green-bar";
    private static final String[] barColorStyleClasses = {RED_BAR, ORANGE_BAR, YELLOW_BAR, GREEN_BAR};

    @FXML ProgressBar lifebar;
    @FXML ProgressBar XPBar;
    @FXML Label name;
    @FXML Label lvl;
    @FXML Label PV;
    Pokemon pokemon;

    public PokemonCard() {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("pokemonCard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try
        {
            fxmlLoader.load();
        }
        catch(IOException exception)
        {
            throw new RuntimeException(exception);
        }
    }
    public void initialize() {
        // Set color on lifebar change
        lifebar.progressProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                colorLifeBar(newValue);
            }
        });
    }

    private void setBarStyleClass(ProgressBar bar, String barStyleClass) {
        bar.getStyleClass().removeAll(barColorStyleClasses);
        bar.getStyleClass().add(barStyleClass);
    }
    private void colorLifeBar(Number newValue) {
        double progress = newValue == null ? 0 : newValue.doubleValue();
        if (progress < 0.2) {
            setBarStyleClass(lifebar, RED_BAR);
        } else if (progress < 0.4) {
            setBarStyleClass(lifebar, ORANGE_BAR);
        } else if (progress < 0.6) {
            setBarStyleClass(lifebar, YELLOW_BAR);
        } else {
            setBarStyleClass(lifebar, GREEN_BAR);
        }
    }

    public void updatePV() {
        double progressValue = (double) pokemon.getPV() / pokemon.getMaxPV();
        final Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(0),    new KeyValue(lifebar.progressProperty(), lifebar.progressProperty().getValue())),
                new KeyFrame(Duration.millis(1000), new KeyValue(lifebar.progressProperty(), progressValue))
        );
        timeline.play();
    }
    public void updateXP() {
        double progressValue = getProgressValue();
        final Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(0),    new KeyValue(XPBar.progressProperty(), XPBar.progressProperty().getValue())),
                new KeyFrame(Duration.millis(1000), new KeyValue(XPBar.progressProperty(), progressValue))
        );
        timeline.play();
    }

    public void setXP(){
        double progressValue = getProgressValue();
        XPBar.progressProperty().setValue(progressValue);
    }
    public void setPV(){
        double progressValue = (double) pokemon.getPV() / pokemon.getMaxPV();
        lifebar.progressProperty().setValue(progressValue);
        colorLifeBar(progressValue);
    }
    public void setPokemon(Pokemon pokemon){
        this.pokemon =  pokemon;
        this.name.setText(pokemon.getName().toUpperCase());
        this.lvl.setText("lv." + pokemon.calcLVL());
        this.PV.setText(pokemon.getPV() + "/" + pokemon.getMaxPV());
        setPV();
        setXP();
    }

    private double getProgressValue() {
        double xpProgress = pokemon.getXPProgress();
        double xpToNext = pokemon.getXPtoNext();
        return (double) xpProgress / xpToNext;
    }

    public void updatePokemon(Pokemon pokemon) {
        this.pokemon =  pokemon;
        this.name.setText(pokemon.getName().toUpperCase());
        this.lvl.setText("lv." + pokemon.calcLVL());
        this.PV.setText(pokemon.getPV() + "/" + pokemon.getMaxPV());
        updateXP();
        updatePV();
    }
}
