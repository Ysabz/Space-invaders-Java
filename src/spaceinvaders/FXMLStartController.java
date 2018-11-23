/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author yasam
 */
public class FXMLStartController implements Initializable {

    @FXML
    private Button startB;
    @FXML
   private ImageView poster;
     @FXML
   private AnchorPane pane;
    
    
    /**
     * Initializes the controller class.
     */
    public void startBHandle(ActionEvent ae)throws IOException{
    
        Stage stage = SpaceInvaders.stagetemp;
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        
        scene.getRoot().requestFocus();
        
        stage.setScene(scene);
        stage.show();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       AssetManager.preloadAllAssets();
       File file = new File ("./assets/images/pic2.jpg");
       Image im = new Image(file.toURI().toString(),700,700,false,false);
       poster.setImage(im);
       startB.setOpacity(0);



        ///
    
       
    }    
    
}
