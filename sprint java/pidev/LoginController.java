/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;


import Entities.User;
import Utils.MaConnection;
import java.awt.event.MouseEvent;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author IMNA
 */
public class LoginController implements Initializable {

    @FXML
    private Label lbl_close;
     @FXML
    private Label lblErrors;
    @FXML
    private TextField txtUsername;
     @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnSignIn;
    @FXML
    private Button btnSignUp;
    @FXML
    private Label forgot;
   
    Connection con = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
        static User user= new User();
    /**
     * Initializes the controller class.
     */
    
  
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if (con == null) {
            lblErrors.setTextFill(Color.TOMATO);
            lblErrors.setText("Server Error : Check");
        } else {
            lblErrors.setTextFill(Color.GREEN);
            lblErrors.setText("Server is up : Good to go");
        }
    }    
    public LoginController() {
         con = MaConnection.getInstance().getConnection();
    }
     private String logIn() {
        String status = "Success";
        String email = txtUsername.getText();
        String password = txtPassword.getText();
        if(email.isEmpty() || password.isEmpty()) {
            setLblError(Color.TOMATO, "Empty credentials");
            status = "Error";
        } else {
            //query
            String sql = "SELECT * FROM user Where email = ? and password = ?";
            try {
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);
                resultSet = preparedStatement.executeQuery();
                if (!resultSet.next()) {
                    setLblError(Color.TOMATO, "Enter Correct Email/Password");
                    status = "Error";
                } else {
                   user.setId(resultSet.getInt(9));
                   user.setRoles(resultSet.getString("roles"));
     //       preparedStatement.setString(3, role);
            
                    setLblError(Color.GREEN, "Login Successful..Redirecting.."+user.getId()+user.getRoles());
                }
                 
                   
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                status = "Exception";
            }
        }
        
        return status;
    }

    private void setLblError(Color color, String text) {
         lblErrors.setTextFill(color);
         lblErrors.setText(text);
         System.out.println(text);
        
    }
     @FXML
    private void getSignUpView() throws IOException {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
            Scene scene = new Scene(parent);
            Stage thstage = new Stage();
            thstage.setScene(scene);
            thstage.initStyle(StageStyle.UTILITY);
            thstage.show();
        } catch (IOException ex) {
            Logger.getLogger(backUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    @FXML
    private void handleButtonAction(javafx.scene.input.MouseEvent event) {
         if (event.getSource()==lbl_close)
        {
            System.exit(0);
        }
//         if (event.getSource()==btnSignIn)
//        {
//            logIn();
//        }
         
          if (event.getSource() == btnSignIn) {
            //login here
            if (logIn().equals("Success")) {
                try {

                    //add you loading or delays - ;-)
                    Node node = (Node) event.getSource();
                    Stage stage1 = (Stage) node.getScene().getWindow();
                    //stage.setMaximized(true);
                    stage1.close();
                               
             FXMLLoader fxmlLoader= new FXMLLoader();
           //Produit p = new Produit("nom", 0, "",1, 88);
           if(user.getRoles().equals("{}")){
               
                fxmlLoader.setLocation(getClass().getResource("PageMain.fxml"));
           
           }
           else{
                fxmlLoader.setLocation(getClass().getResource("Commade.fxml"));
           
               
           }
          
                         AnchorPane singleWindow = fxmlLoader.load();
           //  PageMainController pageMainController = fxmlLoader.getController();
            
           //Label name =  singleController.getNameProd();
          // name.setText(this.produit.getNom());
            
            Stage stage = new Stage();
            Scene scene = new Scene(singleWindow);
              stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
       
        
         
                    /*Scene scene = new Scene(FXMLLoader.load(getClass().getResource("PageMain.fxml")));
                    stage1.setScene(scene);
                    stage1.show();
*/
   
    

                } 
                catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }

            }
        }
    
    
         
    }
    @FXML
     public void getForgot(javafx.scene.input.MouseEvent event){
      try {
            Parent parent = FXMLLoader.load(getClass().getResource("ForgotPass.fxml"));
            Scene scene = new Scene(parent);
            Stage thstage = new Stage();
            thstage.setScene(scene);
            thstage.initStyle(StageStyle.UTILITY);
            thstage.show();
        } catch (IOException ex) {
            Logger.getLogger(backUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
    
}

    

