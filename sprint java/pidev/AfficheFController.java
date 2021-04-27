/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import Entities.Formulaire;
import Services.ServiceFormulaire;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class AfficheFController implements Initializable {
    
    @FXML
    private TableView<Formulaire> FormulaireTable;
    @FXML
    private TableColumn<Formulaire, String> cin;
    @FXML
    private TableColumn<Formulaire, String> questl;
    @FXML
    private TableColumn<Formulaire, String> quest2;
    @FXML
    private TableColumn<Formulaire, String> quest3;
    @FXML
    private TableColumn<Formulaire, String> quest4;
    @FXML
    private TableColumn<Formulaire, String> quest5;
     @FXML
    private TableColumn<Formulaire, String> quest6;
      @FXML
    private TableColumn<Formulaire, String> type;
    
         ServiceFormulaire cnxf= new ServiceFormulaire();
         ObservableList<Formulaire>  formlist = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            refreshTable();
        } catch (SQLException ex) {
            Logger.getLogger(AfficheFController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
  
    @FXML
    private void refreshTable() throws SQLException {
    
                  
                 formlist.clear();
                 formlist=(ObservableList<Formulaire>) cnxf.getFormulaire();
                  
                 FormulaireTable.setItems(formlist);
    }

}
