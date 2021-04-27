/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.User;
import Interfaces.IServiceUser;
import Utils.MaConnection;
import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author IMNA
 */
public class ServiceUser implements IServiceUser {
    Connection cnx;
    public ServiceUser() {
          cnx = MaConnection.getInstance().getConnection();
    }

    
    @Override
    public void addUser(User p) throws SQLException {
        Statement stm = cnx.createStatement();
        String query = "INSERT INTO `user` "
                + "(`id`, `cin`,`email`,`roles`,`password`,`nom`, `prenom`,`adresse`,`numero`)"
                + " VALUES (NULL, '" + p.getCin() + "',"
                + " '" + p.getEmail() + "' ,"
                + " '" + p.getRoles() + "', "
                + " '" + p.getPassword() + "' ,"
                + " '" + p.getNom() + "',"
                + " '" + p.getPrenom() + "', "
                + " '" + p.getAdresse() + "',"
                + " '" + p.getNumero() + "')";
        stm.executeUpdate(query);
    }

    @Override
    public List<User> getUsers() throws SQLException {
       Statement stm = cnx.createStatement();
        String query = "select * from `user`";
        ResultSet rst = stm.executeQuery(query);
        List<User> users = new ArrayList<>();
        while (rst.next()) {
            User p2 = new User();
            p2.setId(rst.getInt("id"));
            p2.setCin(rst.getInt(1));
            p2.setEmail(rst.getString(2));
           String role = rst.getString(3);
            p2.setRoles(role);
            p2.setPassword(rst.getString(4));
            p2.setNom(rst.getString(5));
            p2.setPrenom(rst.getString(6));
            p2.setAdresse(rst.getString(7));
            p2.setNumero(rst.getInt(8));
            users.add(p2);
        }
     return users;
    }

    @Override
    public User getById(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteUser(User p) throws SQLException {
       try {
			
			Statement stmt = cnx.createStatement();
			stmt.execute("DELETE FROM `user`  WHERE id = '" + p.getId() + "'");
			return true;
		} catch (SQLException e) {
			System.out.println("Erreur :" + e.getMessage());
			return false;
		}
    }

    /**
     *
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    public boolean deleteUser(int id) throws SQLException {
        try {
			
			Statement stmt = cnx.createStatement();
			stmt.execute("DELETE FROM `user`  WHERE id = '" + id + "'");
			System.out.println("utilisateur supprimé avec succés");
                        return true;
                       
		} catch (SQLException e) {
			System.out.println("Erreur :" + e.getMessage());
			return false;
		}
    }

    @Override
    public void updateUser(User p) throws SQLException {
        try {
			//Statement stm = cnx.createStatement();
			PreparedStatement req =cnx.prepareStatement("UPDATE `user` SET  cin ='"+p.getCin()+"',email='"+p.getEmail()+"',roles='"+p.getRoles()+"',password='"+p.getPassword()+"',nom='"+p.getNom()+"', prenom='"+p.getPrenom()+"',adresse='"+p.getAdresse()+"',numero='"+p.getNumero()+"' WHERE id ='"+p.getId()+"'");
			// req.setInt(1, p.getId());
//                        req.setInt(1, p.getCin());
//                        req.setString(2,p.getEmail());
//                        req.setString(3,p.getRoles());
//                        req.setString(4,p.getPassword());
//                        req.setString(5, p.getNom());
//			req.setString(6, p.getPrenom());
//                        req.setString(7, p.getAdresse());
//			req.setInt(8, p.getNumero());
                      
			req.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Erreur :" + e.getMessage());
			
		}
    }

    @Override
    public boolean getUser(User p) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
    @Override
    public void trierUsersParNom() throws SQLException  {
        
            List<User> users = getUsers();
     //  Collections.sort(users, new ComparerParNom());
             users.sort((User o1, User o2) -> {
           return o1.getNom().compareTo(o2.getNom());
      });
            System.out.println(users);
        }

    @Override
    public boolean rechercherUser(String nom) throws SQLException {
          List<User> users = getUsers();
        for (int i=0;i<users.size();i++){
            
            if(users.get(i).getNom().equals(nom))
                System.out.println("le nom existe");
                return true;
        }
        
        return false;
    }
     
       
    
}

