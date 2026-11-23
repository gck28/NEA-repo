package com.example.nea;

import java.util.ArrayList;

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