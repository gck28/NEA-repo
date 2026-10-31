package com.example.nea;

import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

import java.io.IOException;
import java.util.*;

class Hand {
    ArrayList<Card> hand;
    boolean hand_in;
    int total = 0;
    int ace_count = 0;
    int hand_x_pos;

    public Hand(int hand_x_pos){
        this.hand_x_pos = hand_x_pos;
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


public class Blackjack extends CardGames {
    boolean first_deals = true;

    public Blackjack(Main main){
        super(main);
    }

    @Override
    public void playGame() throws IOException, InterruptedException {
        super.playGame();

        // three players, player + 2 bots
        Hand player_hand = new Hand(0);
        Hand bot1_hand = new Hand(0);
        Hand bot2_hand = new Hand(0);



        for (int i = 0; i < 2; i++) {
            player_hand.hand.add(move_deal_card(0, 0));
            System.out.println("Dealt to player");
            bot1_hand.hand.add(move_deal_card(0, 0));
            System.out.println("Dealt to bot1");
            bot2_hand.hand.add(move_deal_card(0, 0));
            System.out.println("Dealt to bot2");
        }

        // timer to execute the game
//        AnimationTimer main_timer = new AnimationTimer() {
//            @Override
//            public void handle(long l) {
//
//            }
//        };
//        main_timer.start();

        //TODO bot move, create a dictionary with totals and probability of a hit, which decides what the bot will do, then deal the card.

    }


}
