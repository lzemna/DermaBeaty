/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import Entities.CategorieDerm;
import Utils.MaConnection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author IMNA
 */
public class AddDermController implements Initializable {

    
    @FXML
    private TextField DipFild;

    @FXML
    private TextField FormFild;

    @FXML
    private TextField langFild;

    @FXML
    private TextField HorFild;

    @FXML
    private TextField RegFild;

    @FXML
    private TextField AssFild;

    @FXML
    private TextField ImgFild;
    @FXML
    private ChoiceBox <Integer> Categorie ;

    
        
    @FXML
    private ImageView Imagesh;
    private Image image;
    private FileInputStream fils ; 

     String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    
  
    
    //User user = null;
    private boolean update;
    int DermId;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        choice();
    }    
    
    public void choice() {
      try {  
        
               query = "select * from `categorie_derm`";
               connection = MaConnection.getInstance().getConnection();
               preparedStatement = connection.prepareStatement(query);
               resultSet = preparedStatement.executeQuery();
      
               while (resultSet.next()) {
                   CategorieDerm c = new CategorieDerm();
            c.setRef(resultSet.getInt("ref"));
            
           
    
    Categorie.getItems().addAll(c.getRef()); 
         }
         } catch (SQLException ex) {
            Logger.getLogger(AddDermController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     }

    @FXML
    private void save(MouseEvent event) {
          connection = MaConnection.getInstance().getConnection();
        String dip = DipFild.getText();
        String form = FormFild.getText();
        String lan = langFild.getText();
        String password = HorFild.getText();
        String nom = RegFild.getText();
        String prenom = AssFild.getText();
        String adresse =ImgFild.getText();
        Integer categorie1=Categorie.getValue();
        
        if (dip.isEmpty() || form.isEmpty() || lan.isEmpty() || password.isEmpty() || nom.isEmpty() || prenom.isEmpty() || adresse.isEmpty()   ) {
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
     DipFild.setText(null);
        FormFild.setText(null);
        langFild.setText(null);
        HorFild.setText(null);
        RegFild.setText(null);
        AssFild.setText(null);
        ImgFild.setText(null);
        Categorie.setValue(null);
        //numeroFild.setText(null);
    }

    private void getQuery() {
       if (update == false) {
            
            query = "INSERT INTO `dermatologue`(`categorie`,`diplome`,`formation`,`langue`,`horaire`,`modereglement`, `assurancemaladie`,`image` ) VALUES (?,?,?,?,?,?,?,?)";

        }else{
            query = "UPDATE `dermatologue` SET  `categorie`=?,`diplome` =?,`formation`=?,`langue`=?,`horaire`=?,`modereglement`=?, `assurancemaladie`=?, `image`=? WHERE `id` = '"+DermId+"'";
        }
    }

    private void insert() {
       try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, String.valueOf( Categorie.getValue()));
            preparedStatement.setString(2, DipFild.getText());
            preparedStatement.setString(3,FormFild.getText() );
            preparedStatement.setString(4, langFild.getText());
            preparedStatement.setString(5, HorFild.getText());
            preparedStatement.setString(6, RegFild.getText());
            preparedStatement.setString(7, AssFild.getText());
            preparedStatement.setString(8, ImgFild.getText());
            
            preparedStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(AddUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     void setTextField(int id ,String dip , String form , String lang ,String hor,String reg ,String ass,String img ) {

        DermId = id;
        DipFild.setText(dip);
        FormFild.setText(form);
        langFild.setText(lang);
        HorFild.setText(hor);
        RegFild.setText(reg);
        AssFild.setText(ass);
        ImgFild.setText(img);
        
        

    }

    void setUpdate(boolean b) {
        this.update = b;

    }
 
//    private  void AttachImg(ActionEvent event){
//        JFileChooser chooser = new JFileChooser();
//        chooser.showOpenDialog(null);
//        File f = chooser.getSelectedFile();
//        String filename=f.getAbsolutePath();
//        ImgFild.setText(filename);
//        Image getAbsolutePath = null;
//        ImageIcon icon = new ImageIcon(filename);
//        Image image;
//        image = icon.getImage().getScaledInstance(50,50 , Image.SCALE_SMOOTH);
//        lbl_image.setIcon(icon);
//    }
    
   
   @FXML
    private void AttachImg(ActionEvent event)
    {
        //connection = MaConnection.getInstance().getConnection();
       MaConnection instance = MaConnection.getInstance();
        instance.filen();
        String vpath= instance.getp();
        if(vpath==null){
        }else
        {
            ImgFild.setText(vpath);
            //Imagesh.setImage(new Image(getClass().getResource(vpath).toExternalForm(), 250, 250, true, true));
               
            //enregistrer l'image dans un dossier 
        }
    }
}
    
//    @FXML
//    private void saveImg (){
//    
//     FileChooser fileChooser = new FileChooser();
//                fileChooser.setTitle("Save Image");
//                
//                File file = fileChooser.showSaveDialog(null);
//                if (file != null) {
//                    try {
//                        ImageIO.write(SwingFXUtils.fromFXImage(Imagesh.getImage(),
//                                null), "jpg", file);
//                    } catch (IOException ex) {
//                        Logger.getLogger(
//                            AddDermController.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
//            }
//    }
    
//    ImageView imageViewAdjusted = new ImageView(new Image(getClass().getResource("thinking-man.jpg").toExternalForm(), 250, 250, true, true));
//
//ColorAdjust colorAdjust = new ColorAdjust();
//colorAdjust.setContrast(0.9);
//
//imageViewAdjusted.setEffect(colorAdjust);
//imageViewAdjusted.setCache(true);
//imageViewAdjusted.setCacheHint(CacheHint.SPEED);
//
//Button btnSave = new Button("Save to File");
//btnSave.setOnAction(new EventHandler<ActionEvent>() {
//
//    @Override
//    public void handle(ActionEvent event) {
//        File outputFile = new File("D:/formattedPicture.png");
//        BufferedImage bImage = SwingFXUtils.fromFXImage(imageViewAdjusted.snapshot(null, null), null);
//        try {
//            ImageIO.write(bImage, "png", outputFile);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//});
    
    
    

