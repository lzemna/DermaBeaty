/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author DELL
 */
public class Panier {
    
  private int id;  
  private Produit produit;
  private int userId;
  private int quantity;

    public Panier() {
    }

    public Panier(int id, Produit produit, int userId, int quantity) {
        this.id = id;
        this.produit = produit;
        this.userId = userId;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public Produit getProduit() {
        return produit;
    }

    public int getUserId() {
        return userId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Panier{" + "id=" + id + ", produit=" + produit + ", userId=" + userId + ", quantity=" + quantity + '}';
    }
    
    
    
  
  
  
}
