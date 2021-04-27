/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;


/**
 *
 * @author Demni Anis
 */

 
public class Categorie_centre {
   
    int id;
    
  private String type, specialite;

    public Categorie_centre() {
    }

  
    public Categorie_centre(int id,String type, String specialite  ) {
        this.id = id;
        this.type = type;
        this.specialite =specialite;
    }

    
     public Categorie_centre(String type, String specialite  ) {
        
        this.type = type;
        this.specialite =specialite;
    }

     
     
     
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    

    @Override
    public String toString() {
        return "Categorie_centre{" + "id=" + id + ", type=" + type + " specialite=" + specialite + '}';
    }
  
}
