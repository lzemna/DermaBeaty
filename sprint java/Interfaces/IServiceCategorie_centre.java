/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.Categorie_centre;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author DEMNI Anis
 */
public interface IServiceCategorie_centre {
    
     public void addCategorie(Categorie_centre c) throws SQLException;

    public List<Categorie_centre> getCategories() throws SQLException;

  

    public void deleteCategorie(int id) throws SQLException;

    public void updateCategorie(Categorie_centre c) throws SQLException;
    
}
