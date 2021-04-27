/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import Entities.Produit;
import Services.ServiceCommande;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.PrinterJob;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class FactureController implements Initializable {

    @FXML
    private Label totLabel;
    @FXML
    private Label dateLabel;
    @FXML
    private Label nbreCommande;
    @FXML
    private VBox tabDetail;
    @FXML
    private Label prixTotalLabel;
    private Stage stage;
    @FXML
    private AnchorPane myPane;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            ServiceCommande sc = new ServiceCommande();
            try {
                HashMap<Produit,Integer> listCommande =  sc.getDetails(LoginController.user.getId());
          float total;
                total = listCommande.entrySet().stream().map(i->i.getKey().getPrix_p() * i.getValue()).reduce((a,b)->a+b).orElse((float)0.00);
                    this.prixTotalLabel.setText(total+"");
                    this.totLabel.setText(total+"");
                           DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd");
           LocalDate localDate = LocalDate.now();
 // System.out.println(dtf.format(localDate));
               String date =  dtf.format(localDate);
   this.dateLabel.setText(date);
                
  
                listCommande.forEach((p,q)->{
        
                             FXMLLoader fxmlLoader= new FXMLLoader();
           
            fxmlLoader.setLocation(getClass().getResource("datail.fxml"));//recuperer le fichier fxml

                //    prod;
                    try {
                        AnchorPane        commande = fxmlLoader.load();
                 
                            DatailController detailController = fxmlLoader.getController();

//System.out.println("produit " + p + "quantite" +q);
               // ligneCommandeController.setCommande(c.get(i));
                detailController.setData(p.getnom(),q,p.getPrix_p()*q);//faire le block pour chaque produit de la liste
          this.tabDetail.getChildren().add(commande);
                    } catch (IOException ex) {
                         System.out.println(ex.getMessage());   
//  Logger.getLogger(CommadeController.class.getName()).log(Level.SEVERE, null, ex);
                    }
           
                 
            
         
         

                
                });
                
                                
                // TODO
            } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
// Logger.getLogger(CommadeController.class.getName()).log(Level.SEVERE, null, ex);
            }
  
    }
        public void setStage(Stage stage){
            this.stage = stage;



        }    

    @FXML
    private void print(MouseEvent event) {
             PrinterJob job = PrinterJob.createPrinterJob();
   if (job != null) {
       PageLayout pageLayout = job.getPrinter().createPageLayout(Paper.A5, PageOrientation.LANDSCAPE, 0, 0, 0, 0);

  boolean success = job.printPage(pageLayout, this.myPane);
  if (success) {
      System.out.println("done");
      job.endJob();
  }
    
  }
        
        
        
    
    
    }

    @FXML
    private void createPDF(MouseEvent event) {
       FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF File", "*.pdf"));
        fc.setTitle("java to pdf");
        fc.setInitialFileName("facture.pdf");
        File file = fc.showSaveDialog(this.stage);

        
        
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(this.myPane.snapshot(null,null), null),"png",new File("image.png"));
        
        
        
        } catch (IOException ex) {
            //Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(file!=null){
         
            String str = file.getAbsolutePath();
            try {
  //              Docume
                FileOutputStream fos = new FileOutputStream(str);
                try {
                  //  PDF pdf = new PDF(fos);
                   
                   
       
        //  img.setImage(image);
        
        //document.add(new Paragraph("Sample 1: This is simple image demo."));
        com.itextpdf.text.Image image =com.itextpdf.text.Image.getInstance("image.png");
//image.scalePercent(1);
  Document document = new Document(new com.itextpdf.text.Rectangle(image.getScaledWidth(), image.getScaledHeight()));
  PdfWriter.getInstance(document, fos);
        document.open();
         
  document.newPage();
    image.setAbsolutePosition(0, 0);
  document.add(image);
        document.close();
        System.out.println("Done");  
        fos.flush();
                
                } catch (Exception ex) {
                    //Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
                    
            
            } catch (FileNotFoundException ex) {
  //              Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
        else{
            
            System.out.println("enregistrement annule");
            
        }
   
    
    
    
    }
    
}
