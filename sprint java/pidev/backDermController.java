/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import Entities.Dermatologue;
import Entities.User;
import Utils.MaConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
public class backDermController implements Initializable {

    @FXML
    private TableView<Dermatologue> DermsTable;
    @FXML
    private TableColumn<Dermatologue, String> DipCol;
    @FXML
    private TableColumn<Dermatologue, String> FormCol;
    @FXML
    private TableColumn<Dermatologue, String> LangCol;
    @FXML
    private TableColumn<Dermatologue, String> HorCol;
    @FXML
    private TableColumn<Dermatologue, String> RegCol;
    @FXML
    private TableColumn<Dermatologue, String> AssCol;
    @FXML
    private TableColumn<Dermatologue, String> ImgCol;
    
    @FXML
    private TableColumn<Dermatologue, String> editCol;

    /**
     * Initializes the controller class.
     */
    String query = null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    Dermatologue  derm = null ;
    
    ObservableList<Dermatologue>  DermList = FXCollections.observableArrayList();
    @Override
    
    public void initialize(URL url, ResourceBundle rb) {
        try {
            LoadDate();
        } catch (SQLException ex) {
            Logger.getLogger(backDermController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void refreshTable() throws SQLException {
        
        DermList.clear();
        query = "SELECT * FROM `dermatologue`";
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        
        while (resultSet.next()) {
            Dermatologue p2 = new Dermatologue();
            p2.setIdDerm(resultSet.getInt(1));
            p2.setDiplome(resultSet.getString(3));
            p2.setFormation(resultSet.getString(4));
            p2.setLangue(resultSet.getString(5));
            p2.setHoraire(resultSet.getString(6));
            p2.setModereglement(resultSet.getString(7));
            p2.setAssurancemaladie(resultSet.getString(8));
            p2.setImage(resultSet.getString(9));
            
            DermList.add(p2);
            DermsTable.setItems(DermList);
        }
    }

    @FXML
    private void getAddView(MouseEvent event) {
         try {
            Parent parent = FXMLLoader.load(getClass().getResource("addDerm.fxml"));
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
    DipCol.setCellValueFactory(new PropertyValueFactory<>("diplome"));
    FormCol.setCellValueFactory(new PropertyValueFactory<>("formation"));
    LangCol.setCellValueFactory(new PropertyValueFactory<>("langue"));
    HorCol.setCellValueFactory(new PropertyValueFactory<>("horaire"));
    RegCol.setCellValueFactory(new PropertyValueFactory<>("modereglement"));
    AssCol.setCellValueFactory(new PropertyValueFactory<>("assurancemaladie"));
    ImgCol.setCellValueFactory(new PropertyValueFactory<>("image"));
    //add cell of button edit 
         Callback <TableColumn<Dermatologue, String>, TableCell<Dermatologue, String>> cellFoctory = (TableColumn<Dermatologue, String> param) -> {
            // make cell containing buttons
            final TableCell<Dermatologue, String> cell = new TableCell<Dermatologue, String>() {    
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
                                derm = DermsTable.getSelectionModel().getSelectedItem();
                                query = "Delete  from `dermatologue` where id ="+derm.getIdDerm();
                                connection = MaConnection.getInstance().getConnection();
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.execute();
                                refreshTable();
                           } catch (SQLException ex) {
                                Logger.getLogger(backDermController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                       editIcon.setOnMouseClicked(event -> {
                            
                            derm = DermsTable.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("addDerm.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(backDermController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            AddDermController addDermsController = loader.getController();
                            addDermsController.setUpdate(true);
                            addDermsController.setTextField(derm.getIdDerm(),derm.getDiplome(), derm.getFormation(),derm.getLangue(),derm.getHoraire(), derm.getModereglement(),derm.getAssurancemaladie(),derm.getImage());
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
         DermsTable.setItems(DermList);
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
    

