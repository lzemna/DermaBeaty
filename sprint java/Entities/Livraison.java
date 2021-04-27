/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author user
 */
public class Livraison {
    
  
     private int id;
     private String destination;
     
     private String date;
    private int id_Liv;

    public Livraison() {
    }

    public Livraison(int id, String destination, String date, int id_Liv) {
        this.id = id;
        this.destination = destination;
        this.date = date;
        this.id_Liv = id_Liv;
    }

    public int getId() {
        return id;
    }

    public String getDestination() {
        return destination;
    }

    public String getDate() {
        return date;
    }

    public int getId_Liv() {
        return id_Liv;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setId_Liv(int id_Liv) {
        this.id_Liv = id_Liv;
    }

    @Override
    public String toString() {
        return "Livraison{" + "id=" + id + ", destination=" + destination + ", date=" + date + ", id_Liv=" + id_Liv + '}';
    }
    
    
}
