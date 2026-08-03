package com.example.nea;

import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {

    Stage stage;
    final int width = 1200;
    final int height = 600;

    @Override
    public void start(Stage primary_stage) {
        stage = primary_stage;
        stage.setWidth(width);
        stage.setHeight(height);
        stage.setTitle("Casino game");

        StartMenu startMenu = new StartMenu(this);

        stage.setScene(startMenu.scene);
        stage.show();
    }
}
