package com.example.nea;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

class Games {
    Main main;

    VBox root;
    Scene scene;

    Button startGame;

    // default game function which loads into the start screen for each minigame
    public Games(Main main){
        this.main = main;

        root = new VBox();
        root.setAlignment(Pos.CENTER);
        scene = new Scene(root, main.width, main.height);

        startGame = new Button("Press to start game");
        startGame.setOnAction(event -> playGame());

        root.getChildren().addAll(startGame);

        main.switchScene(scene);
    }

    // the code for the minigame which is @
    void playGame() {
        root.getChildren().clear();
    }
}
