/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

import java.util.ArrayList;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author yasam
 */
public class Ennemies {

    private ArrayList<GameObject> ennemies = new ArrayList();
    private int decider =1;

    public Ennemies(Vector2D velocity, double radius) {
        // size 10
        Vector2D tempPosition;
        double tempY = 100;
        double tempX = 100;
        for (int i = 0; i < 40; i++) {

            tempPosition = new Vector2D( tempX , tempY);
            GameObject ennemy ;
            if (tempPosition.getX() < 600) //pane.height())// set the acceleration to 0 //pane width =200 so they can move to right or left
            {
                ennemy = new GameObject(tempPosition, velocity, new Vector2D(0.0, 0.0), radius);
                ennemies.add(ennemy);
                ennemy.circle.setFill(AssetManager.getAlienImage());
                tempX+=50;
            } else {
                tempY+=50;
               ennemy=  new GameObject(new Vector2D(100, tempY), velocity, new Vector2D(0.0, 0.0), radius);
                ennemies.add(ennemy);
                ennemy.circle.setFill(AssetManager.getAlienImage());
               tempX =150;

            }
        }

    }
public ArrayList<GameObject> getList()
{
    return ennemies;
}
public void updateImage (){
    for(int i = 0; i< ennemies.size();i++){
        if(decider==-1)
        ennemies.get(i).circle.setFill(AssetManager.getAlienImage2());
        else 
            ennemies.get(i).circle.setFill(AssetManager.getAlienImage());
}
    decider *=-1;
}
}


