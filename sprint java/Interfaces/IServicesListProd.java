/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import Entities.Commade;
import Entities.ListProd;
import Entities.Produit;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface IServicesListProd {
     public void addListProd(ListProd p) throws SQLException;

    public List<ListProd> getListProduit() throws SQLException;
    public void deleteListProduit(int id) throws SQLException;
    public void updateListProduit(ListProd lp) throws SQLException;
    public int getContiteListProd(int userId) throws SQLException;
    public Double getPrixTotal(int userId) throws SQLException;
    public LinkedHashMap<Integer,List<ListProd>> getListByUser() throws SQLException;
    public LinkedHashMap<Integer,List<ListProd>> getListOrderPrixTotal() throws SQLException;
    public String bestSeller() throws SQLException;


}
