package Users;

import Activities.Activities;
import AlertBox.WarningBox;
import Database.DatabaseManager;
import MenuMember.Subscription;
import SportClub.Course;
import SportClub.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class UsersController implements Initializable{

    @FXML
    private ComboBox<String> userComboBox;

    @FXML
    private Button deleteButton;

    @FXML
    private Button addButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button menuButton;

    @FXML
    private TableView<Users> usersTable;

    @FXML
    private TableColumn<String, Users> nameColumn;

    @FXML
    private TableColumn<String, Users> surnameColumn;

    @FXML
    private TableColumn<String, Users> usernameColumn;

    @FXML
    private TableColumn<String, Users> userTypeColumn;

    @FXML
    private TableColumn<String, Users> passwordColumn;

    Stage dialogStage = new Stage();
    Scene scene;

    private ObservableList<Users> data;
    private ObservableList<String> options;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
        setUserComboBox();
    }

    /**
     * Load he data in the TableView
     */
    private void loadData(){
        try {
            this.data = FXCollections.observableArrayList();
            PreparedStatement pstmt = DatabaseManager.getConnection().prepareStatement("SELECT * FROM sportclub.member");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                this.data.add(new Users(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(5),rs.getString(4)));
            }
        }
        catch (SQLException e)
        {
            System.err.println("Error " + e);
        }
        this.nameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
        this.surnameColumn.setCellValueFactory(new PropertyValueFactory<>("userSurname"));
        this.usernameColumn.setCellValueFactory(new PropertyValueFactory<>("userUsername"));
        this.userTypeColumn.setCellValueFactory(new PropertyValueFactory<>("userType"));
        this.passwordColumn.setCellValueFactory(new PropertyValueFactory<>("userPassword"));
        this.usersTable.setItems(null);
        this.usersTable.setItems(this.data);
    }

    /**
     * Set the options of the userComboBox.
     */
    private void setUserComboBox(){
        try {
            this.options = FXCollections.observableArrayList();

            PreparedStatement pstmt = DatabaseManager.getConnection().prepareStatement
                    ("SELECT sportclub.member.username FROM sportclub.member");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                this.options.add(rs.getString(1));
            }
        }
        catch (SQLException e)
        {
            System.err.println("Error " + e);
        }
        this.userComboBox.setItems(null);
        this.userComboBox.setItems(this.options);
    }

    /**
     * Back to Member menu page.
     *
     * @param event press on Back to Menu button
     */
    @FXML
    void backMenu(ActionEvent event) {
        try{
            Node source = (Node) event.getSource();
            dialogStage = (Stage) source.getScene().getWindow();
            this.scene = new Scene(FXMLLoader.load(getClass().getResource("../MenuAdmin/AdminMenu.fxml")));
            dialogStage.setScene(scene);
            dialogStage.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    void addUser(ActionEvent event) {

    }

    @FXML
    void deleteUser(ActionEvent event) {

    }

    @FXML
    void updateUser(ActionEvent event) {

    }
}
