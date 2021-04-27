/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import Entities.Dermatologue;
import Services.ServiceDerm;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author IMNA
 */
public class FrontDermController implements Initializable {

    @FXML
    private Button cart_id;
    @FXML
    private Button connection_id;
    @FXML
    private ScrollPane scrollId;
    @FXML
    private GridPane gridId;

    /**
     * Initializes the controller class.
     */
    
    List<Dermatologue> derms = new ArrayList();
    private ServiceDerm sp = new ServiceDerm();
    
    private List<Dermatologue> getDermatologue() {
        ArrayList<Dermatologue> datas = new ArrayList();   
    try {
     
        datas.addAll(sp.getDerms()) ;
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
        // Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
    }
    return datas;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      // TODO
      derms.addAll(getDermatologue());
              //Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        int ligne =0;
        int colone = 1;
              
              for(int i=0;i<derms.size();i++){
                  
           
                  try {
                       
            if(colone == 5){
                
                ligne++;
                colone=0;
            }
            
            FXMLLoader fxmlLoader= new FXMLLoader();
           
            fxmlLoader.setLocation(getClass().getResource("Derm.fxml"));//recuperer le fichier fxml
             AnchorPane    col = fxmlLoader.load();//recuperer le block du produit
        
            DermController dermController = fxmlLoader.getController();//recuperer le controller du ficher fxml
            dermController.setData(derms.get(i));//faire le block pour chaque produit de la liste
            //prodController.setProduit(produits.get(i));//prodController.clickProd(event);
                     
                      gridId.add(col, colone++, ligne);//ajouter le block dans le grid
                      GridPane.setMargin(col, new Insets(10));
                  } catch (IOException ex) {
                System.out.println(ex.getMessage());
    // Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    }

    
     @FXML
   private void goFormulaire() throws IOException{
     Parent parent = FXMLLoader.load(getClass().getResource("front_formulaire.fxml"));
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

    @FXML
    private void goProduit(ActionEvent event) throws IOException {
         Parent parent = FXMLLoader.load(getClass().getResource("front_prod.fxml"));
            Scene scene = new Scene(parent);
            Stage prodstage = new Stage();
            prodstage.setScene(scene);
            prodstage.initStyle(StageStyle.UTILITY);
            prodstage.show();
    }
}
