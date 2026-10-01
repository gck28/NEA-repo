package com.example.nea;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;

import java.util.*;

class Hand {
    ArrayList<Card> hand;
    boolean hand_in;
    int total = 0;
    int ace_count = 0;

    public Hand(){
        hand = new ArrayList<>();
        hand_in = true;
    }

    // find total of the hand
    public int find_total(){
        for (Card card : hand) {
            total = 0;
            total += card.value;

            // if the value is 1, then an ace is in the deck, so increment ace_count and add 10 to total
            if (card.number == 1){
                ace_count += 1;
                total += 10;
            }
        }
        return total;
    }

    // checks to see if the ace can be 11 instead of 1
    public int ace_correction(Hand ace_correction_hand){
        for (int i = 0; i < ace_count; i++) {
            if (ace_correction_hand.total > 21){
                ace_correction_hand.total -= 10;
            } else {
                return total;
            }
        }
        return total;
    }
}

public class Blackjack extends Games {
    Random random = new Random();

    public Blackjack(Main main) {
        super(main);
    }

    Scene scene;
    VBox root;

    Scanner scan;

    public void botMove(int total, boolean ace_present){
        // create hashmap which changes probability of a bot hit
        Dictionary<Integer, Integer> probabilities =  new Hashtable<>();
        probabilities.put(14, 100);
        probabilities.put(15, 70);
        probabilities.put(16, 55);
        probabilities.put(17, 30);
        probabilities.put(18, 15);
        probabilities.put(19, 7);
        probabilities.put(20, 2);
    }

    @Override
    public void playGame(){
        super.playGame();

        root = new VBox();
        scene = new Scene(root);

        // shuffled deck
        CardDeck card_deck = new CardDeck();

        // three players, player + 2 bots
        Hand player_hand = new Hand();
        Hand bot1_hand = new Hand();
        Hand bot2_hand = new Hand();

        while (player_hand.hand_in || bot1_hand.hand_in || bot2_hand.hand_in){
            // deal first 2 cards for each player
            for (int i = 0; i < 3; i++) {
                player_hand.hand.add(card_deck.dealCard());
                bot1_hand.hand.add(card_deck.dealCard());
                bot2_hand.hand.add(card_deck.dealCard());
            }

            if (player_hand.hand_in){
                // give player the choice
                System.out.println(player_hand.hand);
                System.out.println("Hit or Stand:  ");
                String move = scan.next();

                // if they hit
                if (move.equals("h")){
                    player_hand.hand.add(card_deck.dealCard());
                } else {
                    player_hand.hand_in = false; // if they stand, remove it from the deck until dealer checks
                }

                player_hand.total = player_hand.find_total();
                player_hand.total = player_hand.ace_correction(player_hand);
            }

            if (bot1_hand.hand_in){
                if (bot1_hand.total < 14){
                    bot1_hand.hand.add(card_deck.dealCard());
                }
            }
            // TODO bot move, create a dictionary with totals and probability of a hit, which decides what the bot will do, then deal the card.


        }


        System.out.println("Playing Blackjack");
    }
}
