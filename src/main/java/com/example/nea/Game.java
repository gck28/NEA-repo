package com.example.nea;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import javax.swing.text.html.ImageView;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;


// main class which has the scene where the player can move around and choose which game they would like to play

public class Game {

    Pane root;
    Scene scene;

    GameDefault blackjack;

    Player player;

    Main main;

    Background bg;

    int cam_x;
    int cam_y;

    ArrayList<GameSprites> game_tables;

    public Game (Main main) throws IOException {
        this.main = main;

        // create root node and scene
        root = new Pane();
        root.setPrefSize(main.width, main.height);
        scene = new Scene(root, main.width, main.height);

        bg = new Background(main); // create background

        player = new Player(this); // create player

        // create an arraylist for all the game tables
        game_tables = new ArrayList<>();

        // create two classes for the blackjack table on the main scene that is interacted with and the actual game that gets switched to
        blackjack = new Blackjack(main);
        GameSprites blackjack_table = new GameSprites(this, "Assets/GameTableSprites/blackjack_table.png", blackjack.scene, 100, 100);

        game_tables.add(blackjack_table);

        // display the player
        player.sprite.setX(player.map_x*bg.tile_width);
        player.sprite.setY(player.map_y*bg.tile_width);

        root.getChildren().addAll(bg.canvas, player.sprite, blackjack_table.sprite);


        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {

                // calculate camera x and y coordinates relative to player
                cam_x = (int) (player.map_x - ((double) main.width /2));
                cam_y = (int) (player.map_y - ((double) main.height /2));

                // calculate maximum camera positions for bound checking
                int max_cam_x = (bg.tilemap[0].length*bg.tile_width) - main.width;
                int max_cam_y = (bg.tilemap.length*bg.tile_width) - main.height;

                // adjust camera in case it forces the displayed background to go beyond the given walls.
                cam_x = checkCam(cam_x, max_cam_x);
                cam_y = checkCam(cam_y, max_cam_y);

                // update the player's position
                player.updatePlayer(cam_x, cam_y); // handle player movement and collision

                // reposition all the game tables with respect to the map
                for (int i = 0; i < game_tables.size(); i++) {
                    game_tables.get(i).sprite.setX(game_tables.get(i).map_x - cam_x);
                    game_tables.get(i).sprite.setY(game_tables.get(i).map_y - cam_y);
                }

                // render the canvas based of the camera position
                bg.renderCanvas((cam_y/ bg.tile_width), (cam_x/ bg.tile_width));
            }

            // function to check camera angles
            private int checkCam(int cam, int max) {
                if (cam < 0){
                    return 0;
                } else if (cam > max){
                    return max;
                }
                return cam;
            }
        };
        timer.start();
    }
}
