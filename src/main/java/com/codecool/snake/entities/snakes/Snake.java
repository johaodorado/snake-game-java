package com.codecool.snake.entities.snakes;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.eventhandler.InputHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import java.util.ArrayList;
import java.util.List;

public class Snake implements Animatable, SnakePart {
    private static final float speed = 2;
    private int health = 100;
    private List<SnakePart> parts = new ArrayList<>();

    public Snake(Point2D position) {
        SnakeHead head = new SnakeHead(this, position);
        parts.add(head);
        addParts(4);
    }

    @Override
    public void step() {
        SnakeControl turnDir = getUserInput();
        ((SnakeHead) parts.get(0)).updateRotation(turnDir, speed);
        updateSnakeBodyHistory();
        checkForGameOverConditions();
    }

    private SnakeControl getUserInput() {
        SnakeControl turnDir = SnakeControl.INVALID;
        if (InputHandler.getInstance().isKeyPressed(KeyCode.LEFT)) turnDir = SnakeControl.TURN_LEFT;
        if (InputHandler.getInstance().isKeyPressed(KeyCode.RIGHT)) turnDir = SnakeControl.TURN_RIGHT;
        return turnDir;
    }

    public void addParts(int numParts) {
        SnakePart parent = getLastPart();
        Point2D position = parent.getPosition();

        for (int i = 0; i < numParts; i++) {
            SnakeBody newBodyPart = new SnakeBody(position);
            parts.add(newBodyPart);
            parent = newBodyPart;
        }
        Globals.getInstance().display.updateSnakeHeadDrawPosition((GameEntity) parts.get(0));
    }

    public void changeHealth(int diff) {
        health += diff;
    }

    private void checkForGameOverConditions() {
        if (((GameEntity) parts.get(0)).isOutOfBounds() || health <= 0) {
            System.out.println("Game Over");
            Globals.getInstance().stopGame();
        }
    }

    private void updateSnakeBodyHistory() {
        updatePartPosition(parts.get(0), parts.get(0).getPosition());
    }

    private void updatePartPosition(SnakePart part, Point2D newPosition) {
        for (SnakePart child : parts) {
            if (child != part) {
                Point2D oldPosition = child.getPosition();
                child.updatePosition(newPosition);
                updatePartPosition(child, oldPosition);
            }
        }
    }

    private SnakePart getLastPart() {
        return parts.get(parts.size() - 1);
    }

    @Override
    public void updatePosition(Point2D newPosition) {
        parts.get(0).updatePosition(newPosition);
    }

    @Override
    public Point2D getPosition() {
        return parts.get(0).getPosition();
    }

    public void add(SnakePart part) {
        parts.add(part);
    }

    public void remove(SnakePart part) {
        parts.remove(part);
    }

    public SnakePart getChild(int index) {
        return parts.get(index);
    }

    public List<SnakePart> getChildren() {
        return parts;
    }
}
