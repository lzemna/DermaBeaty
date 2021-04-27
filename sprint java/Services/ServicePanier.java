/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import Entities.Panier;
import Entities.Produit;
import interfaces.IServicePanier;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;
import Utils.MaConnection;

/**
 *
 * @author DELL
 */
public class ServicePanier implements IServicePanier {
  Connection cnx;

    public ServicePanier() {
        cnx = MaConnection.getInstance().getConnection();
    }
    @Override
    public void addPanier(Panier panier) throws SQLException {
        Statement stm = cnx.createStatement();
      String s= "en attente";
        String query = "INSERT INTO `panier` "
                + "(`id`, `quantity`, `userid`,`referenceprod`) values (NULL," + panier.getQuantity() + ","+panier.getUserId()+",'"+panier.getProduit().getref()+"')"  ;    //)";
             
        stm.executeUpdate(query);
     }
    

    @Override
    public HashMap<Produit, Integer> affichePanier(int userId) throws SQLException {
        Statement stm = cnx.createStatement();
        String query = "SELECT p.reference_p ,p.nom_p,p.type_p,marque_p, p.prix_p ,p.quantite_p,p.image_p,p.description_p,p.quantite_v,p.couleur,p.categorie,pan.userid,pan.quantity,pan.id FROM produit p INNER JOIN panier pan  ON pan.referenceprod = p.reference_p";
        ResultSet rst = stm.executeQuery(query);
        HashMap<Produit,Integer> panier = new LinkedHashMap();
            
        while (rst.next()) {
     //       Commade c = new Commade();
           // c.setId(rst.getInt("id"));
           
        Produit p = new Produit(rst.getString(1),rst.getString(2), rst.getString(3), rst.getString(4),rst.getFloat(5), rst.getInt(6), rst.getString(7), rst.getString(8), rst.getInt(9),rst.getString(10), rst.getString(11));
          int quantity = rst.getInt(13);
        //   list.add(new Panier(rst.getInt(6), p,1, quantity));
           if(panier.containsKey(p)){
        panier.put(p, panier.get(p)+quantity);
             
           }
           else{
                 panier.put(p, quantity);
           }
           
            

//commandes.put(c);
        }
        return panier;
        
    // return list.stream().collect(Collectors.toMap(i->i.getProduit(), i->i.getQuantity(),(a,b)->a+b, LinkedHashMap::new)); nes2el 3leha hosni

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void DeletePanier(String reference,int userid) throws SQLException {
        Statement stm = cnx.createStatement();
        //  String s= "en attente";
            String query = "delete from `panier` where referenceprod ='"+reference+"' and userid = "+userid;
                //    + "(`id`, `prixtot`, `date`,`etat`,`userid`) values (NULL," + c.getPrixTot() + ",'"+c.getDate()+"','"+s+"',"+c.getUserId()+")"  ;    //)";

            stm.executeUpdate(query);   

        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void DeletePan(int id) throws SQLException {
        Statement stm = cnx.createStatement();
        //  String s= "en attente";
            String query = "delete from `panier` where userid ="+id;
                //    + "(`id`, `prixtot`, `date`,`etat`,`userid`) values (NULL," + c.getPrixTot() + ",'"+c.getDate()+"','"+s+"',"+c.getUserId()+")"  ;    //)";

            stm.executeUpdate(query);   

        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
