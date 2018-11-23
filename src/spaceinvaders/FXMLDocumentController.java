/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author yasam
 */
public class FXMLDocumentController implements Initializable {

    /**
     * Initializes the controller class.
     *
     */
    @FXML
    AnchorPane pane;
    @FXML
    Button button;
    @FXML
    Label message;
    private double lastFrameTime = 0.0;
    private ArrayList<Shoots> shootS;
    private ArrayList<Shoots> shootE;
    private int numCircleDestroyed = 0;
    private double mouseX = 0.0, mouseY = 700;
    private Spaceship ship = null;
    private Ennemies ennemies = null;
    private int updateImage;
    private long clickTimerStart = System.currentTimeMillis();
    private int live = 3;
    private GameObject temp = null;
    private int score;
    @FXML
    ImageView live1;
    @FXML
    ImageView live2;
    @FXML
    ImageView live3;
    @FXML
    Label scoreLabel;

    @FXML
    public void onMouseMove(MouseEvent event) {
        // System.out.println("(" + event.getX() + ", " + event.getY() + ")");
        // position of the spaceship >>  only change the x !! 
        mouseX = event.getX();

        ship.setPosition(mouseX, mouseY);
    }

    @FXML
    public void onMouseClick(MouseEvent event) {

        long clickTimerEnd = System.currentTimeMillis();

        if (event.getButton() == MouseButton.PRIMARY) {
            //System.out.println(clickTimerEnd-clickTimerStart);
            if ((int) (clickTimerEnd - clickTimerStart) / 1000 > 1) {
                Shoots shoot = new Shoots(new Vector2D(mouseX, mouseY), new Vector2D(0, -100), 5);
                shootS.add(shoot);
                addToPane(shoot.getCircle());
                AssetManager.getShootingSound().play();
                clickTimerStart = System.currentTimeMillis();

            }
        }

    }

    public void addToPane(Node node) {
        pane.getChildren().add(node);
    }

    public void buttonHandle(ActionEvent ae) throws Exception {
        Stage stage = SpaceInvaders.stagetemp;
        Parent root = FXMLLoader.load(getClass().getResource("FXMLStart.fxml"));
        Scene scene = new Scene(root);

        scene.getRoot().requestFocus();

        stage.setScene(scene);
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lastFrameTime = 0.0f;
        long initialTime = System.nanoTime();
        live1.setImage(AssetManager.getLiveImage());
        live2.setImage(AssetManager.getLiveImage());
        live3.setImage(AssetManager.getLiveImage());
        ArrayList<ImageView> lives = new ArrayList();
        lives.add(live1);
        lives.add(live2);
        lives.add(live3);
        scoreLabel.toFront();
        message.setVisible(false);
        button.setVisible(false);
        ship = new Spaceship(new Vector2D(mouseX, mouseY), 30);
        
        ennemies = new Ennemies(new Vector2D(30, 0.0), 20);// check the constructor
        shootS = new ArrayList<Shoots>();// includes all the shoots from the space ship 
        shootE = new ArrayList();// includes all the shoots from the ennemies
        MediaPlayer musicPlayer = new MediaPlayer(AssetManager.getBackgroundMusic());
        musicPlayer.play();
        ArrayList<GameObject> ennemyList = ennemies.getList();// get list gives the position of the all the circles(game objects) in the group of ennemies
        pane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        addToPane(ship.getCircle());
        for (int i = 0; i < ennemyList.size(); i++) {
            addToPane(ennemyList.get(i).getCircle());
        }

        // System.out.println("add"+i+ " "+ (ennemyList.get(i)).position.getX());
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                // Time calculation                

                double currentTime = (now - initialTime) / 1000000000.0;
                double frameDeltaTime = currentTime - lastFrameTime;
                lastFrameTime = currentTime;
                if ((int) currentTime > updateImage) {
                    ennemies.updateImage();
                    updateImage++;
                }
                // Test for collisions of the ennemie shoots and the space ship
                if (live > 0) {

                    for (int i = 0; i < shootE.size(); ++i) {
                        Shoots s1 = shootE.get(i); // shoots are in form of circles
                        double d = Vector2D.distance(s1.getPosition(), ship.getPosition());

                        if (d < s1.getCircle().getRadius() + ship.getCircle().getRadius()) {

                            AssetManager.getExplosionSound().play();
                            shootE.remove(i);
                            pane.getChildren().remove(s1.getCircle());
                           
                            lives.get(0).setImage(null);
                            lives.remove(0);
                            live--;

                        }
                    }
                } else {
// set mouse disabled 
                    musicPlayer.stop();
                     ship.circle.setFill(AssetManager.getDeadImage());
                    AssetManager.getExplosionSound().play();
                   
                    button.setVisible(true);
                    button.toFront();
                    message.setVisible(true);
                    message.toFront();
                    stop();
                }

                /* */
                // test for the collision of the spaceship shoots and the ennemies
                if (ennemyList.size() > 0) {
                    if (temp != null) {
                        pane.getChildren().remove(temp.getCircle());
                        ennemyList.remove(temp);
                    }
                    
                    for (int i = 0; i < shootS.size(); ++i) {
                        Shoots s1 = shootS.get(i); // shoots are in form of circles
                        for (int j = 0; j < ennemyList.size(); j++) {
                            GameObject ennemy = ennemyList.get(j);
                            double d = Vector2D.distance(s1.getPosition(), ennemy.getPosition());

                            if (d < s1.getCircle().getRadius() + ennemy.getCircle().getRadius()) {

                                shootS.remove(s1);
                                ennemy.circle.setFill(AssetManager.getFlashImage());
                                temp = ennemy;
                                score ++;
                                scoreLabel.setText(""+score);
                                pane.getChildren().remove(s1.getCircle());
                                AssetManager.getDeadSound().play();
                                // System.out.println("+ one point");

                            }
                        }
                    }
                } else {

                    musicPlayer.stop();
                    button.setVisible(true);
                    button.toFront();
                    message.setText("Victory");
                    message.setVisible(true);
                    message.toFront();
                    stop();
                }

                for (int i = 0; i < ennemyList.size(); i++) {
                    GameObject x = ennemyList.get(i);

                    if (x.position.getX() < 20 || x.position.getX() > pane.getWidth() - 20) {
                        for (int j = 0; j < ennemyList.size(); j++) {

                            ennemyList.get(j).position = new Vector2D(ennemyList.get(j).position.getX(), ennemyList.get(j).position.getY() + 30);
                            //System.out.println("loop in" + j);
                            ennemyList.get(j).velocity = new Vector2D(-ennemyList.get(j).velocity.getX(), 0);
                            ennemyList.get(j).update(frameDeltaTime);
                        }

                        //System.out.println("loop done");
                        break;
                    }
                }
                for (int i = 0; i < ennemyList.size(); i++) {
                    GameObject x = ennemyList.get(i);
                    x.update(frameDeltaTime);

                }
                 
                

                if ((int) currentTime > shootE.size()) {
                    Random rng = new Random();
                    int rand = rng.nextInt(ennemyList.size());

                    Shoots shoote = new Shoots(new Vector2D(ennemyList.get(rand).position.getX(), ennemyList.get(rand).position.getY()), new Vector2D(0, +100), 5);
                    shootE.add(shoote);
                    addToPane(shoote.getCircle());
                   

                }

// Update exiating shots  note we dont want bounces
                for (Shoots shoot : shootE) {
                    shoot.update(frameDeltaTime);

                    if (shoot.position.getY() > pane.getHeight() - 5 || shoot.position.getX() > pane.getWidth() - 5 || shoot.position.getX() < 5) {
                        pane.getChildren().remove(shoot);
                    }

                }
                for (Shoots shoot : shootS) {
                    shoot.update(frameDeltaTime);
                    if (shoot.position.getY() < 5 || shoot.position.getX() > pane.getWidth() - 5 || shoot.position.getX() < 5) {
                        pane.getChildren().remove(shoot);
                    }

                }
            }

        }.start();
    }

}
