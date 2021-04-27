/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.centre;

import Entities.Categorie_centre;
import Services.ServiceCategorie_centre;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author demni
 */
public class AjoutCategorieController implements Initializable {

    @FXML
    private TextField type;
    @FXML
    private TextField specialite;
    @FXML
    private Button btn;
    @FXML
    private Button btn1;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjouterCategorie(ActionEvent event) throws IOException, SQLException {
        ServiceCategorie_centre p = new ServiceCategorie_centre();
        
        p.addCategorie(new Categorie_centre (type.getText() ,  specialite.getText()  ));
        JOptionPane.showMessageDialog(null, "Categorie Ajouter");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CategorieCentre.fxml"));
        Parent root = loader.load();
        type.getScene().setRoot(root);
    }

    @FXML
    private void AfficherCategorie(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CategorieCentre.fxml"));
        Parent root = loader.load();
        type.getScene().setRoot(root);
    }
    
    
        
    
    
}
