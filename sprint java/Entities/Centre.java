/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;





/**
 *
 * @author DEMNI Anis
 */


public class Centre {
   
     
 
  private String nom,email,horaire,description,localisation;
  
  private int id,telephone ;
  
    public Centre() {
    }

    public Centre(int id , String nom, int telephone, String email, String horaire, String description, String localisation) {
        this.id=id;
        this.nom=nom;
        this.telephone=telephone;
        this.email=email;
        this.horaire=horaire;
        this.description=description;
        this.localisation=localisation;
       
      
    }

    public Centre(String nom,  int telephone, String email, String horaire, String description,String localisation) {
        this.nom = nom;
        this.email = email;
        this.horaire = horaire;
        this.description = description;
        this.localisation = localisation;
        this.telephone = telephone;
    }

    


    
  

    


   

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHoraire() {
        return horaire;
    }

    public void setHoraire(String horaire) {
        this.horaire = horaire;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }


    
    @Override
    public String toString() {
        return "Centre{" + "nom=" + nom + ", telephone=" + telephone + ", email=" + email + ", horaire=" + horaire + ", description=" + description + ", localisation=" + localisation+'}';
    }

    
}
