/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Conseil;
import static Entities.Conseil.ComparatorReference;
import Interfaces.IServiceConseil;
import Utils.MaConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author amine
 */
public class ServiceConseil implements IServiceConseil {
 Connection cnx;

    public ServiceConseil() {
        cnx = MaConnection.getInstance().getConnection();
    }
   
    @Override
    public void addConseil(Conseil c) throws SQLException {
          Statement stm = cnx.createStatement();
        String query = "INSERT INTO `conseil` "
                + "(`reference`,`remarques`,`date_red`,`date_limite`,`nom_derma`,`email`)"
                + " VALUES ('" + c.getReference() + "',"
                + " '" + c.getRemarques() + "',"+" '"+ c.getDate_red()+"',"+" '"+ c.getDate_limite()+"',"+" '"+ c.getNom_derma()+"',"+" '"+ c.getEmail()+"')";
        stm.executeUpdate(query);
        
    }

    @Override
    public List<Conseil> getConseil() throws SQLException {
        Statement stm = cnx.createStatement();
        String query = "select * from `conseil`";
        ResultSet rst = stm.executeQuery(query);
        List<Conseil> conseil = new ArrayList<>();
        while (rst.next()) {
            Conseil c = new Conseil();
            c.setReference(rst.getInt("reference"));
            c.setRemarques(rst.getString("remarques"));
            c.setDate_red(rst.getDate("date_red"));
             c.setDate_limite(rst.getDate("date_limite"));
            c.setNom_derma(rst.getString("nom_derma"));
             c.setEmail(rst.getString("email")); 
            
            conseil.add(c);
        }
     return conseil;
    }

    @Override
    public Conseil getById(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteConseil(Conseil p) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteConseil(int id) throws SQLException {
          Statement stm = cnx.createStatement();
        String query = "Delete  from `conseil` where reference = '"+id+"' ";
         stm.executeUpdate(query);
    }

    @Override
    public void updateConseil(Conseil c) throws SQLException {
        Statement stm = cnx.createStatement();
        String query = "UPDATE `conseil` SET  reference ='"+c.getReference()+"',remarques= '"+c.getRemarques()+"',date_red = '"+c.getDate_red()+"',date_limite = '"+c.getDate_limite()+"',nom_derma = '"+c.getNom_derma()+"',email = '"+c.getEmail()+"' where reference ='"+c.getReference()+"'    ";
        stm.executeUpdate(query);
    }

    @Override
    public boolean getConseil(Conseil p) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void trierConseilParId(List c) {
       Collections.sort(c,ComparatorReference);
    }
    @Override
    public void trierConseilDate(List c) {
      Collections.sort(c);
    }

    @Override
    public Boolean rechercheConseil(Conseil c , String email) {
       
     
         if (c.getEmail().equals(email))
         { 
             return true;
         } 
     
     return false;
    }
    

}
