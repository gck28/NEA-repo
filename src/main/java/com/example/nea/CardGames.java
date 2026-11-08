package com.example.nea;

import javafx.animation.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Queue;

class CardGames {
    Main main;

    Pane root;
    Scene scene;

    Button startGame;

    ImageView card_back;
    CardDeck card_deck;

    boolean is_transitioning;
    Queue<SequentialTransition> transitions_queue;


    // default game function which loads into the start screen for each minigame
    public CardGames(Main main){
        this.main = main;

        root = new Pane();
        scene = new Scene(root, main.width, main.height);

        startGame = new Button("Press to start game");
        startGame.setOnAction(event -> {
            try {
                playGame();
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        root.getChildren().addAll(startGame);

        main.switchScene(scene);
    }

    // the code for the minigame which is @Override
    public void playGame() throws IOException, InterruptedException {
        root.getChildren().clear();
        // create a queue and boolean lock for transitions
        transitions_queue = new ArrayDeque<>();
        is_transitioning = false;

        // create a card deck for the games which have cards
        card_deck = new CardDeck();

        // create the background
        Background bg = new Background(main, "Assets/card_game_tilemap.txt");

        // create the card back that is shown at the top right where cards are dealt from
        card_back = new ImageView(new Image(new FileInputStream("Assets/Cards/back_of_card.png")));
        card_back.setPreserveRatio(true);
        card_back.setFitWidth(75);
        card_back.setX(card_deck.deck.getFirst().x_coordinate);
        card_back.setY(card_deck.deck.getFirst().y_coordinate);

        root.getChildren().addAll(bg.canvas, card_back);        // add background and card back to the root
    }

    public Card move_deal_card(int x, int y) {

        Card card = card_deck.dealCard();

        ImageView card_image = new ImageView(card_back.getImage());
        card_image.setX(0);
        card_image.setY(0);
        card_image.setPreserveRatio(true);
        card_image.setFitWidth(75);

        root.getChildren().add(card_image);

        SequentialTransition move_deal = new SequentialTransition(flip_card(card_image, card.card_image), move_card(card_image, x, y));
        transitions_queue.add(move_deal);

        if (transitions_queue.isEmpty()){
            is_transitioning = false;
        }
        if (!is_transitioning){
            playTransition();
        }


        return card;
    }

    // play a transition
    public void playTransition(){
        if (!transitions_queue.isEmpty()){
            is_transitioning = true;
            SequentialTransition transition = transitions_queue.poll();
            assert transition != null;
            transition.play();
            transition.setOnFinished(event -> {
                playTransition();
            });
        }

    }

    // flip the card
    public SequentialTransition flip_card(ImageView card_image, ImageView card_front) {
        ScaleTransition squash = new ScaleTransition(Duration.millis(150), card_image);
        squash.setToX(0);
        ScaleTransition stretch = new ScaleTransition(Duration.millis(150), card_image);
        card_image.setImage(card_front.getImage());
        stretch.setToX(1);
        SequentialTransition squash_stretch = new SequentialTransition(squash, stretch);


        System.out.println("Flipped the card");

        return new SequentialTransition(squash_stretch);
    }

    // move card
    public SequentialTransition move_card(ImageView card_image, int x, int y){
        TranslateTransition move = new TranslateTransition(Duration.seconds(1.5), card_image);
        move.setToX(x);
        move.setByY(y);

        System.out.println("moved card");

        return  new SequentialTransition(move);
    }
}
