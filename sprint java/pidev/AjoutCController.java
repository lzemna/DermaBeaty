/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import Utils.MaConnection;
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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class AjoutCController implements Initializable {

     String query = null;
    Connection connection = MaConnection.getInstance().getConnection() ;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    //ServiceFormulaire cnxf = new ServiceFormulaire();
    @FXML
    private TextField Reference;
    @FXML
    private TextField nom;
    @FXML
    private TextField type;
    

    
    private boolean update;
    int ref;
   
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     
    @FXML
    private void save(MouseEvent event) {

        
        String Ref =   Reference.getText();
        String Nom = nom.getText();
        String Type = type.getText();
        
        if (  Ref.isEmpty() || Nom.isEmpty() || Type.isEmpty()  ) {
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
        Reference.setText(null);
        nom.setText(null);
        type.setText(null);
        
        }

    private void getQuery() {

         if (update == false) {
            
            query = "INSERT INTO `form_categ` "
                + "(`id_cat`, `nom`, `type`)"
                + " VALUES (? ,? ,?)";

        }else{
            query =  "UPDATE `form_categ` SET  id_cat = ? , nom= ? ,type = ? where id_cat ='"+ref+"'    ";
        }

    }

    private void insert() {

        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, String.valueOf( Reference.getText()));
            preparedStatement.setString(2, nom.getText());
            preparedStatement.setString(3, type.getText());
            preparedStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(AjoutCController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void setTextField( int Ref, String n, String t) {

        ref = Ref;
        
        nom.setText(n);
        type.setText(t);
       

    }

    void setUpdate(boolean b) {
        this.update = b;

    }
    
}
