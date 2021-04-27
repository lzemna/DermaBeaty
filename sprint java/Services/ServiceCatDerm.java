/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.CategorieDerm;

import Interfaces.IServiceCatD;
import Utils.MaConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author IMNA
 */
public class ServiceCatDerm implements IServiceCatD{
Connection cnx;
    public ServiceCatDerm() {
          cnx = MaConnection.getInstance().getConnection();
    }

    @Override
    public void addCatDerm(CategorieDerm p) throws SQLException {
        Statement stm = cnx.createStatement();
        String query = "INSERT INTO `categorie_derm` "
                + "(`ref`, `localisation`,`nom_cat`)"
                + " VALUES (NULL, '" + p.getLocalisation()+ "',"
                + " '" + p.getNomCat()+  "')";
        stm.executeUpdate(query);
    }

    @Override
    public List<CategorieDerm> getCatDerms() throws SQLException {
         Statement stm = cnx.createStatement();
        String query = "select * from `categorie_derm`";
        ResultSet rst = stm.executeQuery(query);
        List<CategorieDerm> catderms = new ArrayList<>();
        while (rst.next()) {
            CategorieDerm p2 = new CategorieDerm();
            p2.setRef(rst.getInt("ref"));
            p2.setLocalisation(rst.getString(2));
            p2.setNomCat(rst.getString(3));
           
            
            catderms.add(p2);
        }
     return catderms;
    }

    @Override
    public boolean deleteCatDerm(int id) throws SQLException {
         try {
			
			Statement stmt = cnx.createStatement();
			stmt.execute("DELETE FROM `categorie_derm`  WHERE ref = '" + id + "'");
			System.out.println("Categorie supprimé avec succés");
                        return true;
                       
		} catch (SQLException e) {
			System.out.println("Erreur :" + e.getMessage());
			return false;
		}
    }

    @Override
    public void updateCatDerm(CategorieDerm p) throws SQLException {
       try {
			//Statement stm = cnx.createStatement();
			PreparedStatement req =cnx.prepareStatement("UPDATE `categorie_derm` SET  localisation ='"+p.getLocalisation()+"',nom_cat='"+p.getNomCat()+"' WHERE ref ='"+p.getRef()+"' ");
			
                      
			req.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Erreur :" + e.getMessage());
			
		}
    }

    @Override
    public void trierCatNom(List c) {
        Collections.sort(c);
    }

    @Override
    public Boolean rechercheCat(CategorieDerm c, String nom) {
       if (c.getNomCat().equals(nom))
         { 
             return true;
         } 
     
     return false;
    }
   
    
}
