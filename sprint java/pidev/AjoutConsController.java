/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import Utils.MaConnection;
import java.net.URL;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class AjoutConsController implements Initializable {

    @FXML
    private TextField Reference;
    @FXML
    private TextField Remarques;
    @FXML
    private DatePicker date_red;
    @FXML
    private DatePicker date_limite;
    @FXML
    private TextField nom_derma;
    @FXML
    private TextField email;
      String query = null;
    Connection connection = MaConnection.getInstance().getConnection() ;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    
    
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
        String rem = Remarques.getText();
        String dater = String.valueOf(date_red.getValue());
        String datel = String.valueOf(date_limite.getValue());
         String Nom =   nom_derma.getText();
        String Email = email.getText();
        
        if (  Ref.isEmpty() || rem.isEmpty() || dater.isEmpty() || datel.isEmpty()|| Nom.isEmpty() || Email.isEmpty() ) {
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
        Remarques.setText(null);
        date_red.setValue(null);
        date_limite.setValue(null);
        nom_derma.setText(null);
        email.setText(null);
    }

    private void getQuery() {

         if (update == false) {
            
            query ="INSERT INTO `conseil` "
                + "(`reference`,`remarques`,`date_red`,`date_limite`,`nom_derma`,`email`)"
                + " VALUES (?,?,?,?,?,?)";

        }else{
            query = "UPDATE `conseil` SET  reference = ? ,remarques= ? ,date_red = ? ,date_limite = ? ,nom_derma = ? ,email = ? where reference ='"+ref+"'    ";
        }

    }

    private void insert() {

        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, String.valueOf( Reference.getText()));
            preparedStatement.setString(2, Remarques.getText());
            preparedStatement.setString(3, String.valueOf( date_red.getValue()));
             preparedStatement.setString(4, String.valueOf( date_limite.getValue()));
            preparedStatement.setString(5, nom_derma.getText() );
            preparedStatement.setString(6, email.getText() );
            preparedStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(AjoutCController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void setTextField(int Ref, String R, String N, String E) {

        ref = Ref;
        Reference.setText(String.valueOf(Ref));
        nom_derma.setText(N);
        email.setText(E);
       

    }

    void setUpdate(boolean b) {
        this.update = b;

    }
    
}
