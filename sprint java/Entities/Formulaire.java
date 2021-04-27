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
public class Formulaire {
    private  int ref,cin,FormCateg;
    private String quest1,quest2,quest3,quest4,quest5,quest6,type; 

  

    public Formulaire( int cin, String quest1, String quest2, String quest3, String quest4, String quest5, String quest6, String type,int FormCateg) {
        
        this.cin = cin;
        this.quest1 = quest1;
        this.quest2 = quest2;
        this.quest3 = quest3;
        this.quest4 = quest4;
        this.quest5 = quest5;
        this.quest6 = quest6;
        this.type = type;
        this.FormCateg = FormCateg;

    }

    public int getFormCateg() {
        return FormCateg;
    }

    public void setFormCateg(int FormCateg) {
        this.FormCateg = FormCateg;
    }

    public Formulaire() {
        
    }

    public void setRef(int ref) {
        this.ref = ref;
    }

    public int getRef() {
        return ref;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public String getQuest1() {
        return quest1;
    }

    public void setQuest1(String quest1) {
        this.quest1 = quest1;
    }

    public String getQuest2() {
        return quest2;
    }

    public void setQuest2(String quest2) {
        this.quest2 = quest2;
    }

    public String getQuest3() {
        return quest3;
    }

    public void setQuest3(String quest3) {
        this.quest3 = quest3;
    }

    public String getQuest4() {
        return quest4;
    }

    public void setQuest4(String quest4) {
        this.quest4 = quest4;
    }

    public String getQuest5() {
        return quest5;
    }

    public void setQuest5(String quest5) {
        this.quest5 = quest5;
    }

    public String getQuest6() {
        return quest6;
    }

    public void setQuest6(String quest6) {
        this.quest6 = quest6;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Formulaire{" + "ref=" + ref + ", cin=" + cin + ", FormCateg=" + FormCateg + ", quest1=" + quest1 + ", quest2=" + quest2 + ", quest3=" + quest3 + ", quest4=" + quest4 + ", quest5=" + quest5 + ", quest6=" + quest6 + ", type=" + type + '}';
    }

  
         public static Comparator <Formulaire> ComparatorREF =(Formulaire o1, Formulaire o2) -> o1.getRef()- o2.getRef();
    
}
