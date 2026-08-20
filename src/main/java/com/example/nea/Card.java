package com.example.nea;

public class Card {
    //parameters belonging to each card
    int number;
    String suit;
    int value;
    String colour;

    // set the parameters for each card
    public Card(int number, String suit, int value, String colour){
        this.number = number;
        this.suit = suit;
        this.value = value;
        this.colour = colour;
    }
}
