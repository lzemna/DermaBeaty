/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class DatailController implements Initializable {

    
    @FXML
    private Label nomLabel;
    @FXML
    private Label quantityLabel;
    @FXML
    private Label totalLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    public void setData(String name , int quantity,double total){
    this.nomLabel.setText(name);
    this.quantityLabel.setText(quantity+"");
    this.totalLabel.setText(total+"");
        
        
        
        
        
    }
    
}
