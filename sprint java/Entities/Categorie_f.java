/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Comparator;

/**
 *
 * @author amine
 */
public class Categorie_f {
    private int id_cat;
    private String nom, type;

    public Categorie_f(int id_cat, String nom, String type) {
        this.id_cat = id_cat;
        this.nom = nom;
        this.type = type;
    }

    public Categorie_f() {
       
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId_cat() {
        return id_cat;
    }

    public String getNom() {
        return nom;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Categorie_f{" + "id_cat=" + id_cat + ", nom=" + nom + ", type=" + type + '}';
    }

    public void setId_cat(int id_cat) {
        this.id_cat = id_cat;
    }
        public static Comparator <Categorie_f> ComparatorID =(Categorie_f o1, Categorie_f o2) -> o1.getId_cat()- o2.getId_cat();
    
    
}
