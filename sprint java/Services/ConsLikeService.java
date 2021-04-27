/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Interfaces.IConsLike;
import Utils.MaConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author amine
 */
public class ConsLikeService implements IConsLike {
Connection cnx;
    public ConsLikeService() {
          cnx = MaConnection.getInstance().getConnection();
    }

    
    
    @Override
    public void addLike(String ref, int id) throws SQLException {
         Statement stm = cnx.createStatement();
        String query = "INSERT INTO `cons_like` "
                + "( `reference`,`user`)"
                + " VALUES ( '" + ref+ "',"
                + " '" + id+  "')";
        stm.executeUpdate(query);
    }

    @Override
    public void deleteLike(String ref, int id) throws SQLException {
      Statement stmt = cnx.createStatement();
			stmt.execute("DELETE FROM `cons_like`  WHERE reference = '" + ref + "' and user = '"+id+"'");
			
                      
    }
    
}
