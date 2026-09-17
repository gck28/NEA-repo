package com.example.nea;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Scanner;

class Hand {
    ArrayList<Card> hand;
    boolean hand_in;
    int total = 0;
    public Hand(){
        hand = new ArrayList<>();
        hand_in = true;
    }

    public int find_total(){
        for (Card card : hand) {
            total = 0;
            total += card.value;
        }

        if (total > 21){
            System.out.println("bust");
        }

        return total;
    }
}

public class Blackjack extends Games {
    public Blackjack(Main main) {
        super(main);
    }

    Scene scene;
    VBox root;

    Scanner scan;

    @Override
    public void playGame(){
        super.playGame();

        root = new VBox();
        scene = new Scene(root);

        CardDeck card_deck = new CardDeck();

        Hand player_hand = new Hand();
        Hand bot1_hand = new Hand();
        Hand bot2_hand = new Hand();

        while (player_hand.hand_in || bot1_hand.hand_in || bot2_hand.hand_in){
            for (int i = 0; i < 3; i++) {
                player_hand.hand.add(card_deck.dealCard());
                bot1_hand.hand.add(card_deck.dealCard());
                bot2_hand.hand.add(card_deck.dealCard());
            }

            System.out.println(player_hand.hand);
            System.out.println("Hit or Stand:  ");
            String move = scan.next();
            if (move.equals("h")){
                player_hand.hand.add(card_deck.dealCard());
            }
            player_hand.total = player_hand.find_total();

        }



        System.out.println("Playing Blackjack");
    }
}
