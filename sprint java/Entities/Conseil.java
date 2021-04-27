/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Comparator;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author amine
 */
public class Conseil implements Comparable <Conseil>  {
    private int reference;
    private String remarques,nom_derma,email;
    private Date date_red,date_limite;

    public Conseil(int reference, String remarques, String nom_derma, String email, Date date_red, Date date_limite) {
       this.reference=reference;
        this.remarques = remarques;
        this.nom_derma = nom_derma;
        this.email = email;
        this.date_red = date_red;
        this.date_limite = date_limite;
    }

    public Conseil() {
        
    }

    public int getReference() {
        return reference;
    }

    public String getRemarques() {
        return remarques;
    }

    public String getNom_derma() {
        return nom_derma;
    }

    public String getEmail() {
        return email;
    }

    public Date getDate_red() {
        return date_red;
    }

    public Date getDate_limite() {
        return date_limite;
    }

    public void setRemarques(String remarques) {
        this.remarques = remarques;
    }

    public void setNom_derma(String nom_derma) {
        this.nom_derma = nom_derma;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDate_red(Date date_red) {
        this.date_red = date_red;
    }

    public void setDate_limite(Date date_limite) {
        this.date_limite = date_limite;
    }

    @Override
    public String toString() {
        return "Conseil{" + "reference=" + reference + ", remarques=" + remarques + ", nom_derma=" + nom_derma + ", email=" + email + ", date_red=" + date_red + ", date_limite=" + date_limite + '}';
    }

    public void setReference(int reference) {
        this.reference = reference;
    }
     public static Comparator <Conseil> ComparatorReference =(Conseil o1, Conseil o2) -> o1.getReference()-o2.getReference();

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Conseil other = (Conseil) obj;
        if (this.reference != other.reference) {
            return false;
        }
        if (!Objects.equals(this.remarques, other.remarques)) {
            return false;
        }
        if (!Objects.equals(this.nom_derma, other.nom_derma)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.date_red, other.date_red)) {
            return false;
        }
        if (!Objects.equals(this.date_limite, other.date_limite)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Conseil o) {
         return date_red.compareTo(o.getDate_red());
    }

   
    
    
}
