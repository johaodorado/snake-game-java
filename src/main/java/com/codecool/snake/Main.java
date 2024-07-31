package com.codecool.snake;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Main extends Application {
    private GameFacade gameFacade;

    @Override
    public void start(Stage primaryStage) {
        // Inicializa la Facade y el juego
        gameFacade = new GameFacade();
        gameFacade.initializeGame();

        // Crea la escena principal
        Scene mainScene = new Scene(gameFacade.getGame(), Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT);
        primaryStage.setTitle("Snake Game");
        primaryStage.setScene(mainScene);
        primaryStage.show();

        // Inicia el bucle del juego
        gameFacade.startGameLoop();
    }

    @Override
    public void stop() throws Exception {
        // Detiene el juego cuando se cierra la ventana
        gameFacade.stopGame();
        System.out.println("Exiting..");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
