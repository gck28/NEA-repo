package com.example.nea;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;

public class Card_deck {
    int jokers = 2;
    ArrayList<Card> deck = generateDeck();

    public void main (String[] args){
        for (int i = 0; i < deck.size(); i++) {
            System.out.print(deck.get(i).number + " ");
            System.out.print(deck.get(i).value + " ");
            System.out.print(deck.get(i).suit + " ");
            System.out.println(deck.get(i).colour + " ");
        };
    }


    // function to generate a standard deck using the Card class
    public ArrayList<Card> generateDeck() {

        ArrayList<Card> deck = new ArrayList<>();
        int number = 1;
        String colour = "black";
        String[] suits = {"spades", "clubs", "diamonds", "hearts"};
        int suit_count = 0;

        for (int i = 0; i < 52; i++) {
            if (number > 13){
                number = 1;
                suit_count++;
            }

            int value = number;

            if (number >= 10){
                value = 10;
            }

            if (i >= 26){
                colour = "red";
            }

            Card card = new Card(number, suits[suit_count], value, colour);

            deck.add(card);
            number++;
        }

        return deck;
    }


}
