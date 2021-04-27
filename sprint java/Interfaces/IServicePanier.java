 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;


import java.sql.SQLException;
import java.util.List;
import Entities.Produit;
import Entities.ListProd;
import Entities.Panier;
import java.util.HashMap;
import java.util.LinkedHashMap;
/**
 *
 * @author DELL
 */
public interface IServicePanier {
    public void addPanier(Panier panier) throws SQLException;

    public HashMap<Produit,Integer> affichePanier(int userId) throws SQLException;
        public void DeletePanier(String  reference,int userId) throws SQLException;
        

// public void updatePanier()
            
}
