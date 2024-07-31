/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codecool.snake;

public class GameFacade {
    private Game game;
    private Display display;
    private GameTimer gameTimer;

    public GameFacade() {
       
        Globals.getInstance().setupResources();
        game = new Game();
        display = Globals.getInstance().display;
        gameTimer = new GameTimer();
    }

    public void initializeGame() {
    
        game.init();
        game.start();
    }

    public void startGameLoop() {
      
        GameLoop gameLoop = Globals.getInstance().getGameLoop();
        gameTimer.setup(gameLoop::step);
        gameTimer.play();
    }

    public void stopGame() {
        // Detiene el juego
        gameTimer.stop();
        System.out.println("Game stopped.");
    }

   
    public Game getGame() {
        return game;
    }

    public Display getDisplay() {
        return display;
    }

    public GameTimer getGameTimer() {
        return gameTimer;
    }
}
