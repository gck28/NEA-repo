package com.example.nea;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Background {

    Canvas canvas;
    Main main;
    GraphicsContext graphics_context;
    private static final String FLOOR_COLOR = "#90ee90";
    private static final String WALL_COLOR = "#800000";
    final int tile_width = 30;

    int[][] tilemap;


    public Background(Main main) {
        this.main = main;


        // create prerequisites for canvas, graphics context and tile map
        canvas = new Canvas(main.width, main.height);
        graphics_context = canvas.getGraphicsContext2D();


        tilemap = readTileMap();
        renderCanvas(0, 0);
    }


    // method to read tilemap from txt and convert to 2D array, where each element is an integer
    private  int[][] readTileMap() {
        int[][] return_array = new int[28][54];

        int row = 0;

        try (BufferedReader br = new BufferedReader(new FileReader("Assets/background.txt"))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] line_array = line.split(",");  // separate into array based on commas

                // convert array of strings into array of integers
                int[] int_row = new int[line_array.length];

                for (int col = 0; col < line_array.length; col++) {
                    int_row[col] = Integer.parseInt(line_array[col]);
                }

                // pass integer array into return_array and move onto next row;
                return_array[row] = int_row;
                row++;
            }
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }

        return return_array;
    }


    // function to render canvas every time the animation timer cycles through as it will change depending on the position of the sprite
    public void renderCanvas(int start_y, int start_x) {

        // calculate number of tiles displayed
        int num_tiles_across = main.width / tile_width;
        int num_tiles_down = main.height / tile_width;

        for (int i = start_y; i < start_y+num_tiles_down; i++) {
            for (int j = start_x; j < start_x+num_tiles_across; j++) {
                drawTile(i, j, j-start_x, i-start_y);
            }
        }
    }


    // function to draw each tile on using switch cases for each number, which will determine colour and if it is a solid wall
    private void drawTile(int row, int column, int offset_x, int offset_y) {
        switch(tilemap[row][column]){
            case 1:
                graphics_context.setFill(Color.web(WALL_COLOR));
                graphics_context.fillRect(offset_x*tile_width, offset_y*tile_width, tile_width, tile_width);
                break;
            case 0:
                graphics_context.setFill(Color.web(FLOOR_COLOR));
                graphics_context.fillRect(offset_x*tile_width, offset_y*tile_width, tile_width, tile_width);
                break;
        }
    }
}
