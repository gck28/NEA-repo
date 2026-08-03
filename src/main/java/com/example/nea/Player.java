package com.example.nea;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


// class for the player and all their interactions and movements

public class Player {
    // create sprite images
    ImageView sprite;
    Image static_sprite = new Image(new FileInputStream("Assets/player_sprite.png"));
    Image moving_sprite = new Image(new FileInputStream("Assets/player_moving_sprite.gif"));

    // sprite starting cords
    double map_x = 600;
    double map_y = 300;


    // booleans to for movement
    boolean w_pressed = false;
    boolean a_pressed = false;
    boolean s_pressed = false;
    boolean d_pressed = false;

    Game game;

    public Player(Game game) throws FileNotFoundException {
        this.game = game;

        // control sprite appearance and add key detection for the Game scene
        sprite = new ImageView(static_sprite);
        sprite.setPreserveRatio(true);
        sprite.setFitWidth(30);
        game.scene.setOnKeyPressed(this::keyPressed);
        game.scene.setOnKeyReleased(this::keyReleased);
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
    public void updatePlayer(){
        movePlayer();
        collisionDetection();
    }

    private void movePlayer() {
        // create movement speed normally and movement speed for the tilemap (moves by a decimal amount)
        double normal_speed = (double) 3 ;//game.bg.tile_width;

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

        // display player
        sprite.setX(map_x);
        sprite.setY(map_y);

        // switch player from gif to png and back
        if (w_pressed || a_pressed || s_pressed || d_pressed){
            sprite.setImage(moving_sprite);
        } else {
            sprite.setImage(static_sprite);
        }
    }

    // collision detection with walls and sprites
    private void collisionDetection() {
    }
}
