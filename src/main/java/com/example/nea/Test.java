
package com.example.nea;


import javafx.stage.Stage;

import java.io.IOException;


/**
 * Example: a house map LARGER than the visible window, split into
 * separate rooms by interior walls, with a player sprite you move via
 * arrow keys. The camera follows the player and clamps at the map
 * edges. Walls are solid (the player collides with them); doors are
 * gaps in the walls that let the player pass between rooms.
 */
public class Test extends Main{
    Stage stage;
    final int width = 1200;
    final int height = 600;

    @Override
    public void start(Stage primary_stage) {
        stage = primary_stage;

    }

    public static void main(String[] args) {
        launch(args);
    }
}