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
public class Commade {
    int id;
    double prixTot;
    int userId;
    String date;
    String etat;

    public Commade() {
    }

    public Commade(int id, double prixTot, int userId, String date, String etat) {
        this.id = id;
        this.prixTot = prixTot;
        this.userId = userId;
        this.date = date;
        this.etat = etat;
    }

    public int getId() {
        return id;
    }

    public double getPrixTot() {
        return prixTot;
    }

    public int getUserId() {
        return userId;
    }

    public String getDate() {
        return date;
    }

    public String getEtat() {
        return etat;
    }

    public void setPrixTot(double prixTot) {
        this.prixTot = prixTot;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Commade{" + "id=" + id + ", prixTot=" + prixTot + ", userId=" + userId + ", date=" + date + ", etat=" + etat + '}';
    }
    
    
    
    
}
