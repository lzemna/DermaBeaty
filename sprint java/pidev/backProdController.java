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
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
public class backProdController implements Initializable {

    @FXML
    private Label reflabel;
    @FXML
    private Label nomlabel;
    @FXML
    private Label typelabel;
    @FXML
    private Label marquelabel;
    @FXML
    private Label prixlabel;
     @FXML
    private Label quanitelabel;
    @FXML
    private Label desclabel;
    @FXML
    private Label catlabel;
    private Produit produit;
    private VBox line;
    @FXML
    private ImageView img;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
public void setProd(Produit p){
        this.produit = p;
        
    }
    public void setDataProd(Produit p){
        this.reflabel.setText(p.getref());
       this.nomlabel.setText(p.getnom());
       this.typelabel.setText(p.getType());
       this.marquelabel.setText(p.getMarque_p());
       this.prixlabel.setText(String.valueOf(p.getPrix_p()));
       this.quanitelabel.setText(String.valueOf(p.getQuantite_p()));
       this.desclabel.setText(p.getDescription_p());
       this.catlabel.setText(p.getCategorie());
       this.img.setImage(new Image(getClass().getResourceAsStream(p.getImage_p())));
        this.setProd(p);
            
        
        
    }    

    @FXML
    private void suppprod(MouseEvent event) {
        this.line.getChildren().clear();
   
       ServiceProduit sp =  new ServiceProduit();
        try {
           sp.deleteProduit(this.produit.getref());
            List <Produit> produits=sp.getProduits();
            
     
             
            
         for(int i=0;i<produits.size();i++){
                             FXMLLoader fxmlLoader= new FXMLLoader();
           
            fxmlLoader.setLocation(getClass().getResource("backProd.fxml"));//recuperer le fichier fxml

                //    prod;
                    try {
                AnchorPane        produit = fxmlLoader.load();
                 
                     backProdController backprodController = fxmlLoader.getController();//recuperer le controller du ficher fxml
                
                backprodController.setTable(this.line);
                   
             
                backprodController.setDataProd(produits.get(i));//faire le block pour chaque produit de la liste
           this.line.getChildren().add(produit);
                    } catch (IOException ex) {
                    }
           
                 
            
         
         

                
                };
                
               
            
        
        
        
        }
        
        
        
        
        catch (SQLException ex) {
            Logger.getLogger(backProdController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      public void setTable(VBox vbox){
        this.line = vbox;
        
        
        
    }

    @FXML
    private void editprod(MouseEvent event) throws SQLException {
          produit = this.produit;
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("add_Produit.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(grandprodController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            AddProduitController addProduitsController = loader.getController();
                            addProduitsController.setUpdate(true);
                            addProduitsController.setTextField(produit.getref(),produit.getnom(),produit.getType(),produit.getMarque_p(),produit.getPrix_p(),produit.getQuantite_p(),produit.getImage_p(),produit.getDescription_p(),produit.getCategorie());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
        
    }
    
}
