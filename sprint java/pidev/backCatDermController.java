/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import Entities.CategorieDerm;
import Entities.User;
import Services.ServiceCatDerm;
import Utils.MaConnection;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author IMNA
 */
public class backCatDermController implements Initializable {

    @FXML
    private TableView<CategorieDerm> CatTable;
    @FXML
    private TableColumn<CategorieDerm, String> RefCol;
    @FXML
    private TableColumn<CategorieDerm, String> LocalCol;
    @FXML
    private TableColumn<CategorieDerm, String> NomcatCol;
    @FXML
    private TableColumn<CategorieDerm, String> editCol;
    @FXML
    private TextField txt_rech;

    /**
     * Initializes the controller class.
     */
     String query = null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    CategorieDerm  cat = null ;
    ServiceCatDerm ser=new ServiceCatDerm();
    List <CategorieDerm> catA = new ArrayList<>();
    
   
     ObservableList<CategorieDerm>  CatDermList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            LoadDate();
        } catch (SQLException ex) {
            Logger.getLogger(backCatDermController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void refreshTable() throws SQLException {
         CatDermList.clear();
           // UserList=(ObservableList<User>) scU.getUsers();
            query = "SELECT * FROM `categorie_derm`";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            CategorieDerm p2 = new CategorieDerm();
            p2.setRef(resultSet.getInt("ref"));
            p2.setLocalisation(resultSet.getString(2));
            p2.setNomCat(resultSet.getString(3));
           
            
            CatDermList.add(p2);
            CatTable.setItems(CatDermList);
        }
    }

    @FXML
    private void getAddView(MouseEvent event) throws SQLException {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("addCatDerm.fxml"));
            Scene scene = new Scene(parent);
            Stage secstage = new Stage();
            secstage.setScene(scene);
            secstage.initStyle(StageStyle.UTILITY);
            secstage.show();
        } catch (IOException ex) {
            Logger.getLogger(backUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void LoadDate() throws SQLException {
     connection=MaConnection.getInstance().getConnection();
        refreshTable();
    RefCol.setCellValueFactory(new PropertyValueFactory<>("ref"));
    LocalCol.setCellValueFactory(new PropertyValueFactory<>("localisation"));
    NomcatCol.setCellValueFactory(new PropertyValueFactory<>("nomCat"));
     Callback <TableColumn<CategorieDerm, String>, TableCell<CategorieDerm, String>> cellFoctory = (TableColumn<CategorieDerm, String> param) -> {
            // make cell containing buttons
            final TableCell<CategorieDerm, String> cell = new TableCell<CategorieDerm, String>() {    
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
                                cat = CatTable.getSelectionModel().getSelectedItem();
                                query = "DELETE FROM `categorie_derm`  WHERE ref ="+cat.getRef();
                                connection = MaConnection.getInstance().getConnection();
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.execute();
                                refreshTable();
                           } catch (SQLException ex) {
                                Logger.getLogger(backUserController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                      editIcon.setOnMouseClicked(event -> {
                            
                            cat = CatTable.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("addCatDerm.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(backUserController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            AddCatDermController addcatsController = loader.getController();
                            addcatsController.setUpdate(true);
                            addcatsController.setTextField(cat.getRef(),cat.getLocalisation(), cat.getNomCat());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
                 
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
         editCol.setCellFactory(cellFoctory);
         CatTable.setItems(CatDermList);
    }
    
 
      @FXML
      private void trierTable() throws SQLException {
    
               CatDermList.clear();
              catA=ser.getCatDerms();
               ser.trierCatNom(catA);
               for(int i=0;i<catA.size();i++  ) {
             CategorieDerm c = new CategorieDerm();
            c.setRef(catA.get(i).getRef());
            c.setLocalisation(catA.get(i).getLocalisation());
            c.setNomCat(catA.get(i).getNomCat()); 
             
           
             CatDermList.add(c);
            CatTable.setItems(CatDermList);
        }
               
       RefCol.setCellValueFactory(new PropertyValueFactory<>("ref"));
    LocalCol.setCellValueFactory(new PropertyValueFactory<>("localisation"));
    NomcatCol.setCellValueFactory(new PropertyValueFactory<>("nomCat"));
           
    }
      @FXML
    private void rechercher() throws SQLException {
      CatDermList.clear();
              String Nom=txt_rech.getText();
              catA=ser.getCatDerms();        
               for(int i=0;i<catA.size();i++  ) {
                   if(ser.rechercheCat(catA.get(i), Nom)==true){
             CategorieDerm c = new CategorieDerm();
            c.setRef(catA.get(i).getRef());
            c.setLocalisation(catA.get(i).getLocalisation());
            c.setNomCat(catA.get(i).getNomCat()); 
             
           
             CatDermList.add(c);
            CatTable.setItems(CatDermList);
        }}
               
       RefCol.setCellValueFactory(new PropertyValueFactory<>("ref"));
    LocalCol.setCellValueFactory(new PropertyValueFactory<>("localisation"));
    NomcatCol.setCellValueFactory(new PropertyValueFactory<>("nomCat"));
    
    }
      @FXML
   private void gocatFormulaire() throws IOException{
     Parent parent = FXMLLoader.load(getClass().getResource("Back_Categorie.fxml"));
            Scene scene = new Scene(parent);
            Stage formstage = new Stage();
            formstage.setScene(scene);
            formstage.initStyle(StageStyle.UTILITY);
            formstage.show();
   
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
   private void goConseil() throws IOException{
     Parent parent = FXMLLoader.load(getClass().getResource("Back_Conseil.fxml"));
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

 
}

