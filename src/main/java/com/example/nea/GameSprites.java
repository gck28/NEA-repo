package com.example.nea;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GameSprites {
    Game game;

    ImageView sprite;

    Scene scene;

    int map_x;
    int map_y;


    public  GameSprites (Game game, String sprite_path, Scene scene, int map_x, int map_y) throws FileNotFoundException {
        this.game = game;

        // the scene which is switched to when playing the game
        this.scene = scene;

        // create the sprite for the table
        this.sprite = new ImageView(new Image(new FileInputStream(sprite_path)));
        this.sprite.setPreserveRatio(true);

        this.map_x = map_x;
        this.map_y = map_y;

        sprite.setX(map_x);
        sprite.setY(map_y);
    }
}
