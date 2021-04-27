/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.CategorieDerm;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author IMNA
 */
public interface IServiceCatD {
     public void addCatDerm(CategorieDerm p) throws SQLException;
    public List<CategorieDerm> getCatDerms() throws SQLException;
    public boolean deleteCatDerm(int id) throws SQLException;
    public void updateCatDerm(CategorieDerm p) throws SQLException;
   public void trierCatNom(List c);
   public Boolean rechercheCat(CategorieDerm c , String nom); 
}
