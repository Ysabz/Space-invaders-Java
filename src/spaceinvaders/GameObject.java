/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

/**
 *
 * @author yasam
 */
public class GameObject {

    protected Circle circle;
    protected Vector2D position;
    protected Vector2D velocity;
    protected Vector2D acceleration;
    private int decider = 1;

    public GameObject(Vector2D position, Vector2D velocity, Vector2D acceleration, double radius) {
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;

        circle = new Circle(0.0, 0.0, radius);
        circle.setLayoutX(position.getX());
        circle.setLayoutY(position.getY());
    }

    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(double x, double y) {
        position.setX(x);
        position.setY(y);
        circle.setLayoutX(position.getX());
        circle.setLayoutY(position.getY());
    }

    public Circle getCircle() {
        return circle;
    }

    public void update(double dt) {
        // Euler Integration
        // Update velocity
        Vector2D frameAcceleration = acceleration.mul(dt);
        velocity = velocity.add(frameAcceleration);

        // Update position
        position = position.add(velocity.mul(dt));
        circle.setLayoutX(position.getX());
        circle.setLayoutY(position.getY());
       
         
           
       

        // groupe bounce // check for the one aline at each extreme
    }

    
}
