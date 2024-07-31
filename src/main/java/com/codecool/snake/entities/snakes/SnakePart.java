/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.codecool.snake.entities.snakes;

import javafx.geometry.Point2D;

public interface SnakePart {
    void updatePosition(Point2D newPosition);
    Point2D getPosition();
}
