/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.Conseil;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author amine
 */
public interface IServiceConseil {
    public void addConseil(Conseil p) throws SQLException;

    public List<Conseil> getConseil() throws SQLException;

    public Conseil getById(int id) throws SQLException;

    public void deleteConseil(Conseil p) throws SQLException;

    public void deleteConseil(int id) throws SQLException;

    public void updateConseil(Conseil p) throws SQLException;
    
    public boolean getConseil(Conseil p) throws SQLException;
     public void trierConseilParId(List c);
      public Boolean rechercheConseil(Conseil c , String email);
      public void trierConseilDate(List c) ;
}
