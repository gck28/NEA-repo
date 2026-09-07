package com.example.nea;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class Blackjack extends Games {
    public Blackjack(Main main) {
        super(main);
    }

    @Override
    public void playGame(){
        super.playGame();

        CardDeck card_deck = new CardDeck();

        ArrayList<Card> player_deck = new ArrayList<>();
        ArrayList<Card> bot1_deck = new ArrayList<>();
        ArrayList<Card> bot2_deck = new ArrayList<>();

        boolean player_bust = false;
        boolean bot1_bust = false;
        boolean bot2_bust = false;



        System.out.println("Playing Blackjack");
    }
}
