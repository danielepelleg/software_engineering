package Activities;

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


public class ActivitiesController {

    @FXML
    private Button subscribeButton;

    @FXML
    private ComboBox<String> userComboBox;

    @FXML
    private Button unsubscribeButton;

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private Button loadButton;

    @FXML
    private Button menuButton;

    @FXML
    private TableView<Subscription> subscriptionTable;

    @FXML
    private TableColumn<Subscription, String> courseColumn;

    @FXML
    private TableColumn<Subscription, String> subscriptionColumn;

    @FXML
    void backMenu(ActionEvent event) {

    }

    @FXML
    void loadSubscriptionData(ActionEvent event) {

    }

    @FXML
    void subscribeMember(ActionEvent event) {

    }

    @FXML
    void unsubscribeMember(ActionEvent event) {

    }
}