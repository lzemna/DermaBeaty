/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class ListProd {
    int id;
    int idUser;
    String referenceProd;
    double  prix;
    int quantity;    
    public ListProd() {
    }

    public ListProd(int id, int idUser, String referenceProd, double prix, int quantity) {
        this.id = id;
        this.idUser = idUser;
        this.referenceProd = referenceProd;
        this.prix = prix;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    

    public int getId() {
        return id;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getReferenceProd() {
        return referenceProd;
    }

    public double getPrix() {
        return prix;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setReferenceProd(String referenceProd) {
        this.referenceProd = referenceProd;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "ListProd{" + "id=" + id + ", idUser=" + idUser + ", referenceProd=" + referenceProd + ", prix=" + prix + ", quantity=" + quantity + '}';
    }

    

    
    
    
    
    
    
    
    
    
    
    
    
}
