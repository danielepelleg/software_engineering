package Users;

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


public class UsersController {

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
    private TableView<Subscription> subscriptionTable;

    @FXML
    private TableColumn<String, Subscription> courseColumn;

    @FXML
    private TableColumn<String, Subscription> subscriptionColumn;

    @FXML
    void backMenu(ActionEvent event) {

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
