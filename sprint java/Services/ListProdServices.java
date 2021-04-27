/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import Entities.ListProd;
import Entities.Produit;
import interfaces.IServicesListProd;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import Utils.MaConnection;

/**
 *
 * @author DELL
 */
public class ListProdServices implements IServicesListProd {
Connection cnx;

   
    public ListProdServices() {
 cnx = MaConnection.getInstance().getConnection();
    }

    @Override
     public void addListProd(ListProd p) throws SQLException {
     Statement stm = cnx.createStatement();
     // String s= "en attente";
        String query = "INSERT INTO `listprod` "
                + "(`id`,`referenceprod`,`userid`,`prix`,`quantity`) values (NULL,'"+p.getReferenceProd()+"',"+p.getIdUser()+","+p.getPrix()+","+p.getQuantity()+")";
             
        stm.executeUpdate(query);  
    }

    @Override 
        public List<ListProd> getListProduit() throws SQLException {
           Statement stm = cnx.createStatement();
        String query = "select * from `listprod`";
        ResultSet rst = stm.executeQuery(query);
        List<ListProd> produits = new ArrayList<>();
        while (rst.next()) {
          //  Produit p2 = new Produit();
           // c.setId(rst.getInt("id"));
           //c.setPrixTot((float) rst.getDouble(2));
         ListProd p = new ListProd(rst.getInt(1),rst.getInt(2), rst.getString(3),rst.getDouble(4),rst.getInt(5));
            //p2.setCathId(rst.getInt(4));
            produits.add(p);
            
            
          //  produits.add(p);
        }
  // Map<Produit,Integer> map = produits.stream().collect(Collectors.groupingBy(prod->prod, 1));
 // produits.stream().map(i->)
  return produits;
     
    }
    public void deleteProd(String reference,int idUser) throws SQLException{
        Statement stm = cnx.createStatement();
    //  String s= "en attente";
        String query = "delete from `listprod` where referenceprod = '"+reference+"' and userid + "+idUser;
                
             
        stm.executeUpdate(query);
        
        
        
        
    }

    @Override
    public void deleteListProduit(int idUser) throws SQLException {
        Statement stm = cnx.createStatement();
    //  String s= "en attente";
        String query = "delete from `listprod` where id = "+idUser;
                
             
        stm.executeUpdate(query);
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
    
    }

    @Override
    public void updateListProduit(ListProd lp) throws SQLException {
          Statement stm = cnx.createStatement();
   
  
  
               
               String query = "UPDATE `commande` SET `referenceprod`= '"+lp.getReferenceProd()+"', `prixTotal` =" +lp.getPrix()+", `userid`= "+lp.getIdUser()+", `quantity`= "+lp.getQuantity()+" where id = "+lp.getId();
            stm.executeUpdate(query);
        
// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    //public void

    @Override
    public int getContiteListProd(int userId) throws SQLException {
    LinkedHashMap<Integer,List<ListProd>> map = getListByUser();
    return map.get(userId).stream().map(i->i.getQuantity()).reduce(0, (a,b)->a+b);


//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Double getPrixTotal(int userId) throws SQLException {
                 LinkedHashMap<Integer,List<ListProd>> map = getListByUser();
                return map.get(userId).stream().map(i->i.getPrix()*i.getQuantity()).reduce((a,b)->a+b).orElse(0.00);

//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
    }

    @Override
    public LinkedHashMap<Integer, List<ListProd>> getListByUser() throws SQLException {
        List<ListProd> list = getListProduit();
        return list.stream().collect(Collectors.groupingBy(i->i.getIdUser(),LinkedHashMap::new,Collectors.toList()));


//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LinkedHashMap<Integer, List<ListProd>> getListOrderPrixTotal() throws SQLException {
   Comparator<ListProd> c = new Comparator<ListProd>() {
       @Override
       public int compare(ListProd t, ListProd t1) {
          String s = (t.getQuantity()* t.getPrix())+"";
          String s2 = (t1.getQuantity()* t1.getPrix())+"";
          return s2.compareTo(s);
       }
   };
       return getListByUser().entrySet().stream().map((i) -> {
            
                    List<ListProd> list = i.getValue();
                    Collections.sort(list,c);
                    i.setValue(list);
                    return i;
        }).collect(Collectors.toMap(i->i.getKey(), i->i.getValue(),(a,b)->b, LinkedHashMap :: new));
           

// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String bestSeller() throws SQLException {
      List<Map.Entry<String,List<ListProd>>> l=  getListProduit().stream().collect(Collectors.groupingBy(i->i.getReferenceProd(), LinkedHashMap::new, Collectors.toList())).entrySet().stream().sorted((a,b)->b.getValue().size()-a.getValue().size()).collect(Collectors.toList());
      if(l.isEmpty()){
          
          return "";
      }
      else{
           return l.get(0).getKey();
      }
//    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    
    
    
}
