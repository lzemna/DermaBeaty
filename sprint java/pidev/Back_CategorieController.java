/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;


import Entities.Categorie_f;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class Back_CategorieController implements Initializable {

        @FXML
    private TableView<Categorie_f> CategTable;
    @FXML
    private TableColumn<Categorie_f, Integer > Ref;
    @FXML
    private TableColumn<Categorie_f, String> nom;
    @FXML
    private TableColumn<Categorie_f, String> type;
    
    @FXML
    private TableColumn<Categorie_f, String> actionCol;
    
     String query = null;
    Connection connection = MaConnection.getInstance().getConnection() ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
         //ServiceFormulaire cnxf= new ServiceFormulaire();
         ObservableList<Categorie_f>  categlist = FXCollections.observableArrayList();
              // List<Formulaire> formlist1 = new ArrayList<>();
         Categorie_f categ = null;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
           loadDate();
        } catch (SQLException ex) {
            Logger.getLogger(Back_CategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
       
   @FXML
   private void ajoutCateg() throws SQLException {
    
               try {
            Parent parent = FXMLLoader.load(getClass().getResource("ajoutC.fxml"));
            Scene scene = new Scene(parent);
            Stage tresstage = new Stage();
            tresstage.setScene(scene);
            tresstage.initStyle(StageStyle.UTILITY);
            tresstage.show();
        } catch (IOException ex) {
            Logger.getLogger(Back_CategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void refreshTable() throws SQLException {
    
               categlist.clear();
               query = "select * from `form_categ`";
               preparedStatement = connection.prepareStatement(query);
               resultSet = preparedStatement.executeQuery();
      
               while (resultSet.next()) {
             Categorie_f c = new Categorie_f();
            c.setId_cat(resultSet.getInt("id_cat"));
            c.setNom(resultSet.getString("nom"));
            c.setType(resultSet.getString("type"));
            
             
           
             categlist.add(c);
            CategTable.setItems(categlist);
        }
           
    }
    private void close(MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    private void loadDate() throws SQLException {
        
      
        refreshTable();
        
        Ref.setCellValueFactory(new PropertyValueFactory<>("Ref"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        
        
        //add cell of button edit 
         //add cell of button edit 
         Callback <TableColumn<Categorie_f, String>, TableCell<Categorie_f, String>> cellFoctory = (TableColumn<Categorie_f, String> param) -> {
            // make cell containing buttons
            final TableCell<Categorie_f, String> cell = new TableCell<Categorie_f, String>() {    
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
                                categ = CategTable.getSelectionModel().getSelectedItem();
                              query = "Delete  from `form_categ` where id_cat ="+categ.getId_cat();
                                connection = MaConnection.getInstance().getConnection();
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.execute();
                               // cnxf.deleteFormulaire(form);
                                refreshTable();
                           } catch (SQLException ex) {
                                Logger.getLogger(Back_CategorieController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                      editIcon.setOnMouseClicked(event -> {
                            
                           categ = CategTable.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("ajoutC.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(Back_CategorieController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            AjoutCController AjoutcaController = loader.getController();
                            AjoutcaController.setUpdate(true);
                           AjoutcaController.setTextField(categ.getId_cat(),categ.getNom(),categ.getType());
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
         CategTable.setItems(categlist);
         
         
    }
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

    @FXML
    private void goCommande(javafx.scene.input.MouseEvent event) {
            try {
                Parent parent = FXMLLoader.load(getClass().getResource("back_User.fxml"));
                Scene scene = new Scene(parent);
                Stage formstage = new Stage();
                formstage.setScene(scene);
                formstage.initStyle(StageStyle.UTILITY);
                formstage.show();
            } catch (IOException ex) {
                Logger.getLogger(Back_CategorieController.class.getName()).log(Level.SEVERE, null, ex);
            }
   
          }

    
}
