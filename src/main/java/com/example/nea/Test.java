
package com.example.nea;


import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

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


    private static final int TILE_SIZE = 32;

    // Viewport (window) size — smaller than the full map below.
    private static final int VIEW_WIDTH = 480;
    private static final int VIEW_HEIGHT = 320;

    // 0 = floor, 1 = wall, 2 = rug, 3 = table, 4 = bed, 5 = door
    //
    // Interior walls (the columns/rows of 1s in the middle of the grid,
    // below) split the house into four separate rooms: bedroom (top-left),
    // dining room (top-right), living room (bottom-left), and a small
    // study (bottom-right). The 5s are door gaps that connect rooms —
    // they render as floor-colored and are NOT solid, unlike walls.
    private static final int[][] MAP = {
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,1},
            {1,0,0,2,2,0,1,0,0,0,0,0,1,0,0,4,4,0,1,0,3,3,0,1},
            {1,0,0,2,2,0,1,0,0,0,0,0,1,0,0,4,4,0,1,0,3,3,0,1},
            {1,0,0,0,0,0,5,0,0,0,0,0,5,0,0,0,0,0,5,0,0,0,0,1},
            {1,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,1},
            {1,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
    };

    // Player state, in world (map) pixel coordinates — not screen coordinates.
    // Spawns in the top-left (bedroom) room, on open floor.
    private double playerX = 3 * TILE_SIZE;
    private double playerY = 2 * TILE_SIZE;
    private static final double PLAYER_SIZE = 20;
    private static final double SPEED = 3.0;

    private final Set<KeyCode> pressedKeys = new HashSet<>();

    @Override
    public void start(Stage stage) {
        Canvas canvas = new Canvas(VIEW_WIDTH, VIEW_HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Pane root = new Pane(canvas);
        Scene scene = new Scene(root, VIEW_WIDTH, VIEW_HEIGHT);

        scene.setOnKeyPressed(e -> pressedKeys.add(e.getCode()));
        scene.setOnKeyReleased(e -> pressedKeys.remove(e.getCode()));

        stage.setTitle("Scrolling Camera Example");
        stage.setScene(scene);
        stage.show();
        canvas.requestFocus();

        int mapPixelWidth = MAP[0].length * TILE_SIZE;
        int mapPixelHeight = MAP.length * TILE_SIZE;

        AnimationTimer loop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                updatePlayer(mapPixelWidth, mapPixelHeight);

                // --- Camera math ---
                // Start by centering the camera on the player...
                double camX = playerX - VIEW_WIDTH / 2.0;
                double camY = playerY - VIEW_HEIGHT / 2.0;

                // ...then clamp so the camera never shows past the map edges.
                camX = clamp(camX, 0, mapPixelWidth - VIEW_WIDTH);
                camY = clamp(camY, 0, mapPixelHeight - VIEW_HEIGHT);

                render(gc, camX, camY);
            }
        };
        loop.start();
    }

    private void updatePlayer(int mapPixelWidth, int mapPixelHeight) {
        double dx = 0, dy = 0;
        if (pressedKeys.contains(KeyCode.LEFT))  dx -= SPEED;
        if (pressedKeys.contains(KeyCode.RIGHT)) dx += SPEED;
        if (pressedKeys.contains(KeyCode.UP))    dy -= SPEED;
        if (pressedKeys.contains(KeyCode.DOWN))  dy += SPEED;

        // Resolve each axis separately so bumping into a wall on one axis
        // (e.g. X) doesn't also block legal movement on the other (Y) —
        // this is what lets the player slide along a wall instead of
        // sticking completely when moving diagonally into it.
        double newX = clamp(playerX + dx, 0, mapPixelWidth - PLAYER_SIZE);
        if (!collidesWithWall(newX, playerY)) {
            playerX = newX;
        }

        double newY = clamp(playerY + dy, 0, mapPixelHeight - PLAYER_SIZE);
        if (!collidesWithWall(playerX, newY)) {
            playerY = newY;
        }
    }

    /**
     * Checks whether the player's bounding box at (x, y) overlaps any
     * solid (wall) tile. Doors (tile 5) are intentionally NOT solid.
     */
    private boolean collidesWithWall(double x, double y) {
        int leftCol   = (int) Math.floor(x / TILE_SIZE);
        int rightCol  = (int) Math.floor((x + PLAYER_SIZE - 1) / TILE_SIZE);
        int topRow    = (int) Math.floor(y / TILE_SIZE);
        int bottomRow = (int) Math.floor((y + PLAYER_SIZE - 1) / TILE_SIZE);

        for (int row = topRow; row <= bottomRow; row++) {
            for (int col = leftCol; col <= rightCol; col++) {
                if (row < 0 || row >= MAP.length || col < 0 || col >= MAP[0].length) {
                    continue; // out of bounds is handled separately by clamp()
                }
                if (isSolid(MAP[row][col])) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isSolid(int tile) {
        return tile == 1; // only walls are solid; doors (5) are passable
    }

    private double clamp(double value, double min, double max) {
        // Guard against maps smaller than the viewport (max < min).
        if (max < min) return min;
        return Math.max(min, Math.min(max, value));
    }

    /**
     * Draws only the portion of the map visible through the camera,
     * offsetting every draw call by (-camX, -camY) so world coordinates
     * map correctly onto screen coordinates.
     */
    private void render(GraphicsContext gc, double camX, double camY) {
        gc.clearRect(0, 0, VIEW_WIDTH, VIEW_HEIGHT);

        // Only loop over tiles that could actually be visible (small optimization).
        int startCol = (int) Math.floor(camX / TILE_SIZE);
        int endCol   = (int) Math.ceil((camX + VIEW_WIDTH) / TILE_SIZE);
        int startRow = (int) Math.floor(camY / TILE_SIZE);
        int endRow   = (int) Math.ceil((camY + VIEW_HEIGHT) / TILE_SIZE);

        for (int row = Math.max(0, startRow); row < Math.min(MAP.length, endRow); row++) {
            for (int col = Math.max(0, startCol); col < Math.min(MAP[0].length, endCol); col++) {
                int tile = MAP[row][col];
                double screenX = col * TILE_SIZE - camX;
                double screenY = row * TILE_SIZE - camY;
                drawTile(gc, tile, screenX, screenY);
            }
        }

        // Draw the player relative to the camera too.
        gc.setFill(Color.web("#e04b4b"));
        gc.fillRect(playerX - camX, playerY - camY, PLAYER_SIZE, PLAYER_SIZE);
    }

    private void drawTile(GraphicsContext gc, int tile, double x, double y) {
        switch (tile) {
            case 1:
                gc.setFill(Color.web("#6b4f3a"));
                gc.fillRect(x, y, TILE_SIZE, TILE_SIZE);
                break;
            case 0:
                gc.setFill(Color.web("#d9b384"));
                gc.fillRect(x, y, TILE_SIZE, TILE_SIZE);
                break;
            case 2:
                gc.setFill(Color.web("#d9b384"));
                gc.fillRect(x, y, TILE_SIZE, TILE_SIZE);
                gc.setFill(Color.web("#b3403f"));
                gc.fillRect(x + 4, y + 4, TILE_SIZE - 8, TILE_SIZE - 8);
                break;
            case 3:
                gc.setFill(Color.web("#d9b384"));
                gc.fillRect(x, y, TILE_SIZE, TILE_SIZE);
                gc.setFill(Color.web("#8a5a2b"));
                gc.fillRect(x + 2, y + 2, TILE_SIZE - 4, TILE_SIZE - 4);
                break;
            case 4:
                gc.setFill(Color.web("#d9b384"));
                gc.fillRect(x, y, TILE_SIZE, TILE_SIZE);
                gc.setFill(Color.web("#7fa8d9"));
                gc.fillRect(x + 2, y + 2, TILE_SIZE - 4, TILE_SIZE - 4);
                break;
            case 5: // door — floor-colored but tinted so the gap in the wall reads clearly
                gc.setFill(Color.web("#a0743d"));
                gc.fillRect(x, y, TILE_SIZE, TILE_SIZE);
                break;
            default:
                gc.setFill(Color.web("#d9b384"));
                gc.fillRect(x, y, TILE_SIZE, TILE_SIZE);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}