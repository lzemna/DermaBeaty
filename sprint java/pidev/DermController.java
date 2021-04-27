/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import Entities.Dermatologue;
import java.net.MalformedURLException;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author IMNA
 */
public class DermController implements Initializable {

    @FXML
    private Label Diplome;
    @FXML
    private Label Horaire;
    @FXML
    private ImageView prodImage;

    /**
     * Initializes the controller class.
     */
    private Dermatologue derm;
  
    
    public void setProduit(Dermatologue p){
  this.derm = p;      
        
        
    }
    
   
    public void setData(Dermatologue p)  {
     
        Diplome.setText(p.getDiplome());
       Horaire.setText(p.getHoraire()+" H");
       System.out.println(p.getImage());
       prodImage.setImage(new Image(getClass().getResourceAsStream(p.getImage())));
       //prodImage.setImage(new Image(ChangeInstru.class.getResourceAsStream("flo.jpg")));
        /*try {
            URL ul = new URL(p.getImage_p());
        } catch (MalformedURLException ex) {
            Logger.getLogger(ProdController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
     // final URL imageURL = getClass().getResource(p.getImage_p()); 
      
   //prodImage.setImage(new Image(ChangeInstru.class.getResourceAsStream(p.getImage_p())));
   
         
      
        
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clickProd(MouseEvent event) {
    }
    
}
