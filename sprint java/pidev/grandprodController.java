/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import Entities.Produit;
import Services.ServiceProduit;
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
public class grandprodController implements Initializable {

   
    List <Produit> produits =new ArrayList();
    @FXML
    private TextField searchm;
    @FXML
    private TextField searcht;
    @FXML
    private VBox tabprods;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
            ServiceProduit sp = new ServiceProduit();
            try {
             
             produits =sp.getProduits();
         for(int i=0;i<produits.size();i++){
                             FXMLLoader fxmlLoader= new FXMLLoader();
           
            fxmlLoader.setLocation(getClass().getResource("backProd.fxml"));//recuperer le fichier fxml

                //    prod;
                    try {
                        AnchorPane        produit = fxmlLoader.load();
                 
                     backProdController backprodController = fxmlLoader.getController();//recuperer le controller du ficher fxml
                
                backprodController.setTable(tabprods);
                
                backprodController.setDataProd(produits.get(i));//faire le block pour chaque produit de la liste
           tabprods.getChildren().add(produit);
           
                    } catch (IOException ex) {
                         System.out.println(ex.getMessage());   

                    }
                    
           
                 
            
         
         }

                
                } catch (SQLException ex) {
            Logger.getLogger(grandprodController.class.getName()).log(Level.SEVERE, null, ex);
        }

                
                              
                
          
    }    


    @FXML
    private void ajoutProd(MouseEvent event) throws IOException {
         try {
            Parent parent = FXMLLoader.load(getClass().getResource("add_Produit.fxml"));
            Scene scene = new Scene(parent);
            Stage ajoutstage = new Stage();
            ajoutstage.setScene(scene);
            ajoutstage.initStyle(StageStyle.UTILITY);
            ajoutstage.show();
        } catch (IOException ex) {
            Logger.getLogger(grandprodController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void refresh(MouseEvent event) {
          ServiceProduit sp = new ServiceProduit();
          
            try {
             
              produits =sp.getProduits();
               this.tabprods.getChildren().clear();
         for(int i=0;i<produits.size();i++){
                             FXMLLoader fxmlLoader= new FXMLLoader();
           
            fxmlLoader.setLocation(getClass().getResource("backProd.fxml"));//recuperer le fichier fxml

                //    prod;
                    try {
                        AnchorPane        produit = fxmlLoader.load();
                 
                     backProdController backprodController = fxmlLoader.getController();//recuperer le controller du ficher fxml
                
                backprodController.setTable(tabprods);
                
                backprodController.setDataProd(produits.get(i));//faire le block pour chaque produit de la liste
           tabprods.getChildren().add(produit);
                    } catch (IOException ex) {
                         System.out.println(ex.getMessage());   
                    }
            
         
         }

                
                } catch (SQLException ex) {
            Logger.getLogger(grandprodController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void tri(MouseEvent event) throws SQLException {
         ServiceProduit sp = new ServiceProduit();
          
         
         produits=sp.trierProduitParNom();
         this.tabprods.getChildren().clear();
         for(int i=0;i<produits.size();i++){
             FXMLLoader fxmlLoader= new FXMLLoader();
             
             fxmlLoader.setLocation(getClass().getResource("backProd.fxml"));//recuperer le fichier fxml
             
             //    prod;
             try {
                 AnchorPane        produit = fxmlLoader.load();
                 
                 backProdController backprodController = fxmlLoader.getController();//recuperer le controller du ficher fxml
                
                 backprodController.setTable(tabprods);
                 
                 backprodController.setDataProd(produits.get(i));//faire le block pour chaque produit de la liste
                 tabprods.getChildren().add(produit);
             } catch (IOException ex) {
                 System.out.println(ex.getMessage());

             }
             
             
             
             
         }

    }

    @FXML
    private void recherche(MouseEvent event) {
         ServiceProduit sp = new ServiceProduit();
         String marque=searchm.getText();
         String type=searcht.getText();
         
        
         this.tabprods.getChildren().clear();
         for(int i=0;i<produits.size();i++){
             if(sp.rechercheProd(produits.get(i), marque, type)){
             FXMLLoader fxmlLoader= new FXMLLoader();
             
             fxmlLoader.setLocation(getClass().getResource("backProd.fxml"));//recuperer le fichier fxml
             
             //    prod;
             try {
                 AnchorPane        produit = fxmlLoader.load();
                 
                 backProdController backprodController = fxmlLoader.getController();//recuperer le controller du ficher fxml
               
                 backprodController.setTable(tabprods);
                 
                 backprodController.setDataProd(produits.get(i));//faire le block pour chaque produit de la liste
                 tabprods.getChildren().add(produit);
             } catch (IOException ex) {
                 System.out.println(ex.getMessage());

             }
             
             
             
             
         }

    }
    } 
     @FXML
    private void categorie(javafx.scene.input.MouseEvent event) throws IOException {
            try {
            Parent parent = FXMLLoader.load(getClass().getResource("backCats.fxml"));
            Scene scene = new Scene(parent);
            Stage secstage = new Stage();
            secstage.setScene(scene);
            secstage.initStyle(StageStyle.UTILITY);
            secstage.show();
        } catch (IOException ex) {
            Logger.getLogger(grandprodController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
     @FXML
    private void stat(javafx.scene.input.MouseEvent event) {
         FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("stat.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(grandprodController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                             Parent parent = loader.getRoot();
                            Stage stat = new Stage();
                            stat.setScene(new Scene(parent));
                            stat.initStyle(StageStyle.UTILITY);
                            stat.show();
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
    private void gocat(ActionEvent event) throws IOException {
         Parent parent = FXMLLoader.load(getClass().getResource("backCats.fxml"));
            Scene scene = new Scene(parent);
            Stage catstage = new Stage();
            catstage.setScene(scene);
            catstage.initStyle(StageStyle.UTILITY);
            catstage.show();
    }
}
