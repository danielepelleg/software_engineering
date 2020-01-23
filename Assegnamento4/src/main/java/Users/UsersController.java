package Users;

import AlertBox.WarningBox;
import Database.DatabaseManager;
import SportClub.*;
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
    private ComboBox<String> typeComboBox;

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

    @FXML
    private Label usernameLabel;

    @FXML
    private TextField nameField;

    @FXML
    private TextField surnameField;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    Stage dialogStage = new Stage();
    Scene scene;

    private ObservableList<Users> data;
    private ObservableList<String> options;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
        setUserComboBox();
        setTypeCombobox();
        setLabels();
    }

    /**
     * Set the text of the labels.
     */
    private void setLabels(){
        usernameLabel.setText(Session.getAdminSession().getUsername());
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
     * Set the options of the typeComboBox.
     */
    private void setTypeCombobox(){
        this.options = FXCollections.observableArrayList();
        this.options.add("Admin");
        this.options.add("Member");
        this.typeComboBox.setItems(null);
        this.typeComboBox.setItems(this.options);
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

    /**
     * Add a user in the database
     *
     * @param event press on Add Button
     */
    @FXML
    void addUser(ActionEvent event) {
        if(!fieldsEmpty() && typeComboBox.getValue() != null){
            String name = nameField.getText();
            String surname = surnameField.getText();
            String username = usernameField.getText();
            String password = passwordField.getText();
            Admin admin = (Admin) Session.getAdminSession();
            if(typeComboBox.getValue().equals("Admin")){
                Admin newAdmin = new Admin(name,surname,username,password);
                admin.addMember(newAdmin);
            }
            else if(typeComboBox.getValue().equals("Member")){
                Member newMember = new Member(name,surname,username,password);
                admin.addMember(newMember);
            }
            loadData();
            setUserComboBox();
            cancel(event);
        }
        else new WarningBox("You have left some fields empty!", "Informations Missing");
    }

    /**
     * Delete a user from the database
     *
     * @param event press on Delete Button
     */
    @FXML
    void deleteUser(ActionEvent event) {
        if(userComboBox.getValue() != null){
            Admin admin = (Admin) Session.getAdminSession();
            admin.removeMember(DatabaseManager.getSelectedMember(userComboBox.getValue()));
            loadData();
            setUserComboBox();
        }
        else new WarningBox("You have left some fields empty!", "Informations Missing");
    }

    /**
     * Update a user's data in the database
     *
     * @param event press on Update Button
     */
    @FXML
    void updateUser(ActionEvent event) {
        if(!fieldsEmpty() && userComboBox.getValue() != null){
            String name = nameField.getText();
            String surname = surnameField.getText();
            String username = usernameField.getText();
            String password = passwordField.getText();
            Admin admin = (Admin) Session.getAdminSession();
            admin.editMember(DatabaseManager.getSelectedMember(userComboBox.getValue()),name,surname,username,password);
            loadData();
            setUserComboBox();
            cancel(event);
        }
        else new WarningBox("You have left some fields empty!", "Informations Missing");
    }

    /**
     * Reset to empty values the TextField and the PasswordField
     *
     * @param event press on reset button
     */
    @FXML
    void cancel(ActionEvent event) {
        this.nameField.clear();
        this.surnameField.clear();
        this.usernameField.clear();
        this.passwordField.clear();
    }

    /**
     * Check if the UsernameField and the PasswordField have been left empty.
     *
     * @return true if they have, false if not
     */
    public boolean fieldsEmpty(){
        if(nameField.getText().equals("") || surnameField.getText().equals("") || usernameField.getText().equals("") || passwordField.getText().equals("")) {
            return true;
        }
        else return false;
    }
}
