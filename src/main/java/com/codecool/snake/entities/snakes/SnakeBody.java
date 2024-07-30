package com.codecool.snake.entities.snakes;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import javafx.geometry.Point2D;

import java.util.LinkedList;
import java.util.Queue;
import javafx.scene.canvas.GraphicsContext;



public class SnakeBody extends GameEntity implements SnakePart {
    private Queue<Point2D> history = new LinkedList<>();
    private static final int historySize = 10;

    public SnakeBody(Point2D coord) {
        setImage(Globals.getInstance().getImage("SnakeBody"));
        setX(coord.getX());
        setY(coord.getY());

        for (int i = 0; i < historySize; i++) {
            history.add(coord);
        }
    }
    @Override
    public void setPosition(Point2D pos) {
        Point2D currentPos = history.poll();
        setX(currentPos.getX());
        setY(currentPos.getY());
        history.add(pos);
    }

    @Override
    public Point2D getPosition() {
        return new Point2D(getX(), getY());
    }
    @Override
    public void updatePosition(Point2D newPosition) {
        setPosition(newPosition);
    }

    public void render(GraphicsContext gc) {
    }

    @Override
    public void step() {
    }

    @Override
    public void render() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
