/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import Entities.Categorie_f;
import Entities.Formulaire;
import Services.ServiceFormulaire;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import Utils.MaConnection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.ChoiceBox;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class AjoutFormController implements Initializable {
    String query = null;
    Connection connection = MaConnection.getInstance().getConnection() ;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    //ServiceFormulaire cnxf = new ServiceFormulaire();
    @FXML
    private TextField cin;
    @FXML
    private ChoiceBox <String> quest1;
    @FXML
    private ChoiceBox <String> quest2;
    @FXML
    private ChoiceBox <String> quest3;
    @FXML
    private ChoiceBox <String> quest4;
    @FXML
    private ChoiceBox <String> quest5;
    @FXML
    private ChoiceBox <String> quest6;
    
    @FXML
    private TextField type;
    
    @FXML
    private ChoiceBox <Integer> FormCateg ;
     
   List < Categorie_f > categ= new ArrayList();
   
    Connection cnx;
    //Formulaire form = null;
    private boolean update;
    int ref;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      choice();
    }    
     public void choice() {
      try {  
        
               query = "select * from `form_categ`";
               preparedStatement = connection.prepareStatement(query);
               resultSet = preparedStatement.executeQuery();
      
               while (resultSet.next()) {
             Categorie_f c = new Categorie_f();
            c.setId_cat(resultSet.getInt("id_cat"));
            c.setNom(resultSet.getString("nom"));
            c.setType(resultSet.getString("type"));
           
            FormCateg.getItems().addAll(c.getId_cat()); 
         }
         } catch (SQLException ex) {
            Logger.getLogger(AjoutFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        quest1.getItems().addAll("oui","non");
         quest2.getItems().addAll("oui","non");
          quest3.getItems().addAll("poussiere","solaire","animale");
           quest4.getItems().addAll("oui","non");
            quest5.getItems().addAll("oui","non");
             quest6.getItems().addAll("oui","non");
     }   
    @FXML
    private void save(MouseEvent event) {

        
        String Cin =   cin.getText();
        String Quest1 = quest1.getValue();
        String Quest2 = quest2.getValue();
        String Quest3 = quest3.getValue();
        String Quest4 = quest4.getValue();
        String Quest5 = quest5.getValue();
        String Quest6 = quest6.getValue();
        String Type = type.getText();
        Integer Formcateg = FormCateg.getValue();
        if ( Cin.isEmpty() || Quest1.isEmpty() || Quest2.isEmpty() || Quest3.isEmpty() || Quest4.isEmpty() || Quest5.isEmpty() || Quest6.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();

        } else {
            getQuery();
            insert();
            clean();

        }

    }

    @FXML
    private void clean() {
        cin.setText(null);
        quest1.setValue(null);
        quest2.setValue(null);
        quest3.setValue(null);
        quest4.setValue(null);
        quest5.setValue(null);
        quest6.setValue(null);
        type.setText(null);
    }

    private void getQuery() {

         if (update == false) {
            
            query = "INSERT INTO `formulaire` (`cin`,`quest1`,`quest2`,`quest3`,`quest4`, `quest5`,`quest6`,`type`,`FormCateg`) VALUES (?,?,?,?,?,?,?,?,?)";

        }else{
            query = query = "UPDATE `formulaire` SET  cin = ? ,quest1 = ? ,quest2 = ? ,quest3 = ? ,quest4 = ? , quest5 = ? ,quest6 = ? ,type = ? ,FormCateg=? where ref ='"+ref+"'    ";
        }

    }

    private void insert() {

        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, String.valueOf( cin.getText()));
            preparedStatement.setString(2, quest1.getValue());
            preparedStatement.setString(3, quest2.getValue());
            preparedStatement.setString(4, quest3.getValue());
            preparedStatement.setString(5, quest4.getValue());
            preparedStatement.setString(6, quest5.getValue());
            preparedStatement.setString(7, quest6.getValue());
            preparedStatement.setString(8, type.getText());
            preparedStatement.setString(9, String.valueOf( FormCateg.getValue()));
            preparedStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(AjoutFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void setTextField(int id, int c, String q1, String q2, String q3, String q4, String q5, String q6, String tp) {

        ref = id;
        cin.setText(String.valueOf(c));
        quest1.setValue(q1);
        quest2.setValue(q2);
        quest3.setValue(q3);
        quest4.setValue(q4);
        quest5.setValue(q5);
        quest6.setValue(q6);
        type.setText(tp);

    }

    void setUpdate(boolean b) {
        this.update = b;

    }

   

}
