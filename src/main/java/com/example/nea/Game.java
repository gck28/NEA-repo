package com.example.nea;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.io.IOException;


// main class which has the scene where the player can move around and choose which game they would like to play

public class Game {

    Pane root;
    Scene scene;

    Player player;

    Main main;

    Background bg;

    int cam_x;
    int cam_y;

    public Game (Main main) throws IOException {
        this.main = main;

        // create root node and scene
        root = new Pane();
        root.setPrefSize(main.width, main.height);
        scene = new Scene(root, main.width, main.height);

        bg = new Background(main); // create background

        player = new Player(this); // create player

        // display the player
        player.sprite.setX(player.map_x*bg.tile_width);
        player.sprite.setY(player.map_y*bg.tile_width);

        root.getChildren().addAll(bg.canvas, player.sprite);


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
                player.updatePlayer(); // handle player movement and collision

                // render the canvas based of the camera position
                bg.renderCanvas((cam_y/ bg.tile_width), (int) (cam_x/ bg.tile_width));
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
