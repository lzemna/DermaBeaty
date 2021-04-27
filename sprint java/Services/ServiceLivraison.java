/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entities.Livraison;
import Utils.MaConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author user
 */
public class ServiceLivraison implements IService<Livraison>{
    Connection cnx = MaConnection.getInstance().getConnection();

   
 
    public HashMap<String,List<Livraison>> getLivraisonsParLiv() throws SQLException{
     Statement stm = cnx.createStatement();
        String query = "SELECT lr.nom,l.date_liv,l.destination,l.livreur_id,l.id FROM livraison l LEFT OUTER JOIN livreur lr ON l.livreur_id = lr.id";
        ResultSet rst = stm.executeQuery(query);
        HashMap<String,List<Livraison>> commandes = new HashMap();
        while (rst.next()) {
     //       Commade c = new Commade();
           // c.setId(rst.getInt("id"));
         String name = "";
         int id_liv = 0;  
         if(rst.getString(1) == null){
               
               name = "*****";
               
           }
           else{
          name = rst.getString(1);      
         id_liv = rst.getInt(4); 
           }
         
           Livraison c= new Livraison(rst.getInt(5), rst.getString(3), rst.getString(2), id_liv);
            if(commandes.containsKey(name)){
               commandes.get(name).add(c);
                
            }
            else{
            ArrayList<Livraison> l = new ArrayList();
            l.add(c);
            commandes.put(name, l);
            }
            

//commandes.put(c);
        }
     return commandes;

        
        
        
        
    }
    
    @Override
    
    
    
    
 public void ajouter(Livraison l) {
        try {
            String requete = "INSERT INTO livraison (livreur_id,destination,date_liv) VALUES (" + l.getId_Liv()+ ",'" + l.getDestination()+ "', '"+ l.getDate()+"')";
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Livraison ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Livraison l) {
        try {
            String requete = "DELETE FROM livraison WHERE id=" + l.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("livraison supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Livraison l) {
        try {
            String requete = "UPDATE livraison SET date_liv='" + l.getDate() + "',livreur_id=" + l.getId_Liv()+ ", destination ='"+l.getDestination()+"' WHERE id=" + l.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Livraison modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<Livraison> afficher() {
        List<Livraison> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM livraison";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                list.add(new Livraison(rs.getInt(1),rs.getString(3), rs.getString(4),rs.getInt(2)));
               // list.add(new Livreur(rs.getInt(1), rs.getString(2), rs.getString("prenom")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    public LinkedHashMap<Integer,List<Livraison>> getLivraisonsParLivreur(){
        return afficher().stream().collect(Collectors.groupingBy(i->i.getId_Liv(), LinkedHashMap::new,Collectors.toList()));
        
        
    }
    
    
    
    
    
    
}
