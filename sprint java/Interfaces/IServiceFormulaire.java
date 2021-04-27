/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.Categorie_f;
import Entities.Formulaire;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author amine
 */
public interface IServiceFormulaire {
    public void addFormulaire(Formulaire p) throws SQLException;

    public List<Formulaire> getFormulaire() throws SQLException;
            
    public void addCategorie(Categorie_f p) throws SQLException;

    public List<Categorie_f> getCategorie() throws SQLException;

    public Formulaire getById(int id) throws SQLException;

    public void deleteFormulaire(Formulaire p) throws SQLException;

    public void deleteFormulaire(int id) throws SQLException;
    public void trierFormulaireParId(List Form);
    public void trierFormulaireParNom();
    public void updateFormulaire(Formulaire p) throws SQLException;
     public void updateCategorie(Categorie_f cf) throws SQLException;
    public boolean getFormulaire(Formulaire p) throws SQLException;
     public void deleteCategorie(int id) throws SQLException;

    
    public void trierCategorieParId(List Form);
    public void trierCategorieParNom();
}

