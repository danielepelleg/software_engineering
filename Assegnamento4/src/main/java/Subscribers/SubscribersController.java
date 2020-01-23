package Subscribers;

import Activities.Activities;
import AlertBox.WarningBox;
import Database.DatabaseManager;
import SportClub.Admin;
import SportClub.Course;
import SportClub.Race;
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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class SubscribersController implements Initializable {

    @FXML
    private TableView<Subscription> activityTable;

    @FXML
    private TableColumn<Subscription, String> activityNameColumn;

    @FXML
    private TableColumn<Subscription, String> activityNumbersColumn;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label memberTypeLabel;

    @FXML
    private Button menuButton;


    Stage dialogStage = new Stage();
    Scene scene;

    private ObservableList<Subscription> data;
    private ObservableList<String> options;

    /**
     * Initialize the page
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
        setLabels();
    }

    /**
     * Set the text of the labels.
     */
    private void setLabels(){
        if(!Session.adminSessions.isEmpty())
        {
            usernameLabel.setText(Session.getAdminSession().getUsername());
            memberTypeLabel.setText("Admin");
        }
        else if(!Session.membersSessions.isEmpty()) {
            usernameLabel.setText(Session.getMemberSession().getUsername());
            memberTypeLabel.setText("Member");
        }
    }

    /**
     * Load he data in the TableView
     */
    private void loadData() {
        try {
            this.data = FXCollections.observableArrayList();
            PreparedStatement pstmt = DatabaseManager.getConnection().prepareStatement("SELECT * FROM sportclub.numbersubscribers");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                this.data.add(new Subscription(rs.getString(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            System.err.println("Error " + e);
        }
        this.activityNameColumn.setCellValueFactory(new PropertyValueFactory<>("activityName"));
        this.activityNumbersColumn.setCellValueFactory(new PropertyValueFactory<>("subscribed"));
        this.activityTable.setItems(null);
        this.activityTable.setItems(this.data);
    }

    /**
     * Back to Member menu page.
     *
     * @param event press on Back to Menu button
     */
    @FXML
    void backMenu(ActionEvent event) {
        try {
            if (!Session.adminSessions.isEmpty()) {
                Node source = (Node) event.getSource();
                dialogStage = (Stage) source.getScene().getWindow();
                this.scene = new Scene(FXMLLoader.load(getClass().getResource("../MenuAdmin/AdminMenu.fxml")));
                dialogStage.setScene(scene);
                dialogStage.show();
            } else if (!Session.membersSessions.isEmpty()) {
                Node source = (Node) event.getSource();
                dialogStage = (Stage) source.getScene().getWindow();
                this.scene = new Scene(FXMLLoader.load(getClass().getResource("../MenuMember/MemberMenu.fxml")));
                dialogStage.setScene(scene);
                dialogStage.show();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}