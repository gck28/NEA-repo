package com.example.nea;

public class Blackjack extends Games {
    public Blackjack(Main main) {
        super(main);
    }

    @Override
    void playGame(){
        super.playGame();
        System.out.println("Playing Blackjack");
    }
}
