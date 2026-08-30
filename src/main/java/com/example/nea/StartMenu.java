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
        // create root scene
        this.main = main;

        root = new VBox();
        root.setAlignment(Pos.CENTER);

        scene = new Scene(root, main.width, main.height);

        // create label and button

        Label label = new Label("Press button to start the game");
        Button button = new Button("Start");

        // switch scene if button is clicked
        button.setOnAction(event -> {
            try {
                main.switchScene(new Casino(this.main).scene);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        root.getChildren().addAll(label, button);
    }
}
