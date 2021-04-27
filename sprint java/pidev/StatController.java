/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Entities.Produit;
import Services.ServiceProduit;
import Utils.MaConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * FXML Controller class
 *
 * @author SDIRI Yasmine
 */
public class StatController implements Initializable {

    
      String query = null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    Produit produit = null ;
    ServiceProduit sp= new ServiceProduit();
    @FXML
    private PieChart piChart;
    
    ObservableList<PieChart.Data> pieChartData;
 
    
ArrayList<String>name=new ArrayList<String>();

ArrayList<Integer>quantite=new ArrayList<Integer>();
   String query1 = null;
    Connection connection1 = null ;
    PreparedStatement preparedStatement1 = null ;
    ResultSet resultSet1 = null ;
    Produit produit1 = null ;
    
    @FXML
    private PieChart piChart1;
    
    ObservableList<PieChart.Data> pieChartData1;
 
    
ArrayList<String>marque=new ArrayList<String>();

ArrayList<Integer>quantite1=new ArrayList<Integer>();
    /**
     * Initializes the controller class.
     */
public void loadData() {
     try {
             pieChartData=FXCollections.observableArrayList();
             query="SELECT * FROM produit";
            connection = MaConnection.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            
             while (resultSet.next()){
             pieChartData.add(new PieChart.Data(resultSet.getString("nom_p"),resultSet.getInt("quantite_p")));
             name.add(resultSet.getString("nom_p"));
             quantite.add(resultSet.getInt("quantite_p"));
             }
        } catch (SQLException ex) {
            Logger.getLogger(StatController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("er");
        }
    /* try {
             pieChartData1=FXCollections.observableArrayList();
             query1="SELECT * FROM produit";
            connection1 = MaConnection.getInstance().getConnection();
            preparedStatement1 = connection1.prepareStatement(query1);
            resultSet1 = preparedStatement1.executeQuery();
            
             while (resultSet1.next()){
             pieChartData1.add(new PieChart.Data(resultSet1.getString("marque_p"),resultSet1.getInt("quantite_p")));
             marque.add(resultSet1.getString("marque_p"));
             quantite1.add(resultSet1.getInt("quantite_p"));
             }
        } catch (SQLException ex) {
            Logger.getLogger(StatController.class.getName()).log(Level.SEVERE, null, ex);
            
        }*/
     
     
}

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        loadData();
        piChart.setData(pieChartData);
       // piChart1.setData(pieChartData1);
        
    }    
    
    
}
