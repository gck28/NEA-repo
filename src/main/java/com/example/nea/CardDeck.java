package com.example.nea;

import java.util.ArrayList;
import java.util.Collections;

public class CardDeck {
    int jokers = 2;
    ArrayList<Card> deck = generateDeck();

    // function to generate a standard deck using the Card class
    public ArrayList<Card> generateDeck() {

        ArrayList<Card> deck = new ArrayList<>();
        int number = 1;
        String colour = "black";
        String[] suits = {"spades", "clubs", "diamonds", "hearts"};
        int suit_count = 0;

        for (int i = 0; i < 52; i++) {
            if (number > 13) {
                number = 1;
                suit_count++;
            }

            int value = Math.min(number, 10);

            if (i >= 26) {
                colour = "red";
            }

            Card card = new Card(number, suits[suit_count], value, colour);

            deck.add(card);
            number++;
        }

        // shuffle the deck
        Collections.shuffle(deck);

        return deck;
    }

    // function to deal cards
    public Card dealCard() {

        Card to_return =  deck.getFirst();
        deck.removeFirst();

        return to_return;
    }


}
