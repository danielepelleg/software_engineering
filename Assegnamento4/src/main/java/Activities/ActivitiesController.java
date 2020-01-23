package Activities;

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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class ActivitiesController implements Initializable {

    @FXML
    private ComboBox<String> typeCombobox;

    @FXML
    private ComboBox<String> activityComboBox;

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

    @FXML
    private Label usernameLabel;

    @FXML
    private TextField nameField;


    Stage dialogStage = new Stage();
    Scene scene;

    private ObservableList<Activities> data;
    private ObservableList<String> options;

    /**
     * Initialize the page
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTypeCombobox();
        setActivityComboBox();
        loadData();
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
    private void setActivityComboBox(){
        try {
            this.options = FXCollections.observableArrayList();

            PreparedStatement pstmt = DatabaseManager.getConnection().prepareStatement
                    ("SELECT sportclub.course.name FROM sportclub.course");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                this.options.add(rs.getString(1));
            }
            pstmt = DatabaseManager.getConnection().prepareStatement("SELECT sportclub.race.name FROM sportclub.race");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                this.options.add(rs.getString(1));
            }
        }
        catch (SQLException e)
        {
            System.err.println("Error " + e);
        }
        this.activityComboBox.setItems(null);
        this.activityComboBox.setItems(this.options);
    }

    /**
     * Set the options of the typeComboBox.
     */
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

    /**
     * Delete the activity chosen from the database
     *
     * @param event press on Delete Button
     */
    @FXML
    void deleteActivity(ActionEvent event) {
        if(activityComboBox.getValue() != null){
            Admin admin = (Admin) Session.getAdminSession();
            admin.deleteActivity(DatabaseManager.getSelectedActivity(activityComboBox.getValue()));
            loadData();
            setActivityComboBox();
        }
        else new WarningBox("You have left some fields empty!", "Informations Missing");
    }

    /**
     * Add a new activity in the database
     *
     * @param event press on Add Button
     */
    @FXML
    void addActivity(ActionEvent event) {
        if(!fieldsEmpty() && (typeCombobox.getValue() != null)){
            String name = nameField.getText();
            Admin admin = (Admin) Session.getAdminSession();
            if(typeCombobox.getValue().equals("Course")){
                Course newCourse = new Course(name);
                admin.addActivity(newCourse);
            }
            else if (typeCombobox.getValue().equals("Race")){
                Race newRace = new Race(name);
                admin.addActivity(newRace);
            }
            loadData();
            setActivityComboBox();
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
    }

    /**
     * Update the data in the tableview
     * @param event
     */
    @FXML
    void updateActivity(ActionEvent event) {
        if(!fieldsEmpty() && activityComboBox.getValue() != null){
            String name = nameField.getText();
            Admin admin = (Admin) Session.getAdminSession();
            if(DatabaseManager.getSelectedActivity(activityComboBox.getValue()).getClass().equals(Course.class)){
                Course course = new Course(activityComboBox.getValue());
                admin.editActivity(course,name);
            }
            else if (DatabaseManager.getSelectedActivity(activityComboBox.getValue()).getClass().equals(Race.class)){
                Race race = new Race(activityComboBox.getValue());
                admin.editActivity(race,name);
            }
            loadData();
            setActivityComboBox();
            cancel(event);
        }
        else new WarningBox("You have left some fields empty!", "Informations Missing");
    }

    /**
     * Check if the UsernameField and the PasswordField have been left empty.
     *
     * @return true if they have, false if not
     */
    public boolean fieldsEmpty(){
        if(nameField.getText().equals("")) { return true;}
        else return false;
    }

}