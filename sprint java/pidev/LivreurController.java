/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import services.ServiceLivreur;
import Entities.Livreur;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class LivreurController implements Initializable {

    @FXML
    private VBox tabLivreur;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refersh();
// TODO
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

    @FXML
    private void goCentre(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("AfficherCentre.fxml"));
            Scene scene = new Scene(parent);
            Stage formstage = new Stage();
            formstage.setScene(scene);
            formstage.initStyle(StageStyle.UTILITY);
            formstage.show();
        } catch (IOException ex) {
            Logger.getLogger(PageMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
          

    
    
}
