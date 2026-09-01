package com.example.nea;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GameSprites {
    Casino casino;

    ImageView sprite;

    Scene scene;

    int map_x;
    int map_y;


    public  GameSprites (Casino casino, String sprite_path, Scene scene, int map_x, int map_y) throws FileNotFoundException {
        this.casino = casino;

        // the scene which is switched to when playing the casino
        this.scene = scene;

        // create the sprite for the table
        this.sprite = new ImageView(new Image(new FileInputStream(sprite_path)));
        this.sprite.setPreserveRatio(true);

        this.map_x = map_x;
        this.map_y = map_y;

        // set the position on the map
        sprite.setX(map_x);
        sprite.setY(map_y);
    }
}
