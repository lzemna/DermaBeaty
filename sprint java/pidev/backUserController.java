/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

//import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import Entities.User;
import Services.ServiceUser;
import Utils.MaConnection;
import java.awt.Insets;
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
import javafx.event.ActionEvent;
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
 * @author IMNA
 */
public class backUserController implements Initializable {

    @FXML
    private TableView<User> UsersTable;
    @FXML
    private TableColumn<User, Integer> CinCol;
    @FXML
    private TableColumn<User, Integer> EmailCol;
    @FXML
    private TableColumn<User, Integer> RolesCol;
    @FXML
    private TableColumn<User, Integer> PasswordCol;
    @FXML
    private TableColumn<User, String> NomCol;
    @FXML
    private TableColumn<User, String> PrenomCol;
    @FXML
    private TableColumn<User, String> AdresseCol;
    @FXML
    private TableColumn<User, Integer> NumeroCol;
    @FXML
    private TableColumn<User, String> editCol;

    /**
     * Initializes the controller class.
     */
     String query = null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    User  user = null ;
    ServiceUser scU= new ServiceUser();
   
     ObservableList<User>  UserList = FXCollections.observableArrayList();
    
     @Override
    
    public void initialize(URL url, ResourceBundle rb) {
        try {
            LoadDate();
        } catch (SQLException ex) {
            Logger.getLogger(backUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    private void close(MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void getAddView() throws IOException {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("addUser.fxml"));
            Scene scene = new Scene(parent);
            Stage secstage = new Stage();
            secstage.setScene(scene);
            secstage.initStyle(StageStyle.UTILITY);
            secstage.show();
        } catch (IOException ex) {
            Logger.getLogger(backUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    @FXML
    private void refreshTable() throws SQLException {
        
            UserList.clear();
           // UserList=(ObservableList<User>) scU.getUsers();
            query = "SELECT * FROM `user`";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
          while (resultSet.next()){
              User p2 = new User();
            p2.setId(resultSet.getInt("id"));
            p2.setCin(resultSet.getInt(1));
            p2.setEmail(resultSet.getString(2));
            String role = resultSet.getString(3);
            p2.setRoles(role);
         
            p2.setPassword(resultSet.getString(4));
            p2.setNom(resultSet.getString(5));
            p2.setPrenom(resultSet.getString(6));
            p2.setAdresse(resultSet.getString(7));
            p2.setNumero(resultSet.getInt(8));  
              UserList.add(p2);
                 
                UsersTable.setItems(UserList);
                
            }
           // System.out.println("iciiiiiiiii");
             
                
         
        
    }
    
    private void LoadDate() throws SQLException{
        
        connection=MaConnection.getInstance().getConnection();
        refreshTable();
    CinCol.setCellValueFactory(new PropertyValueFactory<>("cin"));
    EmailCol.setCellValueFactory(new PropertyValueFactory<>("Email"));
    RolesCol.setCellValueFactory(new PropertyValueFactory<>("roles"));
    PasswordCol.setCellValueFactory(new PropertyValueFactory<>("password"));
    NomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
    PrenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
    AdresseCol.setCellValueFactory(new PropertyValueFactory<>("adresse"));
    NumeroCol.setCellValueFactory(new PropertyValueFactory<>("numero"));
    
     //add cell of button edit 
         Callback <TableColumn<User, String>, TableCell<User, String>> cellFoctory = (TableColumn<User, String> param) -> {
            // make cell containing buttons
            final TableCell<User, String> cell = new TableCell<User, String>() {    
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
                                user = UsersTable.getSelectionModel().getSelectedItem();
                                query = "DELETE FROM `user` WHERE id  ="+user.getId();
                                connection = MaConnection.getInstance().getConnection();
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.execute();
                                refreshTable();
                           } catch (SQLException ex) {
                                Logger.getLogger(backUserController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                      editIcon.setOnMouseClicked(event -> {
                            
                            user = UsersTable.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("addUser.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(backUserController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            AddUserController addUsersController = loader.getController();
                            addUsersController.setUpdate(true);
                            addUsersController.setTextField(user.getId(), user.getCin() , user.getEmail() , user.getPassword() ,user.getNom(),user.getPrenom(),user.getAdresse(),user.getNumero());
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
         UsersTable.setItems(UserList);
    }
    
    private void getDermView() throws IOException {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("back_Dermatologue.fxml"));
            Scene scene = new Scene(parent);
            Stage secstage = new Stage();
            secstage.setScene(scene);
            secstage.initStyle(StageStyle.UTILITY);
            secstage.show();
        } catch (IOException ex) {
            Logger.getLogger(backUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    private void getCatView() throws IOException {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("back_CatDerm.fxml"));
            Scene scene = new Scene(parent);
            Stage secstage = new Stage();
            secstage.setScene(scene);
            secstage.initStyle(StageStyle.UTILITY);
            secstage.show();
        } catch (IOException ex) {
            Logger.getLogger(backUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
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

    @FXML
    private void goprod(ActionEvent event) throws IOException {
         Parent parent = FXMLLoader.load(getClass().getResource("backprods.fxml"));
            Scene scene = new Scene(parent);
            Stage prodstage = new Stage();
            prodstage.setScene(scene);
            prodstage.initStyle(StageStyle.UTILITY);
            prodstage.show();
   
    }

    @FXML
    private void gocat(ActionEvent event) throws IOException {
         Parent parent = FXMLLoader.load(getClass().getResource("backCats.fxml"));
            Scene scene = new Scene(parent);
            Stage catstage = new Stage();
            catstage.setScene(scene);
            catstage.initStyle(StageStyle.UTILITY);
            catstage.show();
   
    }

}
          

    