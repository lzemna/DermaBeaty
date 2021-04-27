/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.centre;

import Entities.Centre;
import Services.ServiceCentre;
import static java.awt.SystemColor.text;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

/**
 * FXML Controller class
 *
 * @author demni
 */
public class AfficherCentreController implements Initializable {
    @FXML
    private TableView<Centre> table;
    @FXML
    private TableColumn<Centre,String> tnom;
    @FXML
    private TableColumn<Centre,Number> tele;
    @FXML
    private TableColumn<Centre,String> email;
    @FXML
    private TableColumn<Centre,String> horaire;
    @FXML
    private TableColumn<Centre,String> description;
    @FXML
    private TableColumn<Centre,String> Localisation;
    
    @FXML
    private TextField recherche;
    
   Connection cnx;
    
   ObservableList<Centre> centreList = FXCollections.observableArrayList();
    
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
       
        
        
   
        try {
           
            ServiceCentre p = new ServiceCentre();
            ObservableList<Centre> centre = p.getCentre();  
            tnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            tele.setCellValueFactory(new PropertyValueFactory<>("telephone"));
            email.setCellValueFactory(new PropertyValueFactory<>("email"));
            horaire.setCellValueFactory(new PropertyValueFactory<>("horaire"));
            description.setCellValueFactory(new PropertyValueFactory<>("description"));
            Localisation.setCellValueFactory(new PropertyValueFactory<>("Localisation"));
            table.setItems((ObservableList<Centre>) centre);
        } catch (SQLException ex) {
            Logger.getLogger(AfficherCentreController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }    
   @FXML
    private void AjouterCentre(ActionEvent event) throws SQLException, IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutCentre.fxml"));
        Parent root = loader.load();
        table.getScene().setRoot(root);
        
    }
    
    @FXML
    private void Refresh() throws SQLException {
          ServiceCentre p = new ServiceCentre();
        ObservableList<Centre> a = p.refresh();
        table.setItems(a);
        
      }
    @FXML
    private void DelCentre(ActionEvent event) throws SQLException  {
       
        Centre centre = table.getSelectionModel().getSelectedItem();
         
        ServiceCentre p = new ServiceCentre();
        p.deleteCentre(centre.getId());
        
        Refresh();
       
        
       
        
    }

@FXML
    private void update (ActionEvent event)  {
       
       
               FXMLLoader loader = new FXMLLoader ();
               
               loader.setLocation(getClass().getResource("UpdateCentre.fxml"));
                try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(AfficherCentreController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                
                UpdateCentreController acc = loader.getController();
                
            
                       Centre centre = table.getSelectionModel().getSelectedItem();
       

           acc.getid().setText(String.valueOf(centre.getId()));
                acc.getTfnom().setText(centre.getNom());
        acc.getTftelephone().setText(String.valueOf(centre.getTelephone()));
        acc.getTfemail().setText(centre.getEmail());
        acc.getTfhoraire().setText(centre.getHoraire());
        acc.getTfdescription().setText(centre.getDescription());
        acc.getTflocalisation().setText(centre.getLocalisation());
       
               
         Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
    }

  
    
  
    
    
         ObservableList<Centre>  centrelist = FXCollections.observableArrayList();
     @FXML
    private void search() throws SQLException {
    
              centrelist.clear();
               String test = recherche.getText();
               ServiceCentre p = new ServiceCentre();
        ObservableList<Centre> a = p.getCentre();
               
               for(int i=0;i<a.size();i++  ) {
                     if(p.rechercheCentre(a.get(i), test)== true){
             Centre c = new Centre();
            c.setNom(a.get(i).getNom());
            c.setTelephone(a.get(i).getTelephone());
            c.setEmail(a.get(i).getEmail());
             c.setHoraire(a.get(i).getHoraire());
            c.setDescription(a.get(i).getDescription());
             c.setLocalisation(a.get(i).getLocalisation()); 
             
           
             centrelist.add(c);
            table.setItems(centrelist);
        }
               }
               
        tnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            tele.setCellValueFactory(new PropertyValueFactory<>("telephone"));
            email.setCellValueFactory(new PropertyValueFactory<>("email"));
            horaire.setCellValueFactory(new PropertyValueFactory<>("horaire"));
            description.setCellValueFactory(new PropertyValueFactory<>("description"));
            Localisation.setCellValueFactory(new PropertyValueFactory<>("Localisation"));
           
    }

    @FXML
    private void qrcode(ActionEvent event) throws Exception {
        
        Centre centre = table.getSelectionModel().getSelectedItem();
        String qrcentre = "Nom :" + centre.getNom() + " Telephone :" + centre.getTelephone() + "Email :" + centre.getEmail() ;
       

           ByteArrayOutputStream out = QRCode.from(qrcentre).to(ImageType.JPG).stream();
           File f = new File ("C:\\Users\\demni\\Desktop\\"+ centre.getNom()+".jpg");
           FileOutputStream fos = new FileOutputStream(f);
           fos.write(out.toByteArray());
           fos.flush();
           
        
    }
      
}
    
    
    
    


