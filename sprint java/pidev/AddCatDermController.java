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
 * @author IMNA
 */
public class AddCatDermController implements Initializable {

    @FXML
    private TextField refFild;
    @FXML
    private TextField localFild;
    @FXML
    private TextField nomFild;

    /**
     * Initializes the controller class.
     */
     String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    //User user = null;
    private boolean update;
    int CatRef;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void save(MouseEvent event) throws SQLException {
         connection = MaConnection.getInstance().getConnection();
        String ref = refFild.getText();
        String local = localFild.getText();
        String nom = nomFild.getText();
         if (ref.isEmpty() || local.isEmpty() || nom.isEmpty()){
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
         refFild.setText(null);
        localFild.setText(null);
        nomFild.setText(null);
    }

    private void getQuery() {
        if (update == false) {
            
            query = "INSERT INTO `categorie_derm` (`ref`, `localisation`, `nom_cat`) VALUES (NULL, ?, ?)";

        }else{
            query = "UPDATE `categorie_derm` SET "
//                    + "`ref`=?,"
                    + "`localisation`=?,"
                    + "`nom_cat`=?  WHERE ref = '"+CatRef+"'";
        }

    }

    private void insert()  {
        try {
            preparedStatement = connection.prepareStatement(query);
            //preparedStatement.setString(1, refFild.getText());
            preparedStatement.setString(1,localFild.getText() );
            preparedStatement.setString(2, nomFild.getText());
            preparedStatement.execute();
            } catch (SQLException ex) {
            Logger.getLogger(AddCatDermController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
       void setTextField(int ref ,String loc , String nomcat  ) {

       CatRef = ref;
       // refFild.setText(String.valueOf(ref));
        localFild.setText(loc);
        nomFild.setText(nomcat);
        
        

    }
     void setUpdate(boolean b) {
        this.update = b;

    }
}
