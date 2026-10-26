package com.example.nea;

import javafx.animation.AnimationTimer;

import java.util.*;

class Hand {
    ArrayList<Card> hand;
    boolean hand_in;
    int total = find_total();
    int ace_count = 0;

    public Hand(){
        hand = new ArrayList<>();
        hand_in = true;
    }

    // find total of the hand
    public int find_total(){
        assert hand != null;
        for (Card card : hand) {
            total += card.value;

            // if the number is 1, then an ace is in the deck, so increment ace_count and add 10 to total
            if (card.number == 1){
                ace_count += 1;
                total += 1; // now value of ace is 11
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
        Background bg = new Background(main, "Assets/blackjack_tilemap.txt");
        System.out.println(bg.tilemap);

        // shuffled deck
        CardDeck card_deck = new CardDeck();
        card_deck.deck = card_deck.generateDeck();

        // three players, player + 2 bots
        Hand player_hand = new Hand();
        Hand bot1_hand = new Hand();
        Hand bot2_hand = new Hand();

        for (int i = 0; i < 2; i++) {
            player_hand.hand.add(card_deck.dealCard());
            bot1_hand.hand.add(card_deck.dealCard());
            bot2_hand.hand.add(card_deck.dealCard());
        }

//        AnimationTimer timer = new AnimationTimer() {
//            @Override
//            public void handle(long l) {
//                if (player_hand.hand_in || bot1_hand.hand_in || bot2_hand.hand_in){
//
//                }
//            }
//        };
//        timer.start();

             //TODO bot move, create a dictionary with totals and probability of a hit, which decides what the bot will do, then deal the card.
        root.getChildren().addAll(bg.canvas);
        System.out.println("added");
    }
}
