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
import Entities.Livraison;
import Entities.Livreur;
import services.ServiceLivraison;
import services.ServiceLivreur;
/**
 * FXML Controller class
 *
 * @author DELL
 */
public class LivreurLineController implements Initializable {

    private VBox tabLivreur;
    private Livreur livreur;
    @FXML
    private Label nomLabel;
    @FXML
    private Label prenomLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void suppLivreur(MouseEvent event) {
        ServiceLivreur sl = new ServiceLivreur();
        sl.supprimer(this.livreur);
        refersh();


    }
    public void setDataLivreur(Livreur l, VBox tab){
     this.nomLabel.setText(l.getNom());
      this.prenomLabel.setText(l.getPrenom());
      this.tabLivreur = tab;
      this.livreur = l;
        
        
        
    }

    @FXML
    private void updateLivreur(MouseEvent event) {
        
           try {
            
           
                 System.out.println("clicked");
                 FXMLLoader fxmlLoader= new FXMLLoader();
           //Produit p = new Produit("nom", 0, "",1, 88);
            fxmlLoader.setLocation(getClass().getResource("modifLivreur.fxml"));
           
            AnchorPane ModifWindow = fxmlLoader.load();
           ModifLivreurController modifLivreurController = fxmlLoader.getController();
           modifLivreurController.setData(this.livreur,this.tabLivreur);
                   
             
            
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
private void refersh(){
    this.tabLivreur.getChildren().clear();
     ServiceLivreur sl = new ServiceLivreur();

        List<Livreur> listLivreur =  sl.afficher();

        listLivreur.forEach((u)->{
                     FXMLLoader fxmlLoader= new FXMLLoader();

    fxmlLoader.setLocation(getClass().getResource("livreurLine.fxml"));//recuperer le fichier fxml

        //    prod;
            try {
                AnchorPane        commande = fxmlLoader.load();

             LivreurLineController livraisonLineController = fxmlLoader.getController();//recuperer le controller du ficher fxml
        //System.out.println("produit " + p + "quantite" +q);
       // ligneCommandeController.setCommande(c.get(i));
        livraisonLineController.setDataLivreur(u,this.tabLivreur);//faire le block pour chaque produit de la liste
   this.tabLivreur.getChildren().add(commande);
            } catch (IOException ex) {
                 System.out.println(ex.getMessage());   
            }







        });

                                
                // TODO
            
        
        
    }
    
}
