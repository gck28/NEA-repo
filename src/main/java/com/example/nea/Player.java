package com.example.nea;

import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;


// class for the player and all their interactions and movements

public class Player {
    // create sprite images
    ImageView sprite;
    final Image static_sprite = new Image(new FileInputStream("Assets/player_sprite.png"));
    final Image moving_sprite = new Image(new FileInputStream("Assets/player_moving_sprite.gif"));

    // sprite starting cords
    double map_x = 600;
    double map_y = 300;

    // sprite coordinates for correction in collision detection
    double prev_map_x= map_x;
    double prev_map_y = map_y;

    // booleans to for movement
    boolean w_pressed = false;
    boolean a_pressed = false;
    boolean s_pressed = false;
    boolean d_pressed = false;

    Casino casino;

    public Player(Casino casino) throws FileNotFoundException {
        this.casino = casino;

        // control sprite appearance and add key detection for the Game scene
        sprite = new ImageView(static_sprite);
        sprite.setPreserveRatio(true);
        sprite.setFitWidth(30);
        sprite.setFitHeight(64.3);
        casino.scene.setOnKeyPressed(this::keyPressed);
        casino.scene.setOnKeyReleased(this::keyReleased);
    }

    // functions which control key press and key release
    private void keyReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.W){
            w_pressed = false;
        }
        if (event.getCode() == KeyCode.A){
            a_pressed = false;
        }
        if (event.getCode() == KeyCode.S){
            s_pressed = false;
        }
        if (event.getCode() == KeyCode.D){
            d_pressed = false;
        }
    }

    private void keyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.W){
            w_pressed = true;
        }
        if (event.getCode() == KeyCode.A){
            a_pressed = true;
        }
        if (event.getCode() == KeyCode.S){
            s_pressed = true;
        }
        if (event.getCode() == KeyCode.D){
            d_pressed = true;
        }
    }


    // handle player movement and collision detection
    public void updatePlayer(int cam_x, int cam_y){
        movePlayer(cam_x, cam_y);
        collisionDetection(cam_x, cam_y);
    }

    private void movePlayer(int cam_x, int cam_y) {
        // create movement speed normally and movement speed for the tilemap (moves by a decimal amount)
        double normal_speed = (double) 3;

        prev_map_x = map_x;
        prev_map_y = map_y;

        // move the player on the scene and its relative position on tilemap
        if (w_pressed){
            map_y -= normal_speed;
        }
        if (a_pressed){
            map_x -= normal_speed;
            sprite.setScaleX(1);  // switch direction facing left
        }
        if (s_pressed){
            map_y += normal_speed;
        }
        if (d_pressed){
            map_x += normal_speed;
            sprite.setScaleX(-1); // switch direction facing right
        }

        // display player relative to camera position
        sprite.setX(map_x-cam_x);
        sprite.setY(map_y-cam_y);

        // switch player from gif to png and back
        if (w_pressed || a_pressed || s_pressed || d_pressed){
            sprite.setImage(moving_sprite);
        } else {
            sprite.setImage(static_sprite);
        }
    }

    // collision detection with walls and sprites
    private void collisionDetection(int cam_x, int cam_y) {
        // the left and top values of the sprite in terms of the tiles
        int left = (int) (map_x/ casino.bg.tile_width);
        int top = (int) (map_y/ casino.bg.tile_width);

        // calculate the right and bottom values in terms of tiles
        int right = (int) (sprite.getFitWidth() + map_x)/ casino.bg.tile_width;
        int bottom = (int) (sprite.getFitHeight() + map_y)/ casino.bg.tile_width;

        // check to see if the borders of the sprite overlap with a tile of value 1
        if ((casino.bg.tilemap[top][left] == 1) || (casino.bg.tilemap[top][right] == 1) || (casino.bg.tilemap[bottom][left] == 1) || (casino.bg.tilemap[bottom][right] == 1)){
            // reset the values for map_x and map_y
            map_x = prev_map_x;
            map_y = prev_map_y;

            // reset the sprites position on the map
            sprite.setX(map_x-cam_x);
            sprite.setY(map_y-cam_y);
        }

        // check to see if it collides with any of the tables
        ArrayList<GameSprites> tables = casino.game_tables;
        for (GameSprites table : tables) {
            // get bounds for table
            Bounds table_bounds = table.sprite.getBoundsInParent();

            // get bounds for sprite
            Bounds player_bounds = sprite.getBoundsInParent();

            if (player_bounds.intersects(table_bounds)){
                // reset the values for map_x and map_y
                map_x = prev_map_x;
                map_y = prev_map_y;

                // reset the sprites position on the map
                sprite.setX(map_x-cam_x);
                sprite.setY(map_y-cam_y);
            }
        }
    }
}
