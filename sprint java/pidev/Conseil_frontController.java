/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import Entities.Conseil;
import Services.ServiceConseil;
import Utils.MaConnection;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import static javafx.scene.input.KeyCode.C;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class Conseil_frontController implements Initializable {
    @FXML
    private ScrollPane scrollC;

    @FXML
    private GridPane gridC;
    private List<Conseil> conseil = new ArrayList();
    private ServiceConseil cons = new ServiceConseil();
    
      String query = null;
    Connection connection = MaConnection.getInstance().getConnection() ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                affichage();
    }    
    
   @FXML
    private void affichage()
    {
               int ligne =0;
               int colone = 0;
               conseil.addAll(getConseil());
              for(int i=0;i<conseil.size();i++){
                   try {
                       
                       
                       FXMLLoader fxmlLoader= new FXMLLoader();
                       
                       fxmlLoader.setLocation(getClass().getResource("Conseil.fxml"));//recuperer le fichier fxml
                       AnchorPane    col;
                       
                       col = fxmlLoader.load(); //recuperer le block du produit
                       
                       
                         ConseilController ConseilController = fxmlLoader.getController();//recuperer le controller du ficher fxml
                        ConseilController.setData(conseil.get(i));//faire le block pour chaque produit de la liste
                        ConseilController.setConseil(conseil.get(i));//prodController.clickProd(event);
                       
                       gridC.add(col, 0, ligne++);//ajouter le block dans le grid
                       GridPane.setMargin(col, new Insets(100));
                   } catch (IOException ex) {
                       Logger.getLogger(Conseil_frontController.class.getName()).log(Level.SEVERE, null, ex);
                   }
        }
    }
    
     @FXML
      private void trierTable() throws SQLException {
    
              int ligne =0;
               int colone = 0;
              conseil=cons.getConseil();
               cons.trierConseilDate(conseil);
               for(int i=0;i<conseil.size();i++  ) {
             
              try {
                       
                       
                       FXMLLoader fxmlLoader= new FXMLLoader();
                       
                       fxmlLoader.setLocation(getClass().getResource("Conseil.fxml"));//recuperer le fichier fxml
                       AnchorPane    col;
                       
                       col = fxmlLoader.load(); //recuperer le block du produit
                       
                       
                         ConseilController ConseilController = fxmlLoader.getController();//recuperer le controller du ficher fxml
                        ConseilController.setData(conseil.get(i));//faire le block pour chaque produit de la liste
                        ConseilController.setConseil(conseil.get(i));//prodController.clickProd(event);
                       
                       gridC.add(col, colone, ligne++);//ajouter le block dans le grid
                       GridPane.setMargin(col, new Insets(100));
                   } catch (IOException ex) {
                       Logger.getLogger(Conseil_frontController.class.getName()).log(Level.SEVERE, null, ex);
                   }
             
           
           
            
        }
               
      
    }
    private List<Conseil> getConseil()  {
       
        ArrayList<Conseil> datas = new ArrayList();   
        try {
            datas.addAll(cons.getConseil()) ;
        } catch (SQLException ex) {
            Logger.getLogger(Conseil_frontController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return datas;
    }
    
      @FXML
   private void goFormulaire() throws IOException{
     Parent parent = FXMLLoader.load(getClass().getResource("Back_Form.fxml"));
            Scene scene = new Scene(parent);
            Stage formstage = new Stage();
            formstage.setScene(scene);
            formstage.initStyle(StageStyle.UTILITY);
            formstage.show();
   
   }
    @FXML
   private void goFormulairefront() throws IOException{
     Parent parent = FXMLLoader.load(getClass().getResource("front_Formulaire.fxml"));
            Scene scene = new Scene(parent);
            Stage formstage = new Stage();
            formstage.setScene(scene);
            formstage.initStyle(StageStyle.UTILITY);
            formstage.show();
   
   }
   
    @FXML
   private void imprimer() throws SQLException {
     Document Doc = new Document();
     query = "select * from `conseil`";
      preparedStatement = connection.prepareStatement(query);
       resultSet = preparedStatement.executeQuery();
     
     try {
            PdfWriter.getInstance(Doc,new FileOutputStream("C:\\Users\\amine\\Desktop\\Conseil.pdf"));
            Doc.open();
            
            Image img = Image.getInstance("C:\\Users\\amine\\Documents\\NetBeansProjects\\Users\\src\\Img\\logoj.png");
            img.scaleAbsoluteWidth(300);
            img.scaleAbsoluteHeight(250);
            img.setAlignment(Image.ALIGN_CENTER);
            Doc.add(img);
             Doc.add(new Paragraph(" "));
            Doc.add(new Paragraph("vos conseil :"));
            Doc.add(new Paragraph(" "));
            Doc.add(new Paragraph(" "));
            PdfPTable table = new PdfPTable(6) ;
            table.setWidthPercentage(100);
            PdfPCell cell;
            ////////////////////////HEADER////////////////////////////////
            cell = new PdfPCell(new Phrase ("Reference", FontFactory.getFont("Cambria", 12)) );
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase ("date redaction", FontFactory.getFont("Cambria", 12)) );
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase ("date limite", FontFactory.getFont("Cambria", 12)) );
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase ("nom dermatologue", FontFactory.getFont("Cambria", 12)) );
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase ("email", FontFactory.getFont("Cambria", 12)) );
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase ("remarque", FontFactory.getFont("Cambria", 12)) );
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(cell);
             ////////////////////////HEADER////////////////////////////////
              ////////////////////////BODY////////////////////////////////
            while (resultSet.next()) 
       {
           
            cell = new PdfPCell(new Phrase (resultSet.getString("reference"), FontFactory.getFont("Cambria", 12)) );
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase (resultSet.getString("date_red"), FontFactory.getFont("Cambria", 12)) );
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase (resultSet.getString("date_limite"), FontFactory.getFont("Cambria", 12)) );
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase (resultSet.getString("nom_derma"), FontFactory.getFont("Cambria", 12)) );
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase (resultSet.getString("email"), FontFactory.getFont("Cambria", 12)) );
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase (resultSet.getString("remarques"), FontFactory.getFont("Cambria", 12)) );
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
       
        table.addCell(cell);
       }
               ////////////////////////BODY////////////////////////////////
           
            
            Doc.add(table);
            Doc.close();
            Desktop.getDesktop().open(new File("C:\\Users\\amine\\Desktop\\Conseil.pdf"));
            
        } catch (DocumentException ex) {
            Logger.getLogger(Conseil_frontController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Conseil_frontController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Conseil_frontController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
   }
   
    @FXML
   private void goBack() throws IOException{
        
           // String[]rolee = new String[]{};
     //       preparedStatement.setString(3, role);
//            JSONObject roles = new JSONObject(rolee);
//            JSONObject roles1 = new JSONObject(  LoginController.user.getRoles());
   
          
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
