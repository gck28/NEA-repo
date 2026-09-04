package com.example.nea;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class Blackjack extends Games {
    public Blackjack(Main main) {
        super(main);
    }

    Scene scene;
    VBox root;

    @Override
    void playGame(){
        super.playGame();

        root = new VBox();
        scene = new Scene(root);

        CardDeck card_deck = new CardDeck();

        System.out.println("Playing Blackjack");
    }
}
