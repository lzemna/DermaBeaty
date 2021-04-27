/*///*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import Entities.User;
import Utils.MaConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author IMNA
 */
public class AddUserController implements Initializable {

    @FXML
    private TextField cinFild;
    @FXML
    private TextField emailFild;
   
    @FXML
    private TextField passwordFild;
    @FXML
    private TextField NomFild;
    @FXML
    private TextField prenomFild;
    @FXML
    private TextField adresseFild;
    @FXML
    private TextField numeroFild;
        @FXML
    private Label LblError;
         @FXML
    private Label LblError1;
  
    @FXML
    private Label LblError1111;


    @FXML
    private Label LblError111;

    @FXML
    private Label LblError1112;

    @FXML
    private Label LblError11121;

    @FXML
    private Label LblError111211;

    /**
     * Initializes the controller class.
     */
    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    User user = new User();
    private boolean update;
    int userId;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

      private static Matcher matcher;

    public static boolean isString(String text) {

        if (text.matches("^[a-zA-Z]+$")) {

            return true;
        } 
            return false;

    }

     public static boolean isNull(String text){
         if(text == ""){
             return true; //null
         }

         return false ;//n'est pas vide
     }

          public static boolean isUsername(String text) {

        if (text.matches("^[A-Za-z0-9]+$+") ) {

            return true;
        } 
            return false;
    }

        

      public static boolean adresse(String text) {

        if (text.matches("^[A-Z a-z 0-9]+$")) {
            return true;
        }
            return false;
    }

          public static boolean iscin(String text) {

        if (text.matches("^[0-9]+$")&& text.length()== 8) {

            return true;

        } else {

            return false;

        }

    }

                  public static boolean isTel(String text) {

        if (text.matches("^[0-9]+$")&& text.length()==8) {

            return true;

        } else {

            return false;

        }

    }



     private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
     private static Pattern pattern = Pattern.compile(EMAIL_PATTERN);
     private static final String pwd=  "^[A-Za-z0-9]+$";
     private static Pattern pattern1 = Pattern.compile(pwd);

     public static boolean validmail(final String hex) {

        matcher = pattern.matcher(hex);

        return matcher.matches();

    }

      public static boolean validPasswor(final String hex) {

        matcher = pattern1.matcher(hex);

        return matcher.matches();

    }
    @FXML
    private void save(MouseEvent event) {
        connection = MaConnection.getInstance().getConnection();
        String cin = cinFild.getText();
        String email = emailFild.getText();
        //String roles = rolesFild.getText();
        String password = passwordFild.getText();
        String nom = NomFild.getText();
        String prenom = prenomFild.getText();
        String adresse =adresseFild.getText();
        String numero =numeroFild.getText();
        
//        if (  || roles.isEmpty() || password.isEmpty() || nom.isEmpty() || prenom.isEmpty() || adresse.isEmpty() || numero.isEmpty()  ||(validPasswor(password)==false) || (isUsername(nom)==false) || (isUsername(prenom)==false) || (adresse(adresse)==false) || (isTel(numero)==false) ) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setHeaderText(null);
//            alert.setContentText("Please Fill All DATA");
//            alert.showAndWait();
//        }
            if(cin.isEmpty() || (iscin(cin)==false)) {
            setLblError(Color.TOMATO, "Please Enter valid Cin" , LblError);
            }
            if( (validmail(email)==false) || (email.isEmpty())) {
            setLblError(Color.TOMATO, "Please Enter valid Email" , LblError1);
            }
             if((validPasswor(password)==false) || password.isEmpty()) {
            setLblError(Color.TOMATO, "Please Enter valid Password" , LblError1111);
             }
             if( (isUsername(nom)==false) ||nom.isEmpty()  ) {
            setLblError(Color.TOMATO, "Please Enter valid Name" ,LblError111);
             }
             if( (isUsername(prenom)==false) ||prenom.isEmpty()  ) {
            setLblError(Color.TOMATO, "Please Enter valid LastName" , LblError1112);
             }
              if( (adresse(adresse)==false) ||adresse.isEmpty()  ) {
            setLblError(Color.TOMATO, "Please Enter valid Address" , LblError11121);
              }
             if ((isTel(numero)==false)|| numero.isEmpty()){
             setLblError(Color.TOMATO, "Please Enter valid Numero" , LblError111211);
        } else {
            getQuery();
            insert();
            clean();

        }
        
    }
    
    @FXML
    private void clean() {
        cinFild.setText(null);
        emailFild.setText(null);
        //rolesFild.setText(null);
        passwordFild.setText(null);
        NomFild.setText(null);
        prenomFild.setText(null);
        adresseFild.setText(null);
        numeroFild.setText(null);
        
    }
     private void getQuery() {

        if (update == false) {
            
            query = "INSERT INTO `user` (`cin`,`email`,`roles`,`password`,`nom`, `prenom`,`adresse`,`numero`) VALUES (?,?,?,?,?,?,?,?)";

        }else{
            query = "UPDATE `user` SET "
                    + "`cin`=?,"
                    + "`email`=?,"
                    + "`roles`=?,"
                    + "`password`= ?,"
                    + "`nom`= ?,"
                    + "`prenom`= ?,"
                    + "`adresse`= ?,"
                    + "`numero`= ?  WHERE id = '"+userId+"'";
        }

    }
      private void insert() {

        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, cinFild.getText());
            preparedStatement.setString(2,emailFild.getText() );
            //preparedStatement.setString(3, rolesFild.getText());
            String role = user.getRoles();
            String[]rolee = new String[]{role};
     //       preparedStatement.setString(3, role);
            JSONObject roles = new JSONObject(rolee);
            preparedStatement.setString(3,  roles.toString());
            
            preparedStatement.setString(4, passwordFild.getText());
            preparedStatement.setString(5, NomFild.getText());
            preparedStatement.setString(6, prenomFild.getText());
            preparedStatement.setString(7, passwordFild.getText());
            preparedStatement.setString(8, numeroFild.getText());
            preparedStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(AddUserController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
      void setTextField(int id, int cin , String email , String password ,String nom,String prenom ,String adresse,int numero ) {

        userId = id;
        cinFild.setText(String.valueOf(cin));
        emailFild.setText(email);
        //rolesFild.setText(roles);
        passwordFild.setText(password);
        NomFild.setText(nom);
        prenomFild.setText(prenom);
        adresseFild.setText(adresse);
        numeroFild.setText(String.valueOf(numero));
        

    }

    void setUpdate(boolean b) {
        this.update = b;

    }
     
    private void setLblError(Color color, String text ,Label label ) {
         
         label.setTextFill(color);
         label.setText(text);
         System.out.println(text);
        
    }
}


