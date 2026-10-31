package com.example.nea;

import javafx.scene.image.ImageView;

public class Card {
    //parameters belonging to each card
    int number;
    String suit;
    int value;
    String colour;
    ImageView card_image;
    int x_coordinate = 997;
    int y_coordinate = 60;

    // set the parameters for each card
    public Card(int number, String suit, int value, String colour, ImageView card_image){
        this.number = number;
        this.suit = suit;
        this.value = value;
        this.colour = colour;
        this.card_image = card_image;
    }
}
