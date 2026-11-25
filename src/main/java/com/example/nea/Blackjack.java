package com.example.nea;

import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.util.*;

public class Blackjack extends CardGames {
    public Blackjack(Main main){
        super(main);
    }

    Hand player_hand;
    Hand bot1_hand;
    Hand bot2_hand;

    Boolean player_moved = false;

    @Override
    public void playGame() throws IOException, InterruptedException {
        super.playGame();

        // three players, player + 2 bots
        player_hand = new Hand(337);
        bot1_hand = new Hand(577);
        bot2_hand = new Hand(817);

        for (int i = 0; i < 2; i++) {
            player_hand.hand.add(move_deal_card(player_hand));
            System.out.println("Dealt to player");
            bot1_hand.hand.add(move_deal_card(bot1_hand));
            System.out.println("Dealt to bot1");
            bot2_hand.hand.add(move_deal_card(bot2_hand));
            System.out.println("Dealt to bot2");
        }
        scene.setOnKeyPressed(this::move_made);

        // timer to execute the game
        AnimationTimer main_timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (player_hand.hand_in){
                    if (player_moved){
                        bot_moves();
                        player_moved = false;
                    }
                } else{
                    bot_moves();
                }
            }
        };
        main_timer.start();

        //TODO bot move, create a dictionary with totals and probability of a hit, which decides what the bot will do, then deal the card.

    }

    private void move_made(KeyEvent event){
        if (event.getCode() == KeyCode.H){
            player_hand.hand.add(move_deal_card(player_hand));
            System.out.println("Dealt to player");
        } else if (event.getCode() == KeyCode.S){
            player_hand.hand_in = false;
        }

        player_moved = true;
    }

    private void bot_moves() {
            if (bot1_hand.hand_in){
                bot1_hand.hand.add(move_deal_card(bot1_hand));
                System.out.println("Dealt to bot1");
            }
            if (bot2_hand.hand_in){
                bot2_hand.hand.add(move_deal_card(bot2_hand));
                System.out.println("Dealt to bot2");
            }
    }

    @Override

}
