 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.Centre;
import java.sql.SQLException;
import java.util.List;


/**
 *
 * @author DEMNI Anis
 */
public interface IServiceCentre {

     public void addCentre(Centre p) throws SQLException;

    public List<Centre> getCentre() throws SQLException;

  

    public void deleteCentre(int id) throws SQLException;

    public void updateCentre(Centre p) throws SQLException;
    
 
     public void trierCentreParNom()throws SQLException;
     
     public boolean rechercherCentre(String nom)throws SQLException ;
}
