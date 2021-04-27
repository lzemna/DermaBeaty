/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import Entities.Formulaire;
import Services.ServiceFormulaire;
import Utils.MaConnection;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



/**
 * FXML Controller class
 *
 * @author amine
 */
public class Back_FormController implements Initializable {

    @FXML
    private TableView<Formulaire> FormulaireTable;
    @FXML
    private TableColumn<Formulaire, Integer> cin;
    @FXML
    private TableColumn<Formulaire, String> quest1;
    @FXML
    private TableColumn<Formulaire, String> quest2;
    @FXML
    private TableColumn<Formulaire, String> quest3;
    @FXML
    private TableColumn<Formulaire, String> quest4;
    @FXML
    private TableColumn<Formulaire, String> quest5;
    @FXML
    private TableColumn<Formulaire, String> quest6;
    @FXML
    private TableColumn<Formulaire, String> type;
    @FXML
    private TableColumn<Formulaire, String> actionCol;
    
    String query = null;
    Connection connection = MaConnection.getInstance().getConnection() ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
         //ServiceFormulaire cnxf= new ServiceFormulaire();
         ObservableList<Formulaire>  formlist = FXCollections.observableArrayList();
              // List<Formulaire> formlist1 = new ArrayList<>();
         Formulaire form = null;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
            
        try {
           loadDate();
        } catch (SQLException ex) {
            Logger.getLogger(Back_FormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
   @FXML
    private void ajoutForm() throws SQLException {
    
               try {
            Parent parent = FXMLLoader.load(getClass().getResource("ajoutForm.fxml"));
            Scene scene = new Scene(parent);
            Stage secstage = new Stage();
            secstage.setScene(scene);
            secstage.initStyle(StageStyle.UTILITY);
            secstage.show();
        } catch (IOException ex) {
            Logger.getLogger(Back_FormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void refreshTable() throws SQLException {
    
                  
               /*  formlist.clear();
                 formlist1=cnxf.getFormulaire();
                 for(int i=0;i<formlist1.size();i++)
                 {
                     formlist.add(new Formulaire(formlist1.get(i).getCin(),formlist1.get(i).getQuest1(),formlist1.get(i).getQuest2(),formlist1.get(i).getQuest3(),formlist1.get(i).getQuest4(),formlist1.get(i).getQuest5(),formlist1.get(i).getQuest6(),formlist1.get(i).getType())); 
                     FormulaireTable.setItems(formlist);
                 }*/
               formlist.clear();
               query = "select * from `formulaire`";
               preparedStatement = connection.prepareStatement(query);
               resultSet = preparedStatement.executeQuery();
      
               while (resultSet.next()) {
            Formulaire f = new Formulaire();
            f.setRef(resultSet.getInt("ref"));
            f.setCin(resultSet.getInt(2));
            f.setQuest1(resultSet.getString(3));
             f.setQuest2(resultSet.getString(4));
              f.setQuest3(resultSet.getString(5));
             f.setQuest4(resultSet.getString(6)); 
             f.setQuest5(resultSet.getString(7));
              f.setQuest6(resultSet.getString(8));
              f.setType(resultSet.getString(9));
               f.setFormCateg(resultSet.getInt(10));
             formlist.add(f);
            FormulaireTable.setItems(formlist);
        }
           
    }
       @FXML
    private void export()  {
    
        try {
            
            query = "select * from `formulaire`";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet("formulairess");
            XSSFRow header = sheet.createRow(0);
            header.createCell(0).setCellValue("ID");
            header.createCell(1).setCellValue("cin");
            header.createCell(2).setCellValue("quest1");
            header.createCell(3).setCellValue("quest2");
            header.createCell(4).setCellValue("quest3");
            header.createCell(5).setCellValue("quest4");
            header.createCell(6).setCellValue("quest5");
            header.createCell(7).setCellValue("quest6");
            header.createCell(8).setCellValue("type");
             header.createCell(9).setCellValue("FormCateg");
            
            int index =1;
            while (resultSet.next()) {
                XSSFRow row = sheet.createRow(index);
                row.createCell(0).setCellValue(resultSet.getInt("ref"));
                row.createCell(1).setCellValue(resultSet.getInt(2));
                row.createCell(2).setCellValue(resultSet.getString(3));
                row.createCell(3).setCellValue(resultSet.getString(4));
                row.createCell(4).setCellValue(resultSet.getString(5));
                row.createCell(5).setCellValue(resultSet.getString(6));
                row.createCell(6).setCellValue(resultSet.getString(7));
                row.createCell(7).setCellValue(resultSet.getString(8));
                row.createCell(8).setCellValue(resultSet.getString(9));
                 row.createCell(9).setCellValue(resultSet.getInt(10));
                index++;
            }
            try {
                FileOutputStream fileout = new FileOutputStream("Formulaire.xlsx");
                wb.write(fileout);
                fileout.close();
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("information");
                alert.setHeaderText(null);
                alert.setContentText("formulaire exported in EXCEL SHEET");
                alert.showAndWait();
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Back_FormController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Back_FormController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Back_FormController.class.getName()).log(Level.SEVERE, null, ex);
        }   
        
   }
    private void close(MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    private void loadDate() throws SQLException {
        
      
        refreshTable();
        
        cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        quest1.setCellValueFactory(new PropertyValueFactory<>("quest1"));
        quest2.setCellValueFactory(new PropertyValueFactory<>("quest2"));
        quest3.setCellValueFactory(new PropertyValueFactory<>("quest3"));
        quest4.setCellValueFactory(new PropertyValueFactory<>("quest4"));
        quest5.setCellValueFactory(new PropertyValueFactory<>("quest5"));
        quest6.setCellValueFactory(new PropertyValueFactory<>("quest6"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
       
        //add cell of button edit 
         //add cell of button edit 
         Callback <TableColumn<Formulaire, String>, TableCell<Formulaire, String>> cellFoctory = (TableColumn<Formulaire, String> param) -> {
            // make cell containing buttons
            final TableCell<Formulaire, String> cell = new TableCell<Formulaire, String>() {    
               @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    }else {

                        Button deleteIcon = new Button("delete");
                        Button editIcon =  new Button("edit");
                     
                      
                         deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                       
                        deleteIcon.setOnMouseClicked(event -> {
                           try{ 
                                form = FormulaireTable.getSelectionModel().getSelectedItem();
                              query = "Delete  from `formulaire` where ref ="+form.getRef();
                                connection = MaConnection.getInstance().getConnection();
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.execute();
                               // cnxf.deleteFormulaire(form);
                                refreshTable();
                           } catch (SQLException ex) {
                                Logger.getLogger(Back_FormController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                      editIcon.setOnMouseClicked(event -> {
                            
                            form = FormulaireTable.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("ajoutForm.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(Back_FormController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            AjoutFormController AjoutFormsController = loader.getController();
                            AjoutFormsController.setUpdate(true);
                            AjoutFormsController.setTextField(form.getRef(),form.getCin(),form.getQuest1(), form.getQuest2(),form.getQuest3(),form.getQuest4(),form.getQuest5(),form.getQuest6(),form.getType());
                            Parent parent = loader.getRoot();
                            Stage ajoutstage = new Stage();
                            ajoutstage.setScene(new Scene(parent));
                            ajoutstage.initStyle(StageStyle.UTILITY);
                            ajoutstage.show();
                 
                        });
                     
                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
//                        HBox.setMargin(deleteIcon,new Insets(0, 0, 0, 50));
//                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

    
}
                }
            };
                    return cell;
                    }; 
         actionCol.setCellFactory(cellFoctory);
         FormulaireTable.setItems(formlist);
         
         
    }
        @FXML
   private void goConseil() throws IOException{
     Parent parent = FXMLLoader.load(getClass().getResource("Back_Conseil.fxml"));
            Scene scene = new Scene(parent);
            Stage consstage = new Stage();
            consstage.setScene(scene);
            consstage.initStyle(StageStyle.UTILITY);
            consstage.show();
   
   }
     @FXML
   private void goCategorie() throws IOException{
     Parent parent = FXMLLoader.load(getClass().getResource("Back_Categorie.fxml"));
            Scene scene = new Scene(parent);
            Stage consstage = new Stage();
            consstage.setScene(scene);
            consstage.initStyle(StageStyle.UTILITY);
            consstage.show();
   
   }
     @FXML
   private void gouser() throws IOException{
     Parent parent = FXMLLoader.load(getClass().getResource("Back_user.fxml"));
            Scene scene = new Scene(parent);
            Stage consstage = new Stage();
            consstage.setScene(scene);
            consstage.initStyle(StageStyle.UTILITY);
            consstage.show();
   
   }
    @FXML
   private void godermcat() throws IOException{
     Parent parent = FXMLLoader.load(getClass().getResource("back_CatDerm.fxml"));
            Scene scene = new Scene(parent);
            Stage consstage = new Stage();
            consstage.setScene(scene);
            consstage.initStyle(StageStyle.UTILITY);
            consstage.show();
   
   }
      @FXML
   private void goderm() throws IOException{
     Parent parent = FXMLLoader.load(getClass().getResource("back_Dermatologue.fxml"));
            Scene scene = new Scene(parent);
            Stage consstage = new Stage();
            consstage.setScene(scene);
            consstage.initStyle(StageStyle.UTILITY);
            consstage.show();
   
   }

    @FXML
    private void goCommande(javafx.scene.input.MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("Commade.fxml"));
            Scene scene = new Scene(parent);
            Stage formstage = new Stage();
            formstage.setScene(scene);
            formstage.initStyle(StageStyle.UTILITY);
            formstage.show();
        } catch (IOException ex) {
            Logger.getLogger(Back_FormController.class.getName()).log(Level.SEVERE, null, ex);
        }
   
    }
}
