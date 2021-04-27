/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Utils.MaConnection;


import Entities.Categorie_centre;
import Interfaces.IServiceCategorie_centre;
import java.sql.PreparedStatement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 *
 * @author DEMNI Anis
 */
public class ServiceCategorie_centre implements IServiceCategorie_centre {
    
       Connection cnx;

    public ServiceCategorie_centre() {
        cnx = MaConnection.getInstance().getConnection();
    }

    @Override
    public void addCategorie(Categorie_centre c) throws SQLException {
        try{       
        Statement stm = cnx.createStatement();
        String query ="INSERT INTO categorie_centre ( type,specialite) VALUES ('" + c.getType() +  "','" + c.getSpecialite() + "')";
        stm.executeUpdate(query);}
     
     catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

  
    
    @Override
    public ObservableList<Categorie_centre> getCategories() throws SQLException {
         Statement stm = cnx.createStatement();
        String query = "select * from `categorie_centre`";
        ResultSet rst = stm.executeQuery(query);
        List<Categorie_centre> categorie_centres = new ArrayList<>();
        
        
        ObservableList<Categorie_centre> categorie_centre = FXCollections.observableArrayList();
        while (rst.next()) {
            
           
            categorie_centre.add(new Categorie_centre(rst.getInt("id"),rst.getString("type") , rst.getString("specialite") ));
           
            
        } 
        
     return categorie_centre;
     
    }
    
    
    
    

  

    @Override
    public void deleteCategorie(int id) throws SQLException {
      String query="DELETE FROM `categorie_centre`  WHERE id = '" + id + "'";
          PreparedStatement a = cnx.prepareStatement(query);
          a.execute();
    }

    @Override
    public void updateCategorie(Categorie_centre c) throws SQLException {
        try { Statement stm = cnx.createStatement();
        String query = "UPDATE `categorie_centre`  SET type ='"+c.getType()+"',specialite='"+c.getSpecialite()+ "' WHERE id='"+c.getId()+"' ";
         stm.executeUpdate(query);}
         catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

 
    
      
    public ObservableList<Categorie_centre> refresh() throws SQLException {
      ObservableList<Categorie_centre>  Categorie_centreList = FXCollections.observableArrayList();
            
            Categorie_centreList.clear();
            
            String query = "SELECT * FROM `categorie_centre`";
            ResultSet a = cnx.prepareStatement(query).executeQuery();
             
            
            while (a.next()){
                Categorie_centreList.add(new  Categorie_centre(
                        a.getInt("id"),
                        a.getString("type"),
                        a.getString("specialite")));
                        
                 

                        
                
            }
            
            
        
        return Categorie_centreList;
    }
    
    }

   

