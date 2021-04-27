/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author ahmed
 */
public class ConsLike {
    private int idl ,reference,user;
  
    public ConsLike(){};

    public ConsLike(int idl, int reference, int user) {
        this.idl = idl;
        this.reference = reference;
        this.user = user;
    }

    public int getIdl() {
        return idl;
    }

    public void setIdl(int idl) {
        this.idl = idl;
    }

    public int getReference() {
        return reference;
    }

    public void setReference(int reference) {
        this.reference = reference;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "ConsLike{" + "idl=" + idl + ", reference=" + reference + ", user=" + user + '}';
    }
    

    
    
}
