/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.Dermatologue;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author IMNA
 */
public interface IServiceDerm {
    
    public void addDerm(Dermatologue p) throws SQLException;
    public List<Dermatologue> getDerms() throws SQLException;
    public boolean deleteDerm(int id) throws SQLException;
    public void updateDerm(Dermatologue p) throws SQLException;
}
