/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import Entities.Commade;
import Entities.User;
import Services.ServiceCommande;
import Services.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class UpdateCommandeController implements Initializable {

   private Label enAttenteLabel;
    private Label encoursLabel;
    private Label nbCommande;
 
    @FXML
    private ComboBox etaCommande;
    
    private Commade commande;
    private VBox tabCommande;
    @FXML
    private ComboBox users;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    public void setCommande(Commade c){
        try {
            this.commande = c;
            ObservableList<String> list = FXCollections.observableArrayList("en attente","livree","en cours");
            
            this.etaCommande.setItems(list);
            this.etaCommande.setValue(this.commande.getEtat());
            ServiceUser sc = new ServiceUser();
            
            
            List<String> names = new ArrayList<>();
            List<User> user = sc.getUsers();
            user.forEach(i->names.add(i.getNom()));
           
 //String name= user.stream().filter(i->i.getId()==this.commande.getId()).findFirst().orElse(new User(0, "","aaa")).getName();
         
        //string to date
            ObservableList<String> usernames = FXCollections.observableArrayList(names);
            String name = user.stream().filter(i->i.getId()==this.commande.getUserId()).findFirst().orElse(new User()).getNom();
            
            
            this.users.setItems(usernames);
            this.users.setValue(name);
   //         this.users.setValue(name);
            /*  try {
            ServiceCommande sc = new ServiceCommande();
            
            
            List<String> names = new ArrayList<>();
            List<User> user = sc.getUsers();
            user.forEach(i->names.add(i.getName()));
            String name= user.stream().filter(i->i.getId()==this.commande.getId()).collect(Collectors.toList()).get(0).getName();
            
            ObservableList<String> usernames = FXCollections.observableArrayList(names);
            this.users.setItems(usernames);
            this.users.setValue(name);
            // TODO
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());    
            // Logger.getLogger(ModifCommandeController.class.getName()).log(Level.SEVERE, null, ex);
            }*/
        } catch (SQLException ex) {
            Logger.getLogger(ModifCommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    public void setTableCommade(VBox tableCommande){
        this.tabCommande = tableCommande;
        
        
        
        
    }

    @FXML
    private void updateCommande(MouseEvent event) {
        try {
            ServiceUser su = new ServiceUser();
           int id= su.getUsers().stream().filter(i->i.getNom().equals((String)this.users.getValue())).findFirst().orElse(new User()).getId();
            System.out.println(id);  
            Commade  c = new Commade(this.commande.getId(),this.commande.getPrixTot(), id, this.commande.getDate(), (String) this.etaCommande.getValue());
           ServiceCommande sc = new ServiceCommande();
            sc.updateCommande(c);
       this.tabCommande.getChildren().clear();
           HashMap<String,List<Commade>> listCommande =  sc.getCommandeParUser();
          this.enAttenteLabel.setText(listCommande.entrySet().stream().map(i-> i.getValue().stream().filter(a->a.getEtat().equals("en attente")).count()).reduce((a,b)->a+b).orElse(Long.parseLong("0"))+"");
               this.encoursLabel.setText(listCommande.entrySet().stream().map(i-> i.getValue().stream().filter(a->a.getEtat().equals("en cours")).count()).reduce((a,b)->a+b).orElse(Long.parseLong("0"))+"");
                       this.nbCommande.setText(listCommande.entrySet().stream().map(i-> i.getValue().stream().count()).reduce((a,b)->a+b).orElse(Long.parseLong("0"))+"");
        
           listCommande.forEach((u,com)->{
         for(int i=0;i<com.size();i++){
                             FXMLLoader fxmlLoader= new FXMLLoader();
           
            fxmlLoader.setLocation(getClass().getResource("LigneCommande.fxml"));//recuperer le fichier fxml

                //    prod;
                    try {
                AnchorPane        commande = fxmlLoader.load();
                 
                     LigneCommandeController ligneCommandeController = fxmlLoader.getController();//recuperer le controller du ficher fxml
                //System.out.println("produit " + p + "quantite" +q);
               // ligneCommandeController.setCommande(c.get(i));
                ligneCommandeController.setTable(this.tabCommande);
               ligneCommandeController.setLabels(nbCommande, enAttenteLabel, encoursLabel);
                ligneCommandeController.setDataCommande(com.get(i),u);//faire le block pour chaque produit de la liste
           this.tabCommande.getChildren().add(commande);
                    } catch (IOException ex) {
          //              Logger.getLogger(CommadeController.class.getName()).log(Level.SEVERE, null, ex);
                    }
           
                 
            
         
         }

                
                });
                
               
            
            
       
           
           
           
           
           
           
        } catch (SQLException ex) {
         
            
            
            
            
            
            System.out.println(ex.getMessage());
            //Logger.getLogger(ModifCommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
    }
    public void setLabels(Label nbCommandes,Label enAttente,Label encours){
        this.nbCommande = nbCommandes;
        this.enAttenteLabel = enAttente;
        this.encoursLabel = encours;
        
        
    }
    
    
    
    
    
    
    
}
