/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import Utils.MaConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class Front_FormulaireController implements Initializable {
  
    String query = null;
    Connection connection = MaConnection.getInstance().getConnection() ;
    
    PreparedStatement preparedStatement;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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

        
            
            query = "INSERT INTO `formulaire` (`cin`,`quest1`,`quest2`,`quest3`,`quest4`, `quest5`,`quest6`,`type`) VALUES (?,?,?,?,?,?,?,?)";

       

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
            preparedStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(AjoutFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
  @FXML
   private void goconseil() throws IOException{
     Parent parent = FXMLLoader.load(getClass().getResource("conseil_front.fxml"));
            Scene scene = new Scene(parent);
            Stage formstage = new Stage();
            formstage.setScene(scene);
            formstage.initStyle(StageStyle.UTILITY);
            formstage.show();
   
   }
      @FXML
   private void godermatologue() throws IOException{
     Parent parent = FXMLLoader.load(getClass().getResource("frontderm.fxml"));
            Scene scene = new Scene(parent);
            Stage formstage = new Stage();
            formstage.setScene(scene);
            formstage.initStyle(StageStyle.UTILITY);
            formstage.show();
   
   }
    @FXML
   private void goBack() throws IOException{
        
                 if(!"{}".equals(LoginController.user.getRoles()))
       {
            Parent parent = FXMLLoader.load(getClass().getResource("back_User.fxml"));
            Scene scene = new Scene(parent);
            Stage formstage = new Stage();
            formstage.setScene(scene);
            formstage.initStyle(StageStyle.UTILITY);
            formstage.show();
   
   }
       else {
       Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("vous n'avez pas  l'acces");
                alert.setHeaderText(null);
                
                alert.showAndWait();
       }
   }
}
