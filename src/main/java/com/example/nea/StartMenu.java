package com.example.nea;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;

// start menu

public class StartMenu {
    Scene scene;
    VBox root;

    Main main;

    public StartMenu(Main main){
        this.main = main;
        root = new VBox();
        root.setAlignment(Pos.CENTER);

        scene = new Scene(root, main.width, main.height);

        Label label = new Label("Press button to start the game");
        Button button = new Button("Start");
        button.setOnAction(event -> {
            try {
                switchToGame();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        root.getChildren().addAll(label, button);

    }

    private void switchToGame() throws IOException {
        this.main.stage.setScene(new Game(this.main).scene);
    }
}
