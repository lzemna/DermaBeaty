/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.centre;

import Entities.Categorie_centre;
import Services.ServiceCategorie_centre;
import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author demni
 */
public class CategorieCentreController implements Initializable {

    @FXML
    private TableView<Categorie_centre> tableC;
    @FXML
    private TableColumn<Categorie_centre,String> type;
    @FXML
    private TableColumn<Categorie_centre,String> Specialite;

    ObservableList<Categorie_centre> categorie_centreList = FXCollections.observableArrayList();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
           
        try {
            ServiceCategorie_centre p = new ServiceCategorie_centre();
            ObservableList<Categorie_centre> categorie_centre = p.getCategories();  
            type.setCellValueFactory(new PropertyValueFactory<>("type"));
            Specialite.setCellValueFactory(new PropertyValueFactory<>("specialite"));
            
            tableC.setItems((ObservableList<Categorie_centre>) categorie_centre);
        } catch (SQLException ex) {
            Logger.getLogger(CategorieCentreController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    

    @FXML
    private void AjouterCategorie(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutCategorie.fxml"));
        Parent root = loader.load();
        tableC.getScene().setRoot(root);
    }

    @FXML
    private void DelCategorie(ActionEvent event) throws SQLException {
        Categorie_centre categorie_centre = tableC.getSelectionModel().getSelectedItem();
         
        ServiceCategorie_centre p = new ServiceCategorie_centre();
        p.deleteCategorie(categorie_centre.getId());
        
        Refresh();
    }

     @FXML
    private void Refresh() throws SQLException {
          ServiceCategorie_centre p = new ServiceCategorie_centre();
        ObservableList<Categorie_centre> a = p.refresh();
        tableC.setItems(a);
        
      }

    @FXML
    private void update(ActionEvent event) {
    }
    
}
