/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.User;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author moham
 */
public interface IServiceUser {

    public void addUser(User p) throws SQLException;

    public List<User> getUsers() throws SQLException;

    public User getById(int id) throws SQLException;

    /**
     *
     * @param p
     * @return
     * @throws SQLException
     */
    public boolean deleteUser(User p) throws SQLException;

    public boolean deleteUser(int id) throws SQLException;

    public void updateUser(User p) throws SQLException;
    
    public boolean getUser (User p) throws SQLException;

    /**
     *
     */
    public void trierUsersParNom()throws SQLException;
   
    public boolean rechercherUser(String nom)throws SQLException ;
       
}
