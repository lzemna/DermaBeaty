/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import Entities.Categorie;
import Services.ServiceCategorie;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author SDIRI Yasmine
 */
public class backCatsController implements Initializable {

    
List <Categorie> categories =new ArrayList();
    @FXML
    private VBox tabcats;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         
            ServiceCategorie sc = new ServiceCategorie();
            try {
             
             categories =sc.getCategories();
         for(int i=0;i<categories.size();i++){
                             FXMLLoader fxmlLoader= new FXMLLoader();
           
            fxmlLoader.setLocation(getClass().getResource("back_cat.fxml"));//recuperer le fichier fxml

                //    prod;
                    try {
                        AnchorPane        produit = fxmlLoader.load();
                 
                     back_catController backcatController = fxmlLoader.getController();//recuperer le controller du ficher fxml
                
                backcatController.setTable(tabcats);
                
                backcatController.setDataCat(categories.get(i));//faire le block pour chaque produit de la liste
           tabcats.getChildren().add(produit);
           
                    } catch (IOException ex) {
                         System.out.println(ex.getMessage());   
                    }
                    
           
         
         }

                
                } catch (SQLException ex) {
            Logger.getLogger(grandprodController.class.getName()).log(Level.SEVERE, null, ex);
        }

                
                              
                
          
    }    


    @FXML
    private void ajoutcat(MouseEvent event) throws IOException {
         try {
            Parent parent = FXMLLoader.load(getClass().getResource("add_Categorie.fxml"));
            Scene scene = new Scene(parent);
            Stage ajoutstage = new Stage();
            ajoutstage.setScene(scene);
            ajoutstage.initStyle(StageStyle.UTILITY);
            ajoutstage.show();
        } catch (IOException ex) {
            Logger.getLogger(backCatsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void refreshcat(MouseEvent event) {
          ServiceCategorie sc = new ServiceCategorie();
          
            try {
             
              categories =sc.getCategories();
               this.tabcats.getChildren().clear();
         for(int i=0;i<categories.size();i++){
                             FXMLLoader fxmlLoader= new FXMLLoader();
           
            fxmlLoader.setLocation(getClass().getResource("back_cat.fxml"));//recuperer le fichier fxml

                //    prod;
                    try {
                        AnchorPane        produit = fxmlLoader.load();
                 
                     back_catController backcatController = fxmlLoader.getController();//recuperer le controller du ficher fxml
              
                     
                backcatController.setTable(tabcats);
                
                backcatController.setDataCat(categories.get(i));//faire le block pour chaque produit de la liste
           tabcats.getChildren().add(produit);
                    } catch (IOException ex) {
                         System.out.println(ex.getMessage());   
                    }
           
                 
            
         
         }

                
                } catch (SQLException ex) {
            Logger.getLogger(backCatsController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     

    @FXML
    private void gouser(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("Back_user.fxml"));
            Scene scene = new Scene(parent);
            Stage consstage = new Stage();
            consstage.setScene(scene);
            consstage.initStyle(StageStyle.UTILITY);
            consstage.show();
   
    }

    @FXML
    private void goderm(ActionEvent event) throws IOException {
         Parent parent = FXMLLoader.load(getClass().getResource("back_Dermatologue.fxml"));
            Scene scene = new Scene(parent);
            Stage consstage = new Stage();
            consstage.setScene(scene);
            consstage.initStyle(StageStyle.UTILITY);
            consstage.show();
   
    }

    @FXML
    private void godermcat(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("back_CatDerm.fxml"));
            Scene scene = new Scene(parent);
            Stage consstage = new Stage();
            consstage.setScene(scene);
            consstage.initStyle(StageStyle.UTILITY);
            consstage.show();
    }

    @FXML
    private void goprod(ActionEvent event) throws IOException {
            Parent parent = FXMLLoader.load(getClass().getResource("backprods.fxml"));
            Scene scene = new Scene(parent);
            Stage prodstage = new Stage();
            prodstage.setScene(scene);
            prodstage.initStyle(StageStyle.UTILITY);
            prodstage.show();
    }
    
}
