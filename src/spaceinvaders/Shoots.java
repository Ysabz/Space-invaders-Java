/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;
import javafx.scene.paint.Color;

/**
 *
 * @author yasam
 */
public class Shoots extends GameObject {
  
    public Shoots(Vector2D position, Vector2D velocity, double radius){
        super(position , velocity , new Vector2D(0.0,0.0),radius);
        circle.setFill(Color.WHITE);
    }
    
}
