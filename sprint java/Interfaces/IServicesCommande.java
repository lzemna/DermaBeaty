/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.sql.SQLException;
import java.util.List;
import Entities.Commade;
import Entities.User;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 *
 * @author DELL
 */
public interface IServicesCommande {
     public void addCommande(Commade p) throws SQLException;

    public List<Commade> getCommande() throws SQLException;
      public List<User> getUsers() throws SQLException;
      public HashMap<String,List<Commade>> getCommandeParUser() throws SQLException;
   
    public void deleteCommande(int id) throws SQLException;
    
    public void updateCommande(Commade c ) throws SQLException;
    public HashMap<Integer,List<Commade>> groupCommandeParUser() throws SQLException;
    public LinkedHashMap<Integer,List<Commade>> usersFidele() throws SQLException;
    public LinkedHashMap<Integer,List<Commade>> trierCommandesParUser()throws SQLException;

}
