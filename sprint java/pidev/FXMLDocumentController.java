/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import Entities.Livraison;
import java.awt.HeadlessException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import services.ServiceLivraison;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private VBox tabLivraison;
     @FXML
    private TextField EmailField;
      @FXML
    private Button button;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       refersh();
    } 
    private void refersh(){
             ServiceLivraison sl = new ServiceLivraison();
            try {
                HashMap<String,List<Livraison>> listCommande =  sl.getLivraisonsParLiv();
           
                listCommande.forEach((u,c)->{
         for(int i=0;i<c.size();i++){
                             FXMLLoader fxmlLoader= new FXMLLoader();
           
            fxmlLoader.setLocation(getClass().getResource("livraisonLine.fxml"));//recuperer le fichier fxml

                //    prod;
                    try {
                        AnchorPane        commande = fxmlLoader.load();
                 
                     LivraisonLineController livraisonLineController = fxmlLoader.getController();//recuperer le controller du ficher fxml
                //System.out.println("produit " + p + "quantite" +q);
               // ligneCommandeController.setCommande(c.get(i));
                livraisonLineController.setDataLivraison(c.get(i),u,this.tabLivraison);//faire le block pour chaque produit de la liste
           this.tabLivraison.getChildren().add(commande);
                    } catch (IOException ex) {
                         System.out.println(ex.getMessage());   
//  Logger.getLogger(CommadeController.class.getName()).log(Level.SEVERE, null, ex);
                    }
           
                 
            
         
         }

                
                });
                
                                
                // TODO
            } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
// Logger.getLogger(CommadeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
    }
      @FXML
    private void send(ActionEvent event) throws SQLException, IOException, MessagingException {
        try {
                String host = "smtp.gmail.com";
                String user = "livreurlivraison12@gmail.com";
                String pass = "chdoulapatrunjo1";
                String to =EmailField.getText() ;
                String from = "livreurlivraison12@gmail.com";
                String subject = "confirmation de livraison ";
                String messageText = "your delivery is confirmed ";
                boolean sessionDebug = false;

                Properties props = System.getProperties();

                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.host", host);
                props.put("mail.smtp.port", "587");
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.required", "true");

                java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
                Session mailSession = Session.getDefaultInstance(props, null);
                mailSession.setDebug(sessionDebug);
                Message msg = new MimeMessage(mailSession);
                msg.setFrom(new InternetAddress(from));
                InternetAddress[] address = {new InternetAddress(to)};
                msg.setRecipients(Message.RecipientType.TO, address);
                msg.setSubject(subject);
                msg.setSentDate(new java.util.Date());
                msg.setText(messageText);

                Transport transport = mailSession.getTransport("smtp");
                transport.connect(host, user, pass);

                transport.sendMessage(msg, msg.getAllRecipients());
                System.out.println("code was sent  successfully");
                
                   
                       JOptionPane.showMessageDialog(null," tebaath saaahbyy  ");
                              Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("ResetPassword.fxml")));
        stage.setScene(scene);
        stage.show();

                //    transport.close();
              

            } catch (HeadlessException | IOException ex) {
                System.out.println(ex);
            }
    }

    @FXML
    private void goLivreurs(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("Livreur.fxml"));
            Scene scene = new Scene(parent);
            Stage formstage = new Stage();
            formstage.setScene(scene);
            formstage.initStyle(StageStyle.UTILITY);
            formstage.show();
        } catch (IOException ex) {
            Logger.getLogger(CommadeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        
        
        
        
        
    }

    @FXML
    private void vewStats(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("statLivreur.fxml"));
            Scene scene = new Scene(parent);
            Stage formstage = new Stage();
            formstage.setScene(scene);
            formstage.initStyle(StageStyle.UTILITY);
            formstage.show();
        } catch (IOException ex) {
            Logger.getLogger(CommadeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    
    
    }
    
}
