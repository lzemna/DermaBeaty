/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author IMNA
 */
public class CategorieDerm implements Comparable <CategorieDerm>{
    int ref ; 
    String localisation , nomCat;

    public CategorieDerm() {
    }

    public CategorieDerm(int ref, String localisation, String nomCat) {
        this.ref = ref;
        this.localisation = localisation;
        this.nomCat = nomCat;
    }

    public int getRef() {
        return ref;
    }

    public void setRef(int ref) {
        this.ref = ref;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getNomCat() {
        return nomCat;
    }

    public void setNomCat(String nomCat) {
        this.nomCat = nomCat;
    }

    @Override
    public String toString() {
        return "CategorieDerm{" + "ref=" + ref + ", localisation=" + localisation + ", nomCat=" + nomCat + '}';
    }

    @Override
    public int compareTo(CategorieDerm o) {
        return nomCat.compareTo(o.getNomCat());
    }
    
    
    
}
