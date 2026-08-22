
package com.example.nea;


import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

/**
 * Example: a house map LARGER than the visible window, split into
 * separate rooms by interior walls, with a player sprite you move via
 * arrow keys. The camera follows the player and clamps at the map
 * edges. Walls are solid (the player collides with them); doors are
 * gaps in the walls that let the player pass between rooms.
 */
public class Test extends Application {

    public void start(Stage stage) throws FileNotFoundException {
        stage = new Stage();
        VBox root = new VBox();
        Scene scene = new Scene(root);

        ImageView image = new ImageView(new Image(new FileInputStream("Assets/Cards/01_kerenel_Cards.png.gif")));

        root.getChildren().addAll(image);
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}