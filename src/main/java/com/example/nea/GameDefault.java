package com.example.nea;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

class GameDefault {
    Main main;

    VBox root;
    Scene scene;

    Button startGame;

    // default game function which loads into the start screen for each minigame
    public GameDefault(Main main){
        this.main = main;

        root = new VBox();
        scene = new Scene(root);

        startGame = new Button("Press to start game");
        startGame.setOnAction(event -> playGame());

        root.getChildren().addAll();

        main.switchScene(scene);
    }

    // the code for the minigame which is @
    void playGame() {
        root.getChildren().clear();
    }
}
