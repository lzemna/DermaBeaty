/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import Entities.Produit;
import Services.ServiceCommande;
import Services.ServiceProduit;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import Entities.Commade;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
/**
 * FXML Controller class
 *
 * @author DELL
 */
public class CommadeController implements Initializable {
 
    
    
    @FXML
    private VBox tabCommande;
   @FXML
    private TextField searchInput;
    @FXML
    private Label nbCommandeLabel;

    @FXML
    private Label enAttenteLabel;

    @FXML
    private Label enCoursLabel;
    @FXML
    private VBox pageCommande;
    @FXML
    private VBox tabProduit;
    @FXML
    private LineChart<?, ?> lineStat;
    @FXML
    private AnchorPane pageStat;

    @FXML
    void chercher(KeyEvent event) {
        String value = searchInput.getText();
     
        ServiceCommande sc = new ServiceCommande();
        try {
             
            
            
   
            HashMap<String,List<Commade>> listCommande =  sc.getCommandeParUser();
             listCommande = listCommande.entrySet().stream().filter(i->i.getKey().startsWith(value)).collect(Collectors.toMap(i->i.getKey(), i->i.getValue(),(a,b)->a, HashMap::new));
             
             //System.out.println(listCommande); 
             this.tabCommande.getChildren().clear();  
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
                ligneCommandeController.setTable(tabCommande);
               ligneCommandeController.setLabels(nbCommandeLabel, enAttenteLabel, enCoursLabel);
                ligneCommandeController.setDataCommande(c.get(i),u);//faire le block pour chaque produit de la liste
           tabCommande.getChildren().add(commande);
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //setPageProduit();
        //this.pageCommande.setVisible(true);
      this.pageStat.setVisible(false);
        ServiceCommande sc = new ServiceCommande();
            try {
                HashMap<String,List<Commade>> listCommande =  sc.getCommandeParUser();
               this.enAttenteLabel.setText(listCommande.entrySet().stream().map(i-> i.getValue().stream().filter(a->a.getEtat().equals("en attente")).count()).reduce((a,b)->a+b).orElse(Long.parseLong("0"))+"");
               this.enCoursLabel.setText(listCommande.entrySet().stream().map(i-> i.getValue().stream().filter(a->a.getEtat().equals("en cours")).count()).reduce((a,b)->a+b).orElse(Long.parseLong("0"))+"");
                       this.nbCommandeLabel.setText(listCommande.entrySet().stream().map(i-> i.getValue().stream().count()).reduce((a,b)->a+b).orElse(Long.parseLong("0"))+"");
        
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
                ligneCommandeController.setTable(tabCommande);
                 ligneCommandeController.setLabels(nbCommandeLabel, enAttenteLabel, enCoursLabel);
                ligneCommandeController.setDataCommande(c.get(i),u);//faire le block pour chaque produit de la liste
           tabCommande.getChildren().add(commande);
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
    private void navigate(MouseEvent event) {
    this.pageCommande.setVisible(false);
   
    
    
    
    
    
    }

    @FXML
    private void click(MouseEvent event) {
        
                
   
        this.pageCommande.setVisible(false);
    this.pageStat.setVisible(true); 
     setPageProduit();
        
        
    }
    public void setPageProduit(){
             ServiceProduit  sp = new ServiceProduit();
        
        
        
            
        try {
            List<Produit> produits = sp.getProduits();
           for(int i=0;i<produits.size();i++){
                  
           
               FXMLLoader fxmlLoader = new FXMLLoader(); // Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
               fxmlLoader.setLocation(getClass().getResource("ligneProduit.fxml"));//recuperer le fichier fxml
               try {
                   AnchorPane    col = fxmlLoader.load(); //recuperer le block du produit
               LigneProduitController prodController = fxmlLoader.getController();//recuperer le controller du ficher fxml
               prodController.setProduit(produits.get(i),lineStat);//faire le block pour chaque produit de la liste
            this.tabProduit.getChildren().add(col);
          
               
               } catch (IOException ex) {
                   //Logger.getLogger(CouraselController.class.getName()).log(Level.SEVERE, null, ex);
               }
               
           
            
        }
              ServiceCommande sc = new ServiceCommande();
              HashMap<Integer,Integer> produitStat = sc.bestSellersParAns(produits.get(1).getref());
               HashMap<String,Integer> produitSt = new HashMap();
             
               
               
               
               HashMap<Integer,String> mois = new HashMap();
             mois.put(1, "janvier");
             mois.put(2, "fevrier");
             mois.put(3, "mars");
             mois.put(4, "avril");
             mois.put(5, "mai");
             mois.put(6, "juin");mois.put(7, "juillet");
             mois.put(8, "aout");
             mois.put(9, "septembre");
             mois.put(10, "octobre");
             mois.put(11, "novembre");
             mois.put(12, "decembre");
             
             produitStat.forEach((t, u) -> {
             produitSt.put(mois.get(t), u);
             });
          XYChart.Series[] series = new XYChart.Series[3];
            series[0] = new XYChart.Series();
            System.out.println(produitSt);
            produitSt.forEach((t, u) -> {
            series[0].getData().addAll(new XYChart.Data<>(t,u));
            });        
        
        
        this.lineStat.getData().addAll(series[0]);
           
              
      
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
//Logger.getLogger(PageMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }

    @FXML
    private void goUser(MouseEvent event) {
     try {
            Parent parent = FXMLLoader.load(getClass().getResource("back_User.fxml"));
            Scene scene = new Scene(parent);
            Stage formstage = new Stage();
            formstage.setScene(scene);
            formstage.initStyle(StageStyle.UTILITY);
            formstage.show();
        } catch (IOException ex) {
            Logger.getLogger(CommadeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }

    @FXML
    private void goLivraison(MouseEvent event) {
try {
            Parent parent = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            Scene scene = new Scene(parent);
            Stage formstage = new Stage();
            formstage.setScene(scene);
            formstage.initStyle(StageStyle.UTILITY);
            formstage.show();
        } catch (IOException ex) {
            Logger.getLogger(CommadeController.class.getName()).log(Level.SEVERE, null, ex);
        }



    }

    @FXML
    private void toCommandes(MouseEvent event) {
   this.pageCommande.setVisible(true);
   this.pageStat.setVisible(false);
    
    }
    
    
    

   
  
}
