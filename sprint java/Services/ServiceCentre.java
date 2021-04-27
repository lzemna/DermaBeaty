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


import Entities.Centre;
import Interfaces.IServiceCentre;
import java.sql.PreparedStatement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 *
 * @author DEMNI Anis
 */
public class ServiceCentre implements IServiceCentre {

      Connection cnx;

    public ServiceCentre() {
        cnx = MaConnection.getInstance().getConnection();
    }

    @Override
     public void addCentre(Centre p) throws SQLException {

     try{   
        Statement stm = cnx.createStatement();
        String query ="INSERT INTO centre ( nom,telephone, email, horaire, description, localisation) VALUES ('" + p.getNom() +  "','" + p.getTelephone() + "','" + p.getEmail() + "','" + p.getHoraire()+ "','" + p.getDescription() + "','" + p.getLocalisation() + "')";
        stm.executeUpdate(query);}
     
     catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
   

    @Override
    public ObservableList<Centre> getCentre() throws SQLException {
        
        Statement stm = cnx.createStatement();
        String query = "select * from `centre`";
        ResultSet rst = stm.executeQuery(query);
        List<Centre> centres = new ArrayList<>();
        
        
        ObservableList<Centre> centre = FXCollections.observableArrayList();
        while (rst.next()) {
            
           
            centre.add(new Centre(rst.getInt("id"),rst.getString("nom") , rst.getInt("telephone") , rst.getString("email") , rst.getString("horaire") , rst.getString("description") , rst.getString("localisation")));
           
            
        } 
        
     return centre;
     
         

     
    }
    
    
    
    
    


    @Override
    public void deleteCentre(int id) throws SQLException {
        
        
         String query="DELETE FROM `centre`  WHERE id = '" + id + "'";
          PreparedStatement a = cnx.prepareStatement(query);
          a.execute();
         
         
         
        
       
        
    }

    public void updateCentre(Centre p ) throws SQLException {
       try {
          Statement stm = cnx.createStatement();
          String query = "UPDATE `centre` SET id ='"+p.getId()+"', nom ='"+p.getNom()+"',telephone='"+p.getTelephone()+"',email='"+p.getEmail()+"',horaire='"+p.getHoraire()+"',description='"+p.getDescription()+"', localisation='"+p.getLocalisation()+"' WHERE id='"+p.getId()+"' ";
         stm.executeUpdate(query);
       }
         catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

 
      @Override
    public void trierCentreParNom() throws SQLException  {
        
            List<Centre> centres = getCentre();
             centres.sort((Centre p1, Centre p2) -> {
           return p1.getNom().compareTo(p2.getNom());   });
            System.out.println(centres);
        }
       @Override
    public boolean rechercherCentre(String nom) throws SQLException {
          List<Centre> centres = getCentre();
        for (int i=0;i<centres.size();i++){
            
            if(centres.get(i).getNom().equals(nom))
          
              System.out.println(centres.get(i).toString());
              
        }
        System.out.println("le centre n'existe pas");
        return false;
    }
    
    
    
   
    
    public ObservableList<Centre> refresh() throws SQLException {
      ObservableList<Centre>  centreList = FXCollections.observableArrayList();
            
            centreList.clear();
            
            String query = "SELECT * FROM `centre`";
            ResultSet a = cnx.prepareStatement(query).executeQuery();
             
            
            while (a.next()){
                centreList.add(new  Centre(
                        a.getInt("id"),
                        a.getString("nom"),
                        a.getInt("telephone"),
                        a.getString("email"),
                        a.getString("horaire"),
                        a.getString("description"),
                        a. getString("localisation")));
                 

                        
                
            }
            
            
        
        return centreList;
    }
    
    public Boolean rechercheCentre(Centre c , String nom) {
       
     
         if (c.getNom().equals(nom))
         { 
             return true;
         } 
     
     return false;
    }
}
