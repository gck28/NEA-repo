package com.example.nea;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

class Games {
    Main main;

    Pane root;
    Scene scene;

    Button startGame;

    CardDeck card_deck;

    // default game function which loads into the start screen for each minigame
    public Games(Main main){
        this.main = main;

        root = new Pane();
        scene = new Scene(root, main.width, main.height);

        startGame = new Button("Press to start game");
        startGame.setOnAction(event -> {
            try {
                playGame();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        root.getChildren().addAll(startGame);

        main.switchScene(scene);
    }

    // the code for the minigame which is @Override
    public void playGame() throws IOException {
        root.getChildren().clear();

        // create a card deck for the games which have cards
        card_deck = new CardDeck();
        card_deck.deck = card_deck.generateDeck();
    }

    public Card move_deal_card(int x, int y){
        Card card = card_deck.dealCard();
        AnimationTimer move_card_timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if ((card.x_coordinate == x) && (card.y_coordinate == y)){
                    this.stop();
                }
                int move_x = (x-card.x_coordinate);
                int move_y = (y-card.y_coordinate);
            }
        };
        move_card_timer.start();

        return card;
    }

    public void flip_card(ImageView card_image, String card_front) throws FileNotFoundException {
        ImageView card_front_image = new ImageView(new Image(new FileInputStream(card_front)));

        AnimationTimer flip_card_timer = new AnimationTimer() {
            @Override
            public void handle(long l) {

            }
        };
        flip_card_timer.start();

    }
}
