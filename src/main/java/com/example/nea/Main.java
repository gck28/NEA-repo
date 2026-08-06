package com.example.nea;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    Stage stage;
    final int width = 1200;
    final int height = 600;

    @Override
    public void start(Stage primary_stage) {
        stage = primary_stage;
        stage.setTitle("Casino game");

        StartMenu startMenu = new StartMenu(this);

        switchScene(startMenu.scene);

        stage.show();
    }

    public static void main(String[] args){
        launch();
    }

    public void switchScene(Scene scene) {
        stage.setScene(scene);
    }
}
