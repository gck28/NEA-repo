package com.example.nea;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

public class CardDeck {
    int jokers = 2;
    ArrayList<Card> deck;

    {
        try {
            deck = generateDeck();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // function to generate a standard deck using the Card class
    public ArrayList<Card> generateDeck() throws FileNotFoundException {
        ArrayList<Card> deck = new ArrayList<>();

        // Hearts
        deck.add(new Card(1, "heart", 1, "Red", new ImageView(new Image(new FileInputStream("Assets/Cards/A_heart.png")))));
        deck.add(new Card(2, "heart", 2, "Red", new ImageView(new Image(new FileInputStream("Assets/Cards/2_heart.png")))));
        deck.add(new Card(3, "heart", 3, "Red", new ImageView(new Image(new FileInputStream("Assets/Cards/3_heart.png")))));
        deck.add(new Card(4, "heart", 4, "Red", new ImageView(new Image(new FileInputStream("Assets/Cards/4_heart.png")))));
        deck.add(new Card(5, "heart", 5, "Red", new ImageView(new Image(new FileInputStream("Assets/Cards/5_heart.png")))));
        deck.add(new Card(6, "heart", 6, "Red", new ImageView(new Image(new FileInputStream("Assets/Cards/6_heart.png")))));
        deck.add(new Card(7, "heart", 7, "Red", new ImageView(new Image(new FileInputStream("Assets/Cards/7_heart.png")))));
        deck.add(new Card(8, "heart", 8, "Red", new ImageView(new Image(new FileInputStream("Assets/Cards/8_heart.png")))));
        deck.add(new Card(9, "heart", 9, "Red", new ImageView(new Image(new FileInputStream("Assets/Cards/9_heart.png")))));
        deck.add(new Card(10, "heart", 10, "Red", new ImageView(new Image(new FileInputStream("Assets/Cards/10_heart.png")))));
        deck.add(new Card(11, "heart", 10, "Red", new ImageView(new Image(new FileInputStream("Assets/Cards/J_heart.png")))));
        deck.add(new Card(12, "heart", 10, "Red", new ImageView(new Image(new FileInputStream("Assets/Cards/Q_heart.png")))));
        deck.add(new Card(13, "heart", 10, "Red", new ImageView(new Image(new FileInputStream("Assets/Cards/K_heart.png")))));

        // Diamonds
        deck.add(new Card(1, "diamond", 1, "Red", new ImageView(new Image(new FileInputStream("Assets/Cards/A_diamond.png")))));
        deck.add(new Card(2, "diamond", 2, "Red", new ImageView(new Image(new FileInputStream("Assets/Cards/2_diamond.png")))));
        deck.add(new Card(3, "diamond", 3, "Red", new ImageView(new Image(new FileInputStream("Assets/Cards/3_diamond.png")))));
        deck.add(new Card(4, "diamond", 4, "Red", new ImageView(new Image(new FileInputStream("Assets/Cards/4_diamond.png")))));
        deck.add(new Card(5, "diamond", 5, "Red", new ImageView(new Image(new FileInputStream("Assets/Cards/5_diamond.png")))));
        deck.add(new Card(6, "diamond", 6, "Red", new ImageView(new Image(new FileInputStream("Assets/Cards/6_diamond.png")))));
        deck.add(new Card(7, "diamond", 7, "Red", new ImageView(new Image(new FileInputStream("Assets/Cards/7_diamond.png")))));
        deck.add(new Card(8, "diamond", 8, "Red", new ImageView(new Image(new FileInputStream("Assets/Cards/8_diamond.png")))));
        deck.add(new Card(9, "diamond", 9, "Red", new ImageView(new Image(new FileInputStream("Assets/Cards/9_diamond.png")))));
        deck.add(new Card(10, "diamond", 10, "Red", new ImageView(new Image(new FileInputStream("Assets/Cards/10_diamond.png")))));
        deck.add(new Card(11, "diamond", 10, "Red", new ImageView(new Image(new FileInputStream("Assets/Cards/J_diamond.png")))));
        deck.add(new Card(12, "diamond", 10, "Red", new ImageView(new Image(new FileInputStream("Assets/Cards/Q_diamond.png")))));
        deck.add(new Card(13, "diamond", 10, "Red", new ImageView(new Image(new FileInputStream("Assets/Cards/K_diamond.png")))));

        // Clubs
        deck.add(new Card(1, "club", 1, "Black", new ImageView(new Image(new FileInputStream("Assets/Cards/A_club.png")))));
        deck.add(new Card(2, "club", 2, "Black", new ImageView(new Image(new FileInputStream("Assets/Cards/2_club.png")))));
        deck.add(new Card(3, "club", 3, "Black", new ImageView(new Image(new FileInputStream("Assets/Cards/3_club.png")))));
        deck.add(new Card(4, "club", 4, "Black", new ImageView(new Image(new FileInputStream("Assets/Cards/4_club.png")))));
        deck.add(new Card(5, "club", 5, "Black", new ImageView(new Image(new FileInputStream("Assets/Cards/5_club.png")))));
        deck.add(new Card(6, "club", 6, "Black", new ImageView(new Image(new FileInputStream("Assets/Cards/6_club.png")))));
        deck.add(new Card(7, "club", 7, "Black", new ImageView(new Image(new FileInputStream("Assets/Cards/7_club.png")))));
        deck.add(new Card(8, "club", 8, "Black", new ImageView(new Image(new FileInputStream("Assets/Cards/8_club.png")))));
        deck.add(new Card(9, "club", 9, "Black", new ImageView(new Image(new FileInputStream("Assets/Cards/9_club.png")))));
        deck.add(new Card(10, "club", 10, "Black", new ImageView(new Image(new FileInputStream("Assets/Cards/10_club.png")))));
        deck.add(new Card(11, "club", 10, "Black", new ImageView(new Image(new FileInputStream("Assets/Cards/J_club.png")))));
        deck.add(new Card(12, "club", 10, "Black", new ImageView(new Image(new FileInputStream("Assets/Cards/Q_club.png")))));
        deck.add(new Card(13, "club", 10, "Black", new ImageView(new Image(new FileInputStream("Assets/Cards/K_club.png")))));

        // Spades
        deck.add(new Card(1, "spade", 1, "Black", new ImageView(new Image(new FileInputStream("Assets/Cards/A_spade.png")))));
        deck.add(new Card(2, "spade", 2, "Black", new ImageView(new Image(new FileInputStream("Assets/Cards/2_spade.png")))));
        deck.add(new Card(3, "spade", 3, "Black", new ImageView(new Image(new FileInputStream("Assets/Cards/3_spade.png")))));
        deck.add(new Card(4, "spade", 4, "Black", new ImageView(new Image(new FileInputStream("Assets/Cards/4_spade.png")))));
        deck.add(new Card(5, "spade", 5, "Black", new ImageView(new Image(new FileInputStream("Assets/Cards/5_spade.png")))));
        deck.add(new Card(6, "spade", 6, "Black", new ImageView(new Image(new FileInputStream("Assets/Cards/6_spade.png")))));
        deck.add(new Card(7, "spade", 7, "Black", new ImageView(new Image(new FileInputStream("Assets/Cards/7_spade.png")))));
        deck.add(new Card(8, "spade", 8, "Black", new ImageView(new Image(new FileInputStream("Assets/Cards/8_spade.png")))));
        deck.add(new Card(9, "spade", 9, "Black", new ImageView(new Image(new FileInputStream("Assets/Cards/9_spade.png")))));
        deck.add(new Card(10, "spade", 10, "Black", new ImageView(new Image(new FileInputStream("Assets/Cards/10_spade.png")))));
        deck.add(new Card(11, "spade", 10, "Black", new ImageView(new Image(new FileInputStream("Assets/Cards/J_spade.png")))));
        deck.add(new Card(12, "spade", 10, "Black", new ImageView(new Image(new FileInputStream("Assets/Cards/Q_spade.png")))));
        deck.add(new Card(13, "spade", 10, "Black", new ImageView(new Image(new FileInputStream("Assets/Cards/K_spade.png")))));

        // shuffle the deck
        Collections.shuffle(deck);
        System.out.println("Successfully created card deck");

        return deck;
    }

    // function to deal cards
    public Card dealCard() {

        Card to_return = deck.getFirst();
        deck.removeFirst();

        return to_return;
    }




}
