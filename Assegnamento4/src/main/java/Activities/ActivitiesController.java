package Activities;

import Database.DatabaseManager;

import SportClub.Activity;
import SportClub.Course;
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


public class ActivitiesController implements Initializable {

    @FXML
    private ComboBox<String> typeCombobox;

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
    private TableView<Activities> activityTable;

    @FXML
    private TableColumn<Activities, String> activityNameColumn;

    @FXML
    private TableColumn<Activities, String> activityTypeColumn;

    Stage dialogStage = new Stage();
    Scene scene;

    private ObservableList<Activities> data;
    private ObservableList<String> options;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTypeCombobox();
        setUserComboBox();
        loadData();
    }

    /**
     * Load he data in the TableView
     */
    private void loadData(){
        try {
            this.data = FXCollections.observableArrayList();
            PreparedStatement pstmt = DatabaseManager.getConnection().prepareStatement("SELECT * FROM sportclub.course");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                this.data.add(new Activities(rs.getString(1),"Course"));
            }
            pstmt = DatabaseManager.getConnection().prepareStatement("SELECT * FROM sportclub.race");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                this.data.add(new Activities(rs.getString(1),"Race"));
            }
        }
        catch (SQLException e)
        {
            System.err.println("Error " + e);
        }
        this.activityNameColumn.setCellValueFactory(new PropertyValueFactory<>("activityName"));
        this.activityTypeColumn.setCellValueFactory(new PropertyValueFactory<>("activityType"));
        this.activityTable.setItems(null);
        this.activityTable.setItems(this.data);
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
    private void setTypeCombobox(){
        this.options = FXCollections.observableArrayList();
        this.options.add("Course");
        this.options.add("Race");
        this.typeCombobox.setItems(null);
        this.typeCombobox.setItems(this.options);
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
    void deleteActivity(ActionEvent event) {

    }

    @FXML
    void addActivity(ActionEvent event) {

    }

    @FXML
    void updateActivity(ActionEvent event) {

    }

}