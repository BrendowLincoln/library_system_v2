package br.org.femass.controllers;

import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Map;


public class ControllerBase {
    protected Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
        this.configureStage(stage);
    }

    private void configureStage(Stage stage) {

        stage.getIcons().add((new Image(getClass().getResourceAsStream("/assets/images/icon-window.png"))));
        stage.setTitle("FeMASS - Bibliotéca Universitaria");
        stage.setWidth(1366);
        stage.setHeight(768);
        stage.centerOnScreen();
        stage.setResizable(false);
    }
}
