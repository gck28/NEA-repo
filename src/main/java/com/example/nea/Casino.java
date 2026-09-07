package com.example.nea;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;


// main class which has the scene where the player can move around and choose which game they would like to play

public class Casino {
    // global objects and variables
    Pane root;
    Scene scene;

    Games blackjack;

    Player player;

    ImageView interact_button;

    Main main;

    Background bg;

    int cam_x;
    int cam_y;

    boolean is_interacting; // to check if the sprite is interacting with any tables
    boolean e_pressed; // create a boolean to check if a table has been clicked on

    ArrayList<Tables> game_tables;

    public Casino(Main main) throws IOException {
        this.main = main;

        // create root node and scene
        root = new Pane();
        root.setPrefSize(main.width, main.height);
        scene = new Scene(root, main.width, main.height);

        bg = new Background(main); // create background

        player = new Player(this); // create player

        interact_button = new ImageView(new Image(new FileInputStream("Assets/press_e.png")));
        interact_button.setPreserveRatio(true);
        interact_button.setFitWidth(200);

        // create an arraylist for all the game tables
        game_tables = new ArrayList<>();

        // create two classes for the blackjack table on the main scene that is interacted with and the actual game that gets switched to
        blackjack = new Blackjack(main);
        Tables blackjack_table = new Tables(this, "Assets/GameTableSprites/test_blackjack.png", blackjack.scene, 100, 100);

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
                for (Tables gameTable : game_tables) {
                    gameTable.sprite.setX(gameTable.map_x - cam_x);
                    gameTable.sprite.setY(gameTable.map_y - cam_y);
                }

                // render the canvas based of the camera position
                bg.renderCanvas((cam_y/ bg.tile_width), (cam_x/ bg.tile_width));

                // change e_pressed value to false

                // add interact button if a table is activated
                if (is_interacting){
                    // display interact button
                    interact_button.setX(main.width - interact_button.getFitWidth());
                    interact_button.setY(0);
                    root.getChildren().remove(interact_button);
                    root.getChildren().add(interact_button);

                    // add key listener for E when interaction button is displayed

                    scene.setOnKeyReleased(event -> {
                        if (event.getCode() == KeyCode.E){
                            main.switchScene(blackjack.scene);
                        }
                    });
                } else {
                    root.getChildren().remove(interact_button);
                }

                if (e_pressed && is_interacting){
                    main.switchScene(player.activated_table.scene);
                }
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
