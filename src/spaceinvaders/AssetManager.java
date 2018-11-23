/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

/**
 *
 */
//@author yasam


import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.paint.ImagePattern;

public class AssetManager {
    static private Background backgroundImage = null;
    
    static private ImagePattern ship = null;
    static private ImagePattern alien = null;
    static private ImagePattern alien2 = null;
    static private ImagePattern crossHairImage = null;
   static private ImagePattern flash = null;
    static private ImagePattern dead = null;
    static private Image live = null;
    static private Media backgroundMusic = null;
    
    static private AudioClip shootingSound = null;
    static private AudioClip deadEnemy = null;
     static private AudioClip explosion = null;
    
    static private String fileURL(String relativePath)
    {
        return new File(relativePath).toURI().toString();
    }
    
    static public void preloadAllAssets()
    {
        // Preload all images
        Image background = new Image(fileURL("./assets/images/Pic0.jpg"));
        backgroundImage = new Background(
                            new BackgroundImage(background, 
                                                BackgroundRepeat.NO_REPEAT, 
                                                BackgroundRepeat.NO_REPEAT, 
                                                BackgroundPosition.DEFAULT,
                                                BackgroundSize.DEFAULT));
        
        
        live = new Image(fileURL("./assets/images/live.jpg"));
       
        alien = new ImagePattern(new Image(fileURL("./assets/images/alien1.jpg")));
         flash = new ImagePattern(new Image(fileURL("./assets/images/flash.png")));
         dead = new ImagePattern(new Image(fileURL("./assets/images/dead.png")));
         alien2 = new ImagePattern(new Image(fileURL("./assets/images/alien2.jpg")));
        crossHairImage = new ImagePattern(new Image(fileURL("./assets/images/crosshair.png")));
           ship = new ImagePattern(new Image(fileURL("./assets/images/ship.png")));
        // Preload all music tracks
        backgroundMusic = new Media(fileURL("./assets/music/backgroundMusic.mp3"));

        // Preload all sound effects
        
        shootingSound =  new AudioClip(fileURL("./assets/soundfx/shooting.wav"));
        deadEnemy  =  new AudioClip(fileURL("./assets/soundfx/newPlanet.wav"));
        explosion =  new AudioClip(fileURL("./assets/soundfx/explosion.wav"));
    }
    
    static public Background getBackgroundImage()
    {
        return backgroundImage;        
    }
    static public Image getLiveImage()
    {
        return live;
    }
    static public ImagePattern getShip()
    {
        
        return ship;
    }
    static public ImagePattern getFlashImage()
    {
        
        return flash;
    }
    static public ImagePattern getDeadImage()
    {
        
        return dead;
    }
 static public ImagePattern getAlienImage()
    {
        
        return alien;
    
    }
 static public ImagePattern getAlienImage2()
    {
        
        return alien2;
    }
    
    
    static public ImagePattern getCrossHairImage()
    {
        return crossHairImage;
    }
    
    static public Media getBackgroundMusic()
    {
        return backgroundMusic;
    }
    
    static public AudioClip getShootingSound()
    {
        return shootingSound;
    }
     static public AudioClip getDeadSound()
    {
        return deadEnemy;
    }
      static public AudioClip getExplosionSound()
    {
        return explosion;
    }
}

