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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author demni
 */
public class UpdateCentreController implements Initializable {

    @FXML
    private TextField tfnom;
    @FXML
    private TextField id;
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
    

    
    
    public TextField getid() {
        return id;
    }

    public void setid(TextField id) {
        this.id = id;
    }
    public TextField getTfnom() {
        return tfnom;
    }

    public void setTfnom(TextField tfnom) {
        this.tfnom = tfnom;
    }

    public TextField getTftelephone() {
        return tftelephone;
    }

    public void setTftelephone(TextField tftelephone) {
        this.tftelephone = tftelephone;
    }

    public TextArea getTfdescription() {
        return tfdescription;
    }

    public void setTfdescription(TextArea tfdescription) {
        this.tfdescription = tfdescription;
    }

    public TextField getTfemail() {
        return tfemail;
    }

    public void setTfemail(TextField tfemail) {
        this.tfemail = tfemail;
    }

    public TextField getTfhoraire() {
        return tfhoraire;
    }

    public void setTfhoraire(TextField tfhoraire) {
        this.tfhoraire = tfhoraire;
    }

    public TextField getTflocalisation() {
        return tflocalisation;
    }

    public void setTflocalisation(TextField tflocalisation) {
        this.tflocalisation = tflocalisation;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
            
        
    }    
    
    
    

    @FXML
    private void edit(ActionEvent event) throws SQLException {
                     
                
        ServiceCentre p = new ServiceCentre();
        int tel = Integer.parseInt(tftelephone.getText());
        int Id = Integer.parseInt(id.getText());
        System.out.print(Id);
        Centre centre= new Centre (Id, tfnom.getText(),tel,tfemail.getText(),tfhoraire.getText(),tfdescription.getText(),tflocalisation.getText());
       
        
        p.updateCentre(centre);
        p.refresh();
       
        Stage stage = (Stage) btn.getScene().getWindow();
    stage.close();
       
        
        
    }

    

    
    
}
