/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import Entities.Conseil;
import Services.ServiceConseil;


import Utils.MaConnection;
import java.awt.event.MouseEvent;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class Back_ConseilController implements Initializable {
    @FXML
    private TableView<Conseil> ConseilTable;
    @FXML
    private TableColumn<Conseil, Integer > Reference;
    @FXML
    private TableColumn<Conseil, String> Remarques;
    @FXML
    private TableColumn<Conseil, String> date_red;
    @FXML
    private TableColumn<Conseil, String> date_limite;
    @FXML
    private TableColumn<Conseil, String> nom_derma;
    @FXML
    private TableColumn<Conseil, String> email;
    @FXML
    private TableColumn<Conseil, String> actionCol;
     @FXML
    private TextField email_rech;
     String query = null;
    Connection connection = MaConnection.getInstance().getConnection() ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
         //ServiceFormulaire cnxf= new ServiceFormulaire();
         ObservableList<Conseil>  conslist = FXCollections.observableArrayList();
              // List<Formulaire> formlist1 = new ArrayList<>();
         Conseil cons = null;
             ServiceConseil cnx=new ServiceConseil(); 
         List<Conseil> consl = new ArrayList<>();
        
    
    
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
   private void ajoutCons() throws SQLException {
    
               try {
            Parent parent = FXMLLoader.load(getClass().getResource("ajoutCons.fxml"));
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
    
               conslist.clear();
               query = "select * from `conseil`";
               preparedStatement = connection.prepareStatement(query);
               resultSet = preparedStatement.executeQuery();
      
               while (resultSet.next()) {
             Conseil c = new Conseil();
            c.setReference(resultSet.getInt("reference"));
            c.setRemarques(resultSet.getString("remarques"));
            c.setDate_red(resultSet.getDate("date_red"));
             c.setDate_limite(resultSet.getDate("date_limite"));
            c.setNom_derma(resultSet.getString("nom_derma"));
             c.setEmail(resultSet.getString("email")); 
             
           
             conslist.add(c);
            ConseilTable.setItems(conslist);
        }
           
    }
    private void close(MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    private void loadDate() throws SQLException {
        
      
        refreshTable();
        
        Reference.setCellValueFactory(new PropertyValueFactory<>("reference"));
        Remarques.setCellValueFactory(new PropertyValueFactory<>("remarques"));
        date_red.setCellValueFactory(new PropertyValueFactory<>("date_red"));
        date_limite.setCellValueFactory(new PropertyValueFactory<>("date_limite"));
        nom_derma.setCellValueFactory(new PropertyValueFactory<>("nom_derma"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        
        //add cell of button edit 
         //add cell of button edit 
         Callback <TableColumn<Conseil, String>, TableCell<Conseil, String>> cellFoctory = (TableColumn<Conseil, String> param) -> {
            // make cell containing buttons
            final TableCell<Conseil, String> cell = new TableCell<Conseil, String>() {    
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
                                cons = ConseilTable.getSelectionModel().getSelectedItem();
                              query = "Delete  from `conseil` where reference ="+cons.getReference();
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
                            
                           cons = ConseilTable.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("ajoutCons.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(Back_ConseilController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            AjoutConsController AjoutconsController = loader.getController();
                            AjoutconsController.setUpdate(true);
                            AjoutconsController.setTextField(cons.getReference(),cons.getRemarques(),cons.getNom_derma(),cons.getEmail());
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
         ConseilTable.setItems(conslist);
         
         
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
      private void trierTable() throws SQLException {
    
               conslist.clear();
              consl=cnx.getConseil();
               cnx.trierConseilDate(consl);
               for(int i=0;i<consl.size();i++  ) {
             Conseil c = new Conseil();
            c.setReference(consl.get(i).getReference());
            c.setRemarques(consl.get(i).getRemarques());
            c.setDate_red(consl.get(i).getDate_red());
             c.setDate_limite(consl.get(i).getDate_limite());
            c.setNom_derma(consl.get(i).getNom_derma());
             c.setEmail(consl.get(i).getEmail()); 
             
           
             conslist.add(c);
            ConseilTable.setItems(conslist);
        }
               
        Reference.setCellValueFactory(new PropertyValueFactory<>("reference"));
        Remarques.setCellValueFactory(new PropertyValueFactory<>("remarques"));
        date_red.setCellValueFactory(new PropertyValueFactory<>("date_red"));
        date_limite.setCellValueFactory(new PropertyValueFactory<>("date_limite"));
        nom_derma.setCellValueFactory(new PropertyValueFactory<>("nom_derma"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
           
    }
    
       @FXML
    private void rechercher() throws SQLException {
    
              conslist.clear();
               String Email = email_rech.getText();
              consl=cnx.getConseil();
               
               for(int i=0;i<consl.size();i++  ) {
                     if(cnx.rechercheConseil(consl.get(i), Email)== true){
             Conseil c = new Conseil();
            c.setReference(consl.get(i).getReference());
            c.setRemarques(consl.get(i).getRemarques());
            c.setDate_red(consl.get(i).getDate_red());
             c.setDate_limite(consl.get(i).getDate_limite());
            c.setNom_derma(consl.get(i).getNom_derma());
             c.setEmail(consl.get(i).getEmail()); 
             
           
             conslist.add(c);
            ConseilTable.setItems(conslist);
        }
               }
               
        Reference.setCellValueFactory(new PropertyValueFactory<>("reference"));
        Remarques.setCellValueFactory(new PropertyValueFactory<>("remarques"));
        date_red.setCellValueFactory(new PropertyValueFactory<>("date_red"));
        date_limite.setCellValueFactory(new PropertyValueFactory<>("date_limite"));
        nom_derma.setCellValueFactory(new PropertyValueFactory<>("nom_derma"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
           
    }

    @FXML
    private void goCommande(KeyEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("Commade.fxml"));
            Scene scene = new Scene(parent);
            Stage formstage = new Stage();
            formstage.setScene(scene);
            formstage.initStyle(StageStyle.UTILITY);
            formstage.show();
        } catch (IOException ex) {
            Logger.getLogger(Back_ConseilController.class.getName()).log(Level.SEVERE, null, ex);
        }
   
    }
   
}
