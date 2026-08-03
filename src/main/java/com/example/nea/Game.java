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

                cam_x = (int) (player.map_x - ((double) main.width /2));
                cam_y = (int) (player.map_y - ((double) main.height /2));

                int max_cam_x = (bg.tilemap[0].length*bg.tile_width) - main.width;
                int max_cam_y = (bg.tilemap.length*bg.tile_width) - main.height;

                cam_x = checkCam(cam_x, max_cam_x);
                cam_y = checkCam(cam_y, max_cam_y);

                player.updatePlayer(); // handle player movement and collision

                bg.renderCanvas((int) (cam_y/ bg.tile_width), (int) (cam_x/ bg.tile_width));
            }

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
