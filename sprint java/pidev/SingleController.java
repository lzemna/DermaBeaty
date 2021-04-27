/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import Entities.Panier;
import Entities.Produit;
import Services.ServicePanier;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class SingleController implements Initializable {

    @FXML
    private Button prod_id;
    @FXML
    private Button centres_id;
    @FXML
    private Button cart_id;
    @FXML
    private Button connection_id;
      
    @FXML
    private Label nameSingleProd;
    @FXML
    private Label descriptionSingleProd;
    @FXML
    private Label priceSingleProd;
    @FXML
    private Button minus;
    @FXML
    private Button plus;
      @FXML
    private Label quantityLabel;
    private VBox tabPanier; 
    private Label quanity; 
    private Label  totalPrice; 
    @FXML
    private ImageView singleImage;
  
    @FXML
    void minusClick(MouseEvent event) {
        int value = Integer.parseInt(quantityLabel.getText());
        if(value > 1){
            
            value--;
        }
        quantityLabel.setText(value+"");
    }

    @FXML
    void plusClick(MouseEvent event) {
   int value = Integer.parseInt(quantityLabel.getText());
   value++;     
        quantityLabel.setText(value+""); 
    }
     @FXML
    void addToCart(MouseEvent event)  {
         // ProjectDemo.panier.
         Panier p = new Panier(0, this.produit, LoginController.user.getId(),Integer.parseInt(quantityLabel.getText()));
         ServicePanier sp = new ServicePanier();
       
         try {
           sp.addPanier(p);
       } catch (SQLException ex) {
           
           System.out.println(ex.getMessage());
//Logger.getLogger(SingleController.class.getName()).log(Level.SEVERE, null, ex);
       }
     setTable();
                    
                 //   ProdInCartController prodInCartController = //recuperer le controller du ficher fxml
                  
    //     PageMainController pageMain = fxmlLoader.getController();
         
         
         
         
      /*   try {
                    FXMLLoader fxmlLoader= new FXMLLoader();
           //Produit p = new Produit("nom", 0, "",1, 88);
            fxmlLoader.setLocation(getClass().getResource("panier.fxml"));
           
                 AnchorPane  panierWindow = fxmlLoader.load();
                          

             } catch (IOException ex) {
           //      Logger.getLogger(SingleController.class.getName()).log(Level.SEVERE, null, ex);
             }
         System.out.println(ProjectDemo.panier);*/
    }
  
    
    private Produit produit;
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }
    public void setProduit(Produit p){
      this.produit =p;
        this.nameSingleProd.setText(p.getnom());
        this.priceSingleProd.setText(p.getPrix_p()+" DT");
   if(p.getImage_p().equals("prod2.jpg") || p.getImage_p().equals("prod5.jpg") || p.getImage_p().equals("prod8.jpg") || p.getImage_p().equals("prod6.jpg")  || p.getImage_p().equals("prod7.jpg")  ){
          System.out.println("truelefzkn");
        this.singleImage.setImage(new Image(getClass().getResourceAsStream("/img/"+p.getImage_p())));   
          
      }
      else{
             this.singleImage.setImage(new Image(getClass().getResourceAsStream("/img/prod17.jpg")));   
     
          
      }
    }
    public void setPanier(VBox vbox,Label prixTotal,Label quantity){
        this.tabPanier = vbox;
        this.quanity = quantity;
        this.totalPrice = prixTotal;
    }
    public void setTable(){
      
        tabPanier.getChildren().clear();
        try {
            ServicePanier sp = new ServicePanier();
            HashMap<Produit,Integer> panier =  sp.affichePanier(LoginController.user.getId());
        this.quanity.setText(panier.entrySet().stream().map(i->i.getValue()).reduce(0,(a,b)->a+b)+"");
        this.totalPrice.setText(panier.entrySet().stream().map(i->i.getKey().getPrix_p()*i.getValue()).reduce((float)0.00, (a,b)->a+b)+"");
            panier.forEach((p,q)->{
                try {
                    FXMLLoader fxmlLoader= new FXMLLoader();
                    
                    fxmlLoader.setLocation(getClass().getResource("ProdInCart.fxml"));//recuperer le fichier fxml
                    
                    AnchorPane    prod = fxmlLoader.load();
                    
                    ProdInCartController prodInCartController = fxmlLoader.getController();//recuperer le controller du ficher fxml
                 
                    //    System.out.println("produit " + p + "quantite" +q);
                    prodInCartController.setDataProd(p,q);//faire le block pour chaque produit de la liste
                 prodInCartController.setElementsPanier(p,this.tabPanier,this.quanity,this.totalPrice);
                    tabPanier.getChildren().add(prod);
                } catch (IOException ex) {    
                    System.out.println(ex.getMessage());
//  Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                //   ProdController prodController = fxmlLoader.getController();
            });
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            // Logger.getLogger(PageMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
 
        
        
    }
    
}
