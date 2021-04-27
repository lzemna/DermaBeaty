/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import Entities.Categorie;
import Entities.Produit;
import Services.ServiceCategorie;
import Services.ServiceProduit;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
public class back_catController implements Initializable {

    @FXML
    private Label reflabel;
    @FXML
    private Label nomlabel;
     private Categorie categorie;
    private VBox line;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setCat(Categorie c){
        this.categorie = c;
        
    }
    public void setDataCat(Categorie c){
        this.reflabel.setText(c.getReference_c());
       this.nomlabel.setText(c.getNom_c());
      
        this.setCat(c);
            
        
        
    }    

    @FXML
    private void suppcat(MouseEvent event) {
        this.line.getChildren().clear();
   
       ServiceCategorie sc =  new ServiceCategorie();
        try {
           sc.deleteCategorie(this.categorie.getReference_c());
            List <Categorie> categories=sc.getCategories();
            
      //  this.enAttenteLabel.setText(listCommande.entrySet().stream().map(i-> i.getValue().stream().filter(a->a.getEtat().equals("en attente")).count()).reduce((a,b)->a+b).orElse(Long.parseLong("0"))+"");
        //       this.encoursLabel.setText(listCommande.entrySet().stream().map(i-> i.getValue().stream().filter(a->a.getEtat().equals("en cours")).count()).reduce((a,b)->a+b).orElse(Long.parseLong("0"))+"");
          //             this.nbCommande.setText(listCommande.entrySet().stream().map(i-> i.getValue().stream().count()).reduce((a,b)->a+b).orElse(Long.parseLong("0"))+"");
        
             
            
         for(int i=0;i<categories.size();i++){
                             FXMLLoader fxmlLoader= new FXMLLoader();
           
            fxmlLoader.setLocation(getClass().getResource("back_cat.fxml"));//recuperer le fichier fxml

                //    prod;
                    try {
                AnchorPane        categorie = fxmlLoader.load();
                 
                     back_catController backcatController = fxmlLoader.getController();//recuperer le controller du ficher fxml
                //System.out.println("produit " + p + "quantite" +q);
               // ligneCommandeController.setCommande(c.get(i));
                backcatController.setTable(this.line);
                   
             
                backcatController.setDataCat(categories.get(i));//faire le block pour chaque produit de la liste
           this.line.getChildren().add(categorie);
                    } catch (IOException ex) {
         //               Logger.getLogger(CommadeController.class.getName()).log(Level.SEVERE, null, ex);
                    }
           
                 
            
         
         

                
                };
                
               
            
            
            
        
        
        
        
        
        
        }
        
        
        
        
        catch (SQLException ex) {
            Logger.getLogger(back_catController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      public void setTable(VBox vbox){
        this.line = vbox;
        
        
        
    }

    @FXML
    private void editcat(MouseEvent event) throws SQLException {
          categorie= this.categorie;
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("add_Categorie.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(back_catController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            AddCategorieController addcatController = loader.getController();
                            addcatController.setUpdate(true);
                            addcatController.setTextField(categorie.getReference_c(),categorie.getNom_c());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
        
    }
    
    
}
