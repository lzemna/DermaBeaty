/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.sql.SQLException;

/**
 *
 * @author amine
 */
public interface IConsLike {
    
     public void addLike(String ref, int id) throws SQLException;
     public void deleteLike(String ref,int id) throws SQLException;
    
}
