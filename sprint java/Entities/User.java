/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;




import java.sql.Array;
import java.util.Objects;
//import javax.json.JsonArray;

public class User {
int id, cin,numero ; 
String nom,prenom,email,adresse,password;
//Array roles ; 
String roles;

    public User() {
    }

//    public User(int id, int cin,int numero, String nom, String prenom, String email, String roles[], String adresse ,String password) {
//        this.id = id;
//        this.cin = cin;
//        this.numero= numero;
//        this.nom = nom;
//        this.prenom = prenom;
//        this.email = email;
//        this.roles = roles;
//        this.adresse = adresse;
//        this.password= password;
//        
//    }

    public String getRoles() {
        return roles ;
    }

    public void setRoles(String Role) {
        this.roles =Role ;
        
    }
    

    public User(int id, int cin, int numero, String nom, String prenom, String email, String adresse, String password, String roles) {
        this.id = id;
        this.cin = cin;
        this.numero = numero;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.adresse = adresse;
        this.password = password;
        this.roles = roles;
    }

   
   

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public String[] getRoles() {
//         this.roles[0] = "ROLE_USER";
//        return roles ;
//    }
//
//    public void setRoles(String roles) {
//        this.roles[0] = "ROLE_USER";
//        this.roles[1]=roles;
//    }

//    public Array getRoles() {
//        
//        return roles;
//    }
//
//    public void setRoles(Array roles) {
//        
//        this.roles = roles;
//    }

   

    public void setId(int id) {
        this.id = id;
    }

   

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", cin=" + cin + ", numero=" + numero + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", roles=" + roles + ", adresse=" + adresse + ", password=" + password + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final User other = (User) obj;
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        return true;
    }

   

}
