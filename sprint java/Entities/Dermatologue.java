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
public class Dermatologue extends User {
    int idDerm   ; 
    int categorie ;
    String diplome,formation,langue,horaire,modereglement,assurancemaladie,image;
    
    public Dermatologue() {
    }

    public Dermatologue(int id, String diplome, String formation, String langue, String horaire, String modereglement, String assurancemaladie, String image , int categorie) {
        this.idDerm = id;
        this.diplome = diplome;
        this.formation = formation;
        this.langue = langue;
        this.horaire = horaire;
        this.modereglement = modereglement;
        this.assurancemaladie = assurancemaladie;
        this.image = image;
        this.categorie=categorie;
        
    }
    

    public int getIdDerm() {
        return idDerm;
    }

    public void setIdDerm(int id) {
        this.idDerm = id;
    }

    public String getDiplome() {
        return diplome;
    }

    public void setDiplome(String diplome) {
        this.diplome = diplome;
    }

    public String getFormation() {
        return formation;
    }

    public void setFormation(String formation) {
        this.formation = formation;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public String getHoraire() {
        return horaire;
    }

    public void setHoraire(String horaire) {
        this.horaire = horaire;
    }

    public String getModereglement() {
        return modereglement;
    }

    public void setModereglement(String modereglement) {
        this.modereglement = modereglement;
    }

    public String getAssurancemaladie() {
        return assurancemaladie;
    }

    public void setAssurancemaladie(String assurancemaladie) {
        this.assurancemaladie = assurancemaladie;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getCategorie() {
        return categorie;
    }

    public void setCategorie(int categorie) {
        this.categorie = categorie;
    }
    

    @Override
    public String toString() {
        return "Dermatologue{" + "id=" + idDerm + ", diplome=" + diplome + ", formation=" + formation + ", langue=" + langue + ", horaire=" + horaire + ", modereglement=" + modereglement + ", assurancemaladie=" + assurancemaladie + ", image=" + image + '}';
    }
    
}
