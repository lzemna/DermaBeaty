/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.centre;

import Entities.Centre;
import Services.ServiceCentre;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author demni
 */
public class AjoutCentreController implements Initializable {

    @FXML
    private TextField tfnom;
    @FXML
    private TextField tftelephone;
    @FXML
    private TextArea tfdescription;
    @FXML
    private TextField tfemail;
    @FXML
    private TextField tfhoraire;
    @FXML
    private TextField tflocalisation;
    @FXML
    private Button btn;
    @FXML
    private Button btn1;
    @FXML
    private ChoiceBox<String> categorie;

    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjouterCentre(ActionEvent event) throws SQLException, IOException {
        ServiceCentre p = new ServiceCentre();
        
        p.addCentre(new Centre(tfnom.getText() , Integer.parseInt(tftelephone.getText()) , tfemail.getText() , tfhoraire.getText() , tfdescription.getText(),tflocalisation.getText()  ));
        JOptionPane.showMessageDialog(null, "Centre Ajouter");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherCentre.fxml"));
        Parent root = loader.load();
        tfnom.getScene().setRoot(root);
    }
    
    @FXML
    private void AfficherCentre(ActionEvent event) throws SQLException, IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherCentre.fxml"));
        Parent root = loader.load();
        tfnom.getScene().setRoot(root);
    }

  
    
    
}
