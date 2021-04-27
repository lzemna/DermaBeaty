/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import Services.ServiceCommande;
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
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import Entities.Commade;
/**
 * FXML Controller class
 *
 * @author DELL
 */
public class LigneCommandeController implements Initializable {

    
  
    private Label enAttenteLabel;
    private Label encoursLabel;
    private Label nbCommande;
    @FXML
    private Label userLabel;
    @FXML
    private Label etatLabel;
    @FXML
    private Label prixTotal;
    @FXML
    private Label dateLabel;
private Commade commande;
private VBox line;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    void updateCommande(MouseEvent event) {
             try {
            
                 System.out.println(this.commande);
                 System.out.println("clicked");
                 FXMLLoader fxmlLoader= new FXMLLoader();
           //Produit p = new Produit("nom", 0, "",1, 88);
            fxmlLoader.setLocation(getClass().getResource("updateCommande.fxml"));
           
            AnchorPane ModifWindow = fxmlLoader.load();
           UpdateCommandeController updateCommandeController = fxmlLoader.getController();
             updateCommandeController.setCommande(this.commande);
                   updateCommandeController.setLabels(nbCommande, enAttenteLabel, encoursLabel);
             
             updateCommandeController.setTableCommade(this.line);
            
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
public void setCommande(Commade c){
        this.commande = c;
        
    }
    public void setDataCommande(Commade c,String name){
        this.userLabel.setText(name);
        this.dateLabel.setText(c.getDate());
        this.etatLabel.setText(c.getEtat());
        this.prixTotal.setText(c.getPrixTot()+"");
        this.setCommande(c);
            
        
        
    }    

    @FXML
    private void suppCommande(MouseEvent event) {
       this.line.getChildren().clear();
   
       ServiceCommande sc =  new ServiceCommande();
        try {
            sc.deleteCommande(this.commande.getId());
            
             HashMap<String,List<Commade>> listCommande =  sc.getCommandeParUser();
        this.enAttenteLabel.setText(listCommande.entrySet().stream().map(i-> i.getValue().stream().filter(a->a.getEtat().equals("en attente")).count()).reduce((a,b)->a+b).orElse(Long.parseLong("0"))+"");
               this.encoursLabel.setText(listCommande.entrySet().stream().map(i-> i.getValue().stream().filter(a->a.getEtat().equals("en cours")).count()).reduce((a,b)->a+b).orElse(Long.parseLong("0"))+"");
                       this.nbCommande.setText(listCommande.entrySet().stream().map(i-> i.getValue().stream().count()).reduce((a,b)->a+b).orElse(Long.parseLong("0"))+"");
        
             
             listCommande.forEach((u,c)->{
         for(int i=0;i<c.size();i++){
                             FXMLLoader fxmlLoader= new FXMLLoader();
           
            fxmlLoader.setLocation(getClass().getResource("LigneCommande.fxml"));//recuperer le fichier fxml

                //    prod;
                    try {
                AnchorPane        commande = fxmlLoader.load();
                 
                     LigneCommandeController ligneCommandeController = fxmlLoader.getController();//recuperer le controller du ficher fxml
                //System.out.println("produit " + p + "quantite" +q);
               // ligneCommandeController.setCommande(c.get(i));
                ligneCommandeController.setTable(this.line);
                    ligneCommandeController.setLabels(nbCommande, enAttenteLabel, encoursLabel);
             
                ligneCommandeController.setDataCommande(c.get(i),u);//faire le block pour chaque produit de la liste
           this.line.getChildren().add(commande);
                    } catch (IOException ex) {
         //               Logger.getLogger(CommadeController.class.getName()).log(Level.SEVERE, null, ex);
                    }
           
                 
            
         
         }

                
                });
                
               
            
            
            
        
        
        
        
        
        
        }
        
        
        
        
        catch (SQLException ex) {
            Logger.getLogger(LigneCommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void setTable(VBox vbox){
        this.line = vbox;
        
        
        
    }
    public void setLabels(Label nbCommandes,Label enAttente,Label encours){
        this.nbCommande = nbCommandes;
        this.enAttenteLabel = enAttente;
        this.encoursLabel = encours;
        
        
    }
    
}
