/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Commade;
import Entities.Panier;
import Entities.Produit;
import Entities.User;
import interfaces.IServicesCommande;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import Utils.MaConnection;

/**
 *
 * @author DELL
 */
public class ServiceCommande implements IServicesCommande {
     Connection cnx;

    public ServiceCommande() {
        cnx = MaConnection.getInstance().getConnection();
    }

   
    @Override
    public void addCommande(Commade c) throws SQLException {
      Statement stm = cnx.createStatement();
      String s= "en attente";
        String query = "INSERT INTO `commande` "
                + "(`id`, `prixtot`, `date`,`etat`,`userid`) values (NULL," + c.getPrixTot() + ",'"+c.getDate()+"','"+s+"',"+c.getUserId()+")"  ;    //)";
             
        stm.executeUpdate(query);
        
        
    }
     
    public void adddetails(Panier panier) throws SQLException {
      Statement stm = cnx.createStatement();
             
     String query;
       query = "INSERT INTO `details` "
                + "(`id`, `quantity`, `id_user`,`id_prod`,`id_commande`) values (NULL," + panier.getQuantity() + ","+panier.getUserId()+",'"+panier.getProduit().getref()+"',(SELECT MAX(id) FROM commande GROUP BY userid HAVING userid ="+panier.getUserId()+"))"  ;    //)";
      
        stm.executeUpdate(query);  
        //         + "(`id`, `prixtot`, `date`,`etat`,`userid`) values (NULL," + c.getPrixTot() + ",'"+c.getDate()+"','"+s+"',"+c.getUserId()+")"  ;    //)";
   //   stm.executeUpdate(query);  
        
    }

    @Override
    public List<Commade> getCommande() throws SQLException {
        Statement stm = cnx.createStatement();
        String query = "select * from `commande`";
        
        ResultSet rst = stm.executeQuery(query);
        List<Commade> commandes = new ArrayList<>();
        while (rst.next()) {
            Commade c = new Commade();
           // c.setId(rst.getInt("id"));
           c.setPrixTot( rst.getDouble(2));
            c.setDate(rst.getDate(3).toString());
          c.setEtat(rst.getString(4));
          c.setUserId(rst.getInt(5));
            
            commandes.add(c);
        }
     return commandes;
    }

    @Override
    public HashMap<String, List<Commade>> getCommandeParUser() throws SQLException {
        
Statement stm = cnx.createStatement();
        String query = "SELECT u.nom,c.id,c.etat,c.userid,c.prixtot,c.date FROM user u INNER JOIN commande c ON u.id = c.userid";
        ResultSet rst = stm.executeQuery(query);
        HashMap<String,List<Commade>> commandes = new HashMap();
        while (rst.next()) {
     //       Commade c = new Commade();
           // c.setId(rst.getInt("id"));
           String name = rst.getString(1);
           Commade c= new Commade(rst.getInt(2),rst.getDouble(5), rst.getInt(4), rst.getDate(6).toString(), rst.getString(3));
            if(commandes.containsKey(name)){
               commandes.get(name).add(c);
                
            }
            else{
            ArrayList<Commade> l = new ArrayList();
            l.add(c);
            commandes.put(name, l);
            }
            

//commandes.put(c);
        }
     return commandes;

//    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteCommande(int id) throws SQLException {
            Statement stm = cnx.createStatement();
        //  String s= "en attente";
            String query = "delete from `commande` where id ="+id;
                //    + "(`id`, `prixtot`, `date`,`etat`,`userid`) values (NULL," + c.getPrixTot() + ",'"+c.getDate()+"','"+s+"',"+c.getUserId()+")"  ;    //)";

            stm.executeUpdate(query);   

//  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   // UPDATE `commande` SET `prixtot` = '800', `date` = '2021-04-15', `etat` = 'en attente' WHERE `commande`.`id` = 48;

    @Override
    public void updateCommande(Commade c) throws SQLException {
    Statement stm = cnx.createStatement();
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd");
               String date =  dtf.format(LocalDate.parse(c.getDate()));
  
               
               String query = "UPDATE `commande` SET `prixtot` = "+c.getPrixTot()+", date  ='" +date +"', etat= '"+c.getEtat()+"', userId =" +c.getUserId()+ " where id = "+c.getId();
            stm.executeUpdate(query);
//  
    }
    public Map<String,List<Commade>> trieFideleClient() throws SQLException{
        HashMap <String,List<Commade>> map = getCommandeParUser();
    return map.entrySet().stream().sorted((a,b)->a.getValue().size()-b.getValue().size()).collect(Collectors.toMap(i->i.getKey(), i->i.getValue()));
        
        
    }

    @Override
    public  HashMap<Integer,List<Commade>> groupCommandeParUser() throws SQLException{
    //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    List<Commade> commandes = getCommande();
  Map<Integer,List<Commade>> myMap= commandes.stream().collect(Collectors.groupingBy(i->i.getUserId())).entrySet().stream().sorted((a,b)->a.getValue().size()-b.getValue().size()).collect(Collectors.toMap(i->i.getKey(), i->i.getValue()));
    HashMap<Integer,List<Commade>> map = new HashMap();
    map.entrySet().addAll(myMap.entrySet());
    return map;
    }
     @Override
    public LinkedHashMap<Integer,List<Commade>> usersFidele() throws SQLException{
        List<Commade> commandes = getCommande();
 return commandes.stream().collect(Collectors.groupingBy(i->i.getUserId())).entrySet().stream().sorted((a,b)->b.getValue().size()-a.getValue().size()).collect(Collectors.toMap(i->i.getKey(), i->i.getValue(),(b,a)->b,LinkedHashMap::new));        
    }

    @Override
    public LinkedHashMap<Integer, List<Commade>> trierCommandesParUser() throws SQLException {
        List<Commade> commandes = getCommande();
            return commandes.stream().collect(Collectors.groupingBy(i->i.getUserId())).entrySet().stream().sorted((a,b)->b.getValue().size()-a.getValue().size()).collect(Collectors.toMap(i->i.getKey(), i->i.getValue(),(b,a)->b,LinkedHashMap::new));
        
//    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> getUsers() throws SQLException {
    Statement stm = cnx.createStatement();
        String query = "select * from `user`";
        ResultSet rst = stm.executeQuery(query);
        List<User> usernames = new ArrayList<>();
        while (rst.next()) {
            
           // c.setId(rst.getInt("id"));
         
            
           // usernames.add(new User(rst.getInt(1),rst.getString(2), rst.getString(3)));
        }
     return usernames;       



// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     public  HashMap<Produit,Integer> getDetails(int userid) throws SQLException {
        Statement stm = cnx.createStatement();
        String query = "SELECT p.reference_p ,p.nom_p,p.type_p,marque_p, p.prix_p ,p.quantite_p,p.image_p,p.description_p,p.quantite_v,p.couleur,p.categorie,d.quantity,d.id_user FROM `details` d inner JOIN produit p on d.id_prod = p.reference_p INNER join commande c on c.id = d.id_commande where c.userid = "+userid+" and c.id = (SELECT MAX(id) FROM commande GROUP BY userid HAVING userid ="+userid+")";
        
        ResultSet rst = stm.executeQuery(query);
        HashMap<Produit,Integer> details = new HashMap<>();
        //List<Commade> commandes = new ArrayList<>();
        while (rst.next()) {
         Produit p = new Produit(rst.getString(1),rst.getString(2), rst.getString(3), rst.getString(4),rst.getFloat(5), rst.getInt(6), rst.getString(7), rst.getString(8), rst.getInt(9),rst.getString(10), rst.getString(11));
             if(details.containsKey(p)){
                int quantity = details.get(p);
                details.put(p, rst.getInt(5)+quantity);
                
                
                
            }
            else{
                details.put(p, rst.getInt(5));
            }
        }
     return details;
    }
     
     public LinkedHashMap<Produit,Integer> bestSellers() throws SQLException{
         
        Statement stm = cnx.createStatement();
        String query = "SELECT p.reference_p ,p.nom_p,p.type_p,marque_p, p.prix_p ,p.quantite_p,p.image_p,p.description_p,p.quantite_v,p.couleur,p.categorie ,d.quantity,d.id_user FROM `details` d inner JOIN produit p on d.id_prod = p.reference_p INNER join commande c on c.id = d.id_commande";
         HashMap<Produit,Integer> details = new HashMap<>();
        ResultSet rst = stm.executeQuery(query);
        while (rst.next()) {
        Produit p = new Produit(rst.getString(1),rst.getString(2), rst.getString(3), rst.getString(4),rst.getFloat(5), rst.getInt(6), rst.getString(7), rst.getString(8), rst.getInt(9),rst.getString(10), rst.getString(11));
             if(details.containsKey(p)){
                int quantity = details.get(p);
                details.put(p, rst.getInt(12)+quantity);
                
                
                
            }
            else{
                details.put(p, rst.getInt(12));
            }
        }
     return details.entrySet().stream().sorted((a,b)->b.getValue()-a.getValue()).collect(Collectors.toMap(a->a.getKey(), b->b.getValue(),(a,b)->b, LinkedHashMap::new));
         
     }
      public HashMap<Integer,Integer> bestSellersParAns(String reference) throws SQLException{
         
        Statement stm = cnx.createStatement();
        String query = "SELECT p.reference_p ,p.nom_p,p.type_p,marque_p, p.prix_p ,p.quantite_p,p.image_p,p.description_p,p.quantite_v,p.couleur,p.categorie,d.quantity ,extract(month FROM(c.date)) from details d inner join commande c on d.id_commande = c.id INNER join produit p on p.reference_p = d.id_prod";
         //HashMap<String,Integer> details = new HashMap<>();
         HashMap<Integer,List<Panier>> details1 = new HashMap<>();
      
         ResultSet rst = stm.executeQuery(query);
        while (rst.next()) {
        Produit p = new Produit(rst.getString(1),rst.getString(2), rst.getString(3), rst.getString(4),rst.getFloat(5), rst.getInt(6), rst.getString(7), rst.getString(8), rst.getInt(9),rst.getString(10), rst.getString(11));
         Panier panier  = new Panier(0, p, 1,rst.getInt(12));
         int mois = rst.getInt(13) ;
       /* LinkedHashMap<Integer,String> nomMois = new LinkedHashMap();
        nomMois.put(1, "janvier");
        nomMois.put(1, "janvier");   
        nomMois.put(1, "janvier"); 
        nomMois.put(1, "janvier");*/ 
         if(details1.containsKey(mois)){
                //int quantity = details.get(p);
             
               details1.get(mois).add(panier);
             //   details.put(p, rst.getInt(5)+quantity);
                
                
                
            }
            else{
             ArrayList<Panier> l = new ArrayList(); 
             l.add(panier);
             details1.put(mois,l);
            }
        }
        
     return  details1.entrySet().stream().map(i->{
             
             int quantity = i.getValue().stream().filter(a->a.getProduit().getref().equals(reference)).map(a->a.getQuantity()).reduce(0,(a,b)->a+b);
             
             
             //Integer reduce = list.getValue().stream().filter(a->a.getProduit().getReference().equals(reference)).map(i->i.getQuantity()).reduce(0,(a,b)->a+b);
             Map<Integer,Integer> map = new HashMap<>();
             map.put(i.getKey(), quantity);
             
             
             return map.entrySet().stream().findFirst().orElse(new Map.Entry<Integer, Integer>() {
                 @Override
                 public Integer getKey() {
              return 0;   }

                 @Override
                 public Integer getValue() {
                     return 0;
//      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                 }

                 @Override
                 public Integer setValue(Integer v) {
                   return 0;
                 }
             });
         }).collect(Collectors.toMap(c->c.getKey(), c->c.getValue(),(a,b)->a, LinkedHashMap::new));
        
     
         
     }
     

    
    
    
}
