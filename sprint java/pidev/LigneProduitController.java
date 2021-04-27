/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import Entities.Produit;
import Services.ServiceCommande;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class LigneProduitController implements Initializable {

   
    @FXML
    private Label refernceLabel;
    @FXML
    private Label nomlabel;
    @FXML
    private Label prixLabel;
    
    private Produit produit;  
     private LineChart<?, ?> lineStat;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setProduit(Produit p , LineChart line ){
        this.produit = p;
        this.refernceLabel.setText(p.getref());
        this.nomlabel.setText(p.getnom());
        this.prixLabel.setText(p.getPrix_p()+"");
        this.lineStat = line;
        
        
        
    }
    
    @FXML
    private void viewCharts(TouchEvent event) {
    
    
    
    }

   
    @FXML
    private void clickchart(MouseEvent event) {
    
     try {
            System.out.println("click");
            this.lineStat.getData().clear();
            ServiceCommande sc = new ServiceCommande();
            HashMap<Integer,Integer> produitStat = sc.bestSellersParAns(this.produit.getref());
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
        }
     catch (SQLException ex) {
            Logger.getLogger(LigneProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
           
        
            
     }

    @FXML
    private void chartUp(MouseEvent event) {
        System.out.println("click");
    
    }
}
