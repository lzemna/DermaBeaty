/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Dermatologue;

import Interfaces.IServiceDerm;
import Utils.MaConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author IMNA
 */
public class ServiceDerm implements IServiceDerm {

     Connection cnx;
    public ServiceDerm() {
          cnx = MaConnection.getInstance().getConnection();
    }
   
    @Override
    public void addDerm(Dermatologue p) throws SQLException {
      Statement stm = cnx.createStatement();
        String query = "INSERT INTO `dermatologue` "
                + "(`id`, `diplome`,`formation`,`langue`,`horaire`,`modereglement`, `assurancemaladie`)"
                + " VALUES (NULL, '" + p.getDiplome()+ "',"
                + " '" + p.getFormation()+ "' ,"
                + " '" + p.getLangue()+ "', "
                + " '" + p.getHoraire()+ "' ,"
                + " '" + p.getModereglement()+ "',"
                + " '" + p.getAssurancemaladie()+ "',"
                + " '" + p.getImage()+ "')";
        stm.executeUpdate(query);
    }

    @Override
    public List<Dermatologue> getDerms() throws SQLException {
         Statement stm = cnx.createStatement();
        String query = "select * from `dermatologue`";
        ResultSet rst = stm.executeQuery(query);
        List<Dermatologue> derms = new ArrayList<>();
        while (rst.next()) {
            Dermatologue p2 = new Dermatologue();
            p2.setId(rst.getInt("id"));
            p2.setDiplome(rst.getString(2));
            p2.setFormation(rst.getString(3));
            p2.setLangue(rst.getString(4));
            p2.setHoraire(rst.getString(5));
            p2.setModereglement(rst.getString(6));
            p2.setAssurancemaladie(rst.getString(7));
            p2.setImage(rst.getString("image"));
            
            derms.add(p2);
        }
     return derms;
    }

    @Override
    public boolean deleteDerm(int id) throws SQLException {
         try {
			
			Statement stmt = cnx.createStatement();
			stmt.execute("DELETE FROM `dermatologue`  WHERE id = '" + id + "'");
			System.out.println("dermatologue supprimé avec succés");
                        return true;
                       
		} catch (SQLException e) {
			System.out.println("Erreur :" + e.getMessage());
			return false;
		}
    }

    @Override
    public void updateDerm(Dermatologue p) throws SQLException {
  try {
			//Statement stm = cnx.createStatement();
			PreparedStatement req =cnx.prepareStatement("UPDATE `dermatologue` SET  diplome ='"+p.getDiplome()+"',formation='"+p.getFormation()+"',langue='"+p.getLangue()+"',horaire='"+p.getHoraire()+"',modereglement='"+p.getModereglement()+"', assurancemaladie='"+p.getAssurancemaladie()+"', image='"+p.getImage()+"' WHERE id ='"+p.getId()+"' ");
			
                      
			req.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Erreur :" + e.getMessage());
			
		}
    }
    
}
