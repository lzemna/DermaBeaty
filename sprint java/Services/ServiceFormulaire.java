/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Categorie_f;
import static Entities.Categorie_f.ComparatorID;
import Entities.Formulaire;
import static Entities.Formulaire.ComparatorREF;
import Interfaces.IServiceFormulaire;
import Utils.MaConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author amine
 */
public class ServiceFormulaire implements IServiceFormulaire {
       Connection cnx;

   
    public ServiceFormulaire() {
          cnx = MaConnection.getInstance().getConnection();
    }
    
    @Override
    public void addFormulaire(Formulaire f) throws SQLException {
         Statement stm = cnx.createStatement();
        String query = "INSERT INTO `formulaire` "
                + "(`ref`, `cin`, `quest1`,`quest2`,`quest3`,`quest4`,`quest5`,`quest6`,`type`,`FormCateg`)"
                + " VALUES (NULL, '" + f.getCin() + "',"
                + " '" + f.getQuest1() + "',"+" '"+ f.getQuest2()+"',"+" '"+ f.getQuest3()+"',"+" '"+ f.getQuest4()+"',"+" '"+ f.getQuest5()+"',"+" '"+ f.getQuest6()+"',"+" '"+ f.getType()+","+" '"+ f.getFormCateg()+" ')";
        stm.executeUpdate(query);
    }

    @Override
    public List<Formulaire> getFormulaire() throws SQLException {
     Statement stm = cnx.createStatement();
        String query = "select * from `formulaire`";
        ResultSet rst = stm.executeQuery(query);
        List<Formulaire> formulaire = new ArrayList<>();
        while (rst.next()) {
            Formulaire f = new Formulaire();
            f.setRef(rst.getInt("ref"));
            f.setCin(rst.getInt("cin"));
            f.setQuest1(rst.getString("quest1"));
             f.setQuest2(rst.getString("quest2"));
              f.setQuest3(rst.getString("quest3"));
             f.setQuest4(rst.getString("quest4")); 
             f.setQuest5(rst.getString("quest5"));
              f.setQuest1(rst.getString("quest6"));
              f.setType(rst.getString("Type"));
              f.setFormCateg(rst.getInt("FormCateg"));
            formulaire.add(f);
        }
     return formulaire;
    }

    @Override
    public Formulaire getById(int id) throws SQLException {
        Statement stm = cnx.createStatement();
        String query = "select * from `formulaire` where ref = '"+id+"' ";
        ResultSet rst = stm.executeQuery(query);
         Formulaire f = new Formulaire();        
        while (rst.next()) {
           
            f.setRef(rst.getInt("ref"));
            f.setCin(rst.getInt("cin"));
            f.setQuest1(rst.getString("quest1"));
             f.setQuest2(rst.getString("quest2"));
              f.setQuest3(rst.getString("quest3"));
             f.setQuest4(rst.getString("quest4")); 
             f.setQuest5(rst.getString("quest5"));
              f.setQuest6(rst.getString("quest6"));
              f.setType(rst.getString("Type"));
               f.setFormCateg(rst.getInt("FormCateg"));
        }
     return f;
    }

    @Override
    public void deleteFormulaire(Formulaire p) throws SQLException {
       
    }

    @Override
    public void deleteFormulaire(int id) throws SQLException {
        Statement stm = cnx.createStatement();
        String query = "Delete  from `formulaire` where ref = '"+id+"' ";
         stm.executeUpdate(query);
    }

    @Override
    public void updateFormulaire(Formulaire f) throws SQLException {
         Statement stm = cnx.createStatement();
        String query = "UPDATE `formulaire` SET  cin ='"+f.getCin()+"',quest1 = '"+f.getQuest1()+"',quest2 = '"+f.getQuest2()+"',quest3 = '"+f.getQuest3()+"',quest4 = '"+f.getQuest4()+"', quest5 = '"+f.getQuest5()+"',quest6 = '"+f.getQuest6()+"',type = '"+f.getType()+"',FormCateg = '"+f.getFormCateg()+"' where ref ='"+f.getRef()+"'    ";
        stm.executeUpdate(query);
    }

    @Override
    public boolean getFormulaire(Formulaire p) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addCategorie(Categorie_f cf) throws SQLException {
           Statement stm = cnx.createStatement();
        String query = "INSERT INTO `form_categ` "
                + "(`id_cat`, `nom`, `type`)"
                + " VALUES ('" + cf.getId_cat() + "',"
                + " '" + cf.getNom() + "',"+" '"+ cf.getType()+"')";
        stm.executeUpdate(query);
    }

    @Override
    public List<Categorie_f> getCategorie() throws SQLException {
      Statement stm = cnx.createStatement();
        String query = "select * from `form_categ`";
        ResultSet rst = stm.executeQuery(query);
        List<Categorie_f> categ = new ArrayList<>();
        while (rst.next()) {
            Categorie_f cf = new Categorie_f();
            cf.setId_cat(rst.getInt("id_cat"));
            cf.setNom(rst.getString("nom"));
            cf.setType(rst.getString("type"));
           
            categ.add(cf);
        }
     return categ;
    }

    @Override
    public void deleteCategorie(int id) throws SQLException {
          Statement stm = cnx.createStatement();
        String query = "Delete  from `form_categ` where id_cat = '"+id+"' ";
         stm.executeUpdate(query);
    }

    @Override
    public void updateCategorie(Categorie_f cf) throws SQLException {
        Statement stm = cnx.createStatement();
        String query = "UPDATE `form_categ` SET  id_cat ='"+cf.getId_cat()+"',nom = '"+cf.getNom()+"',type = '"+cf.getType()+"' where id_cat ='"+cf.getId_cat()+"'    ";
        stm.executeUpdate(query);
    }

    @Override
    public void trierFormulaireParId(List Form) {
           Form.sort(ComparatorREF);
    }

    @Override
    public void trierFormulaireParNom() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
    @Override
    public void trierCategorieParId(List Form) {
        Form.sort(ComparatorID);
    }

    @Override
    public void trierCategorieParNom() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
