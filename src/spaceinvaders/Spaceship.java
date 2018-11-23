/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

/**
 *
 * @author yasam
 */
public class Spaceship extends GameObject
{
    public Spaceship (Vector2D position, double radius)
    {
        super( position,new Vector2D (0.0,0.0) ,new Vector2D (0.0,0.0),  radius);
        //circle.setFill(AssetManager.getSpaceshipCover());
         circle.setFill(AssetManager.getShip());
    }
    
}
