/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import Entities.Conseil;
import Services.ConsLikeService;
import Utils.MaConnection;
import static java.lang.String.valueOf;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class ConseilController implements Initializable {
     @FXML
    private Label reference;

    @FXML
    private Label nomderma;
    
    @FXML
    private Label date_red;

    @FXML
    private Label date_limite;
      
    @FXML
    private Label desc;
    
       
    @FXML
    private HBox action_buttons;
    
    private Conseil cons ;
    Button btn_like ;
     String query = null;
    Connection connection = MaConnection.getInstance().getConnection() ;
    ResultSet rs = null;
    PreparedStatement preparedStatement;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
              btn_like = new Button();
             action_buttons.getChildren().add(btn_like);
             btn_like.setVisible(false);
             btn_like.setTextFill(Color.WHITE);
              btn_like.setStyle("-fx-background-color: #407dd3; ");
             btn_like.setOnAction(this::Btn_Like);
             this.afficheButton();
    }    
     public void setConseil(Conseil c){
      this.cons = c;      
    }
     public void setData(Conseil c){
      
       reference.setText(String.valueOf(c.getReference()));
       nomderma.setText(c.getNom_derma());
       date_red.setText(String.valueOf(c.getDate_red()));
       date_limite.setText(String.valueOf(c.getDate_limite()));
       desc.setText(c.getRemarques());
    // prodImage.setImage(new Image(getClass().getResourceAsStream(p.getImg())));
      }
     
     @FXML
     public void click(MouseEvent event){
              
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(this.cons.getNom_derma());
                alert.setHeaderText(null);
                alert.setContentText(this.cons.getRemarques());
                alert.showAndWait();
     }

     @FXML
     private void Btn_Like(ActionEvent event) 
    {
        if (event.getSource()==btn_like)
        {
            if (btn_like.getText().equals("Like"))
            {
                try {
                    this.like();
                    this.afficheButton();
                } catch (SQLException ex) {
                    Logger.getLogger(ConseilController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if (btn_like.getText().equals("Unlike"))
            {
                try {
                    this.dislike();
                    this.afficheButton();
                } catch (SQLException ex) {
                    Logger.getLogger(ConseilController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
public void like() throws SQLException{
        ConsLikeService as = new  ConsLikeService();
     String ref =String.valueOf(reference.getText());
    int id = LoginController.user.getId();
    as.addLike(ref,id);
    System.out.print("lena el mochkel");
    }
    public void dislike() throws SQLException{
     ConsLikeService as = new ConsLikeService();
    String ref = reference.getText();
    int id = LoginController.user.getId();
    //String mail = DashboardController.email;
    as.deleteLike(ref, id);
    
    }
 @FXML
public void afficheButton()
    {    
        try {
            String ref = reference.getText();
            //String id = DashboardController.email;
            preparedStatement = connection.prepareStatement("select count(*) from cons_like where reference = '"+ref+"' ");
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            int count = rs.getInt(1);
             if (count==0)
                {
                    btn_like.setText("Like");
                }
                else
                {
                    btn_like.setText("Unlike");
                }
                btn_like.setVisible(true);
             } catch (SQLException ex) {
            Logger.getLogger(ConseilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     
}
