package com.codecool.snake.entities.snakes;

import com.codecool.snake.DelayedModificationList;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.eventhandler.InputHandler;

import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;


import java.util.ArrayList;
import java.util.List;


public class Snake implements Animatable {
    private static final float speed = 2;
    private int health = 100;

    private List<SnakePart> parts;

    public Snake(Point2D position) {
        parts = new ArrayList<>();
        SnakeHead head = new SnakeHead(this, position);
        parts.add(head);

        addPart(4);
    }
    public void step() {
        SnakeControl turnDir = getUserInput();
        ((SnakeHead) parts.get(0)).updateRotation(turnDir, speed);
        updateSnakeBodyHistory();
        checkForGameOverConditions();
        for (SnakePart part : parts) {
            part.step();
        }}
    private SnakeControl getUserInput() {
        SnakeControl turnDir = SnakeControl.INVALID;
        if (InputHandler.getInstance().isKeyPressed(KeyCode.LEFT)) turnDir = SnakeControl.TURN_LEFT;
        if (InputHandler.getInstance().isKeyPressed(KeyCode.RIGHT)) turnDir = SnakeControl.TURN_RIGHT;
        return turnDir;
    }

    public void addPart(int numParts) {
        SnakePart parent = getLastPart();
        Point2D position = parent.getPosition();

        for (int i = 0; i < numParts; i++) {
            SnakeBody newBodyPart = new SnakeBody(position);
            parts.add(newBodyPart);
        } }
    public void changeHealth(int diff) {
        health += diff;
    }
    private void checkForGameOverConditions() {
        if (((GameEntity) parts.get(0)).isOutOfBounds() || health <= 0) {
            System.out.println("Game Over");
        }}
    private void updateSnakeBodyHistory() {
        SnakePart prev = parts.get(0);
        for (int i = 1; i < parts.size(); i++) {
            SnakePart currentPart = parts.get(i);
            currentPart.updatePosition(prev.getPosition());
            prev = currentPart;
        } }
    private SnakePart getLastPart() {
        return parts.get(parts.size() - 1);
    }
}
