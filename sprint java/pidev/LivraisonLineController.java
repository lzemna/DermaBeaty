/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.ServiceLivraison;
import Entities.Livraison;
/**
 * FXML Controller class
 *
 * @author DELL
 */
public class LivraisonLineController implements Initializable {

    private VBox tabLivarison;
    private Livraison livraison;
    @FXML
    private Label livreurLabel;
    @FXML
    private Label destinationLabel;
    @FXML
    private Label dateLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
   
    
    }
    
 


    public void setDataLivraison(Livraison l,String name,VBox tab){
        this.livreurLabel.setText(name);
        this.dateLabel.setText(l.getDate());
        this.destinationLabel.setText(l.getDestination());
     //   this..setText(c.getPrixTot()+"");
        this.livraison =l;
        this.tabLivarison =tab;
            
        
        
    }  
   private void refersh(){
    this.tabLivarison.getChildren().clear();
       ServiceLivraison sl = new ServiceLivraison();
            try {
                HashMap<String,List<Livraison>> listCommande =  sl.getLivraisonsParLiv();
           
                listCommande.forEach((u,c)->{
         for(int i=0;i<c.size();i++){
                             FXMLLoader fxmlLoader= new FXMLLoader();
           
            fxmlLoader.setLocation(getClass().getResource("livraisonLine.fxml"));//recuperer le fichier fxml

                //    prod;
                    try {
                        AnchorPane        commande = fxmlLoader.load();
                 
                     LivraisonLineController livraisonLineController = fxmlLoader.getController();//recuperer le controller du ficher fxml
                //System.out.println("produit " + p + "quantite" +q);
               // ligneCommandeController.setCommande(c.get(i));
                livraisonLineController.setDataLivraison(c.get(i),u,this.tabLivarison);//faire le block pour chaque produit de la liste
           this.tabLivarison.getChildren().add(commande);
                    } catch (IOException ex) {
                         System.out.println(ex.getMessage());   
//  Logger.getLogger(CommadeController.class.getName()).log(Level.SEVERE, null, ex);
                    }
           
                 
            
         
         }

                
                });
                
                                
                // TODO
            } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
// Logger.getLogger(CommadeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
    }

    
    @FXML
    private void suppLivraison(MouseEvent event) {
    ServiceLivraison sl = new ServiceLivraison();
    
    
    sl.supprimer(livraison);
    
    refersh();
    
    
    }


    @FXML
    private void updateLivarison(MouseEvent event) {
             try {
            
           
                 System.out.println("clicked");
                 FXMLLoader fxmlLoader= new FXMLLoader();
           //Produit p = new Produit("nom", 0, "",1, 88);
            fxmlLoader.setLocation(getClass().getResource("modifLivraison.fxml"));
           
            AnchorPane ModifWindow = fxmlLoader.load();
           ModifLivraisonController modifLivraisonController = fxmlLoader.getController();
            modifLivraisonController.setData(this.livraison,this.tabLivarison);
                   
             
            
//System.out.println(this.produit);
           //Label name =  singleController.getNameProd();
          // name.setText(this.produit.getNom());
            
            Stage stage = new Stage();
            Scene scene = new Scene(ModifWindow);
              stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
       
        
        } catch (IOException ex) {
                System.out.println(ex.getMessage());
//   Logger.getLogger(ProdController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
