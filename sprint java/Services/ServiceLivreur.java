/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entities.Livreur;
import Utils.MaConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aissa
 */
public class ServiceLivreur implements IService<Livreur> {

    Connection cnx = MaConnection.getInstance().getConnection();

    @Override
    public void ajouter(Livreur l) {
        try {
            String requete = "INSERT INTO livreur (nom,prenom) VALUES ('" + l.getNom() + "','" + l.getPrenom() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Livreur ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Livreur l) {
        try {
            String requete = "DELETE FROM livreur WHERE id=" + l.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("livreur supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Livreur l) {
        try {
            String requete = "UPDATE livreur SET nom='" + l.getNom() + "',prenom='" + l.getPrenom() + "' WHERE id=" + l.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Livreur modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<Livreur> afficher() {
        List<Livreur> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM livreur";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                list.add(new Livreur(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
}
